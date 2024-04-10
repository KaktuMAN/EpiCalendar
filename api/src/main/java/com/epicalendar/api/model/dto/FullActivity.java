package com.epicalendar.api.model.dto;

import com.epicalendar.api.model.Activity;

import java.util.Date;

public class FullActivity {
    private final String mail;
    private final String title;
    private final String room;
    private final Date startDate;
    private final Date endDate;

    public FullActivity(String mail, String title, String room, Date startDate, Date endDate) {
        this.mail = mail;
        this.title = title;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public FullActivity(Activity activity) {
        this.mail = activity.getMail();
        this.title = activity.getTitle();
        this.room = activity.getRoom();
        this.startDate = activity.getStartDate();
        this.endDate = activity.getEndDate();
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
