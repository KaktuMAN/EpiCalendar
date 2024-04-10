package com.epicalendar.api.controllers;

import com.epicalendar.api.model.Activity;
import com.epicalendar.api.model.dto.FullActivity;
import com.epicalendar.api.model.dto.PostActivity;
import com.epicalendar.api.repository.ActivityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Uid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Activity", description = "Activity API")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityRepository activityRepository;

    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @PostMapping("/activity")
    @Operation(summary = "Add a new registration to the database", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = PostActivity.class))))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registration added", content = @Content(schema = @Schema(implementation = int.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<Integer> addActivity(PostActivity activity) {
        return ResponseEntity.ok(activityRepository.save(new Activity(activity)).getId());
    }

    @GetMapping("/activity/{userMail}")
    @Operation(summary = "Get all registrations for a user in a given time frame", parameters = {
            @Parameter(name = "userMail", description = "User mail", required = true),
            @Parameter(name = "startDate", description = "Start date of the time frame (timestamp)", required = true),
            @Parameter(name = "endDate", description = "End date of the time frame (timestamp)", required = true)
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registration found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FullActivity.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Registrations not found", content = @Content)
    })
    public ResponseEntity<List<FullActivity>> getActivities(@PathVariable String userMail, @RequestParam Long startDate, @RequestParam Long endDate) {
        if (startDate == null || endDate == null) {
            return ResponseEntity.badRequest().build();
        }
        Date start = new Date(startDate);
        Date end = new Date(endDate);
        List<Activity> activities = activityRepository.findAllByMailAndStartDateAfterAndEndDateBefore(userMail, start, end);
        return ResponseEntity.ok(activities.stream().map(FullActivity::new).collect(Collectors.toList()));
    }

    @GetMapping("/calendar/{userMail}/ical")
    @Operation(summary = "Get an iCal file with all registrations for a user", parameters = {
            @Parameter(name = "userMail", description = "User mail", required = true)
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "iCal file found", content = @Content(mediaType = "text/calendar")),
            @ApiResponse(responseCode = "404", description = "iCal file not found", content = @Content)
    })
    public ResponseEntity<String> getICal(@PathVariable String userMail) {
        List<Activity> activities = activityRepository.findAllByMail(userMail);
        if (activities.isEmpty())
            return ResponseEntity.notFound().build();
        Calendar calendar = new Calendar().withProdId("-//EpiCalendar 1.0//EN").withDefaults().getFluentTarget();
        activities.forEach(activity -> {
            DateTime startDate = new DateTime(activity.getStartDate());
            DateTime endDate = new DateTime(activity.getEndDate());
            VEvent event = new VEvent(startDate, endDate, activity.getTitle());
            Uid uid = new Uid(activity.getMail() + "-" + activity.getId());
            event.getProperties().add(uid);
            event.getProperties().add(new Location(activity.getRoom()));
            calendar.getComponents().add(event);
        });
        return ResponseEntity.ok(calendar.toString());
    }

    @DeleteMapping("/activity/{userMail}/{id}")
    @Operation(summary = "Delete a registration from the database", parameters = {
            @Parameter(name = "userMail", description = "User mail", required = true),
            @Parameter(name = "id", description = "Registration ID", required = true)
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registration deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Registration not found", content = @Content)
    })
    public ResponseEntity<Void> deleteActivity(@PathVariable String userMail, @PathVariable int id) {
        Activity activity = activityRepository.findByIdAndMail(id, userMail);
        if (activity == null)
            return ResponseEntity.notFound().build();
        activityRepository.delete(activity);
        return ResponseEntity.ok().build();
    }
}