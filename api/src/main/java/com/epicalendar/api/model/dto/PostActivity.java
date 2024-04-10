package com.epicalendar.api.model.dto;

import java.util.Date;

public class PostActivity {
    private final int eventId;
    private final String mail;
    private final String title;
    private final String room;
    private final Date startDate;
    private final Date endDate;

    public PostActivity(int eventId, String mail, String title, String room, Date startDate, Date endDate) {
        this.eventId = eventId;
        this.mail = mail;
        this.title = title;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getEventId() {
        return eventId;
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
