package com.epicalendar.api.model;

import com.epicalendar.api.model.dto.PostActivity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public Activity() {
    }

    public Activity(PostActivity activity) {
        this.mail = activity.getMail();
        this.title = activity.getTitle();
        this.room = activity.getRoom();
        this.startDate = new Date(Long.parseLong(activity.getStartDate()));
        this.endDate = new Date(Long.parseLong(activity.getEndDate()));
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getTitle() {
        return title;
    }

    public String getRoom() {
        return room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}