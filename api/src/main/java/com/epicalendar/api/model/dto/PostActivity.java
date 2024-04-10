package com.epicalendar.api.model.dto;

public class PostActivity {
    private final int eventId;
    private final String mail;
    private final String title;
    private final String room;
    private final long startDate;
    private final long endDate;

    public PostActivity(int eventId, String mail, String title, String room, long startDate, long endDate) {
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

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }
}
