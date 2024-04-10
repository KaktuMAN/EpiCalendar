package com.epicalendar.api.model.dto;

import java.util.Date;

public class PostActivity {
    private final long eventId;
    private final String mail;
    private final String title;
    private final String room;
    private final long startDate;
    private final long endDate;

    public PostActivity(long eventId, String mail, String title, String room, long startDate, long endDate) {
        this.eventId = eventId;
        this.mail = mail;
        this.title = title;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getEventId() {
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
        return new Date(startDate);
    }

    public Date getEndDate() {
        return new Date(endDate);
    }
}
