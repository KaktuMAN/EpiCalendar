package com.epicalendar.api.repository;

import com.epicalendar.api.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByMail(String mail);
    Activity findByIdAndMail(int id, String mail);
}
