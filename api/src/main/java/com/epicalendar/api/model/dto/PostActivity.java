package com.epicalendar.api.model.dto;

public class PostActivity {
    private final String mail;
    private final String title;
    private final String room;
    private final String startDate;
    private final String endDate;

    public PostActivity(String mail, String title, String room, String startDate, String endDate) {
        this.mail = mail;
        this.title = title;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
