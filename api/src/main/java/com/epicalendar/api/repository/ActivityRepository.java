package com.epicalendar.api.repository;

import com.epicalendar.api.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByMailAndStartDateAfterAndEndDateBefore(String mail, Date startDate, Date endDate);
    List<Activity> findAllByMail(String mail);
    Activity findByEventIdAndMail(int id, String mail);
}
