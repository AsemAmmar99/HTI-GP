package com.scorpion_a.studentapp.model;

public class EventsListData {
    private String eventsTitle;
    private String eventsDesc;
    private String eventsDate;

    public EventsListData(String eventsTitle, String eventsDesc, String eventsDate) {
        this.eventsTitle = eventsTitle;
        this.eventsDesc = eventsDesc;
        this.eventsDate = eventsDate;
    }

    public String getEventsTitle() {
        return eventsTitle;
    }

    public void setEventsTitle(String eventsTitle) {
        this.eventsTitle = eventsTitle;
    }

    public String getEventsDesc() {
        return eventsDesc;
    }

    public void setEventsDesc(String eventsDesc) {
        this.eventsDesc = eventsDesc;
    }

    public String getEventsDate() {
        return eventsDate;
    }

    public void setEventsDate(String eventsDate) {
        this.eventsDate = eventsDate;
    }
}
