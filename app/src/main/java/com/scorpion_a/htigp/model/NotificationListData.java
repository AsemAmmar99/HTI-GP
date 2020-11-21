package com.scorpion_a.htigp.model;

public class NotificationListData {
    private String notificationTitle;
    private String notificationDesc;
    private String notificationTime;
    public NotificationListData(String notificationTitle, String notificationDesc, String notificationTime) {
        this.notificationTitle = notificationTitle;
        this.notificationDesc = notificationDesc;
        this.notificationTime = notificationTime;
    }
    public String getnotificationTitle() {
        return notificationTitle;
    }
    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }
    public String getnotificationDesc() {
        return notificationDesc;
    }
    public void setnotificationDesc(String notificationDesc) {
        this.notificationDesc = notificationDesc;
    }
    public String getnotificationTime() {
        return notificationTime;
    }
    public void setnotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}
