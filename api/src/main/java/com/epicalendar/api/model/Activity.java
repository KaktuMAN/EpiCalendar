package com.epicalendar.api.model;

import com.epicalendar.api.model.dto.PostActivity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "activities")
@Getter
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "event_id")
    private int eventId;

    @Column(name = "mail")
    private String mail;

    @Column(name = "title")
    private String title;

    @Column(name = "room")
    private String room;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public Activity(PostActivity activity) {
        this.eventId = activity.getEventId();
        this.mail = activity.getMail();
        this.title = activity.getTitle();
        this.room = activity.getRoom();
        this.startDate = new Date(activity.getStartDate());
        this.endDate = new Date(activity.getEndDate());
    }

    public void update(PostActivity activity) {
        this.mail = activity.getMail();
        this.title = activity.getTitle();
        this.room = activity.getRoom();
        this.startDate = new Date(activity.getStartDate());
        this.endDate = new Date(activity.getEndDate());
    }
}
