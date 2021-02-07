package com.scorpion_a.studentapp.model;

public class ViewRequestsListData {
    private String viewRequestsNumber;
    private String viewRequestsDesc;
    private String requestsStatus;
    private String requestsCount;
    private String requestsTime;

    public String getViewRequestsNumber() {
        return viewRequestsNumber;
    }

    public void setViewRequestsNumber(String viewRequestsNumber) {
        this.viewRequestsNumber = viewRequestsNumber;
    }

    public String getViewRequestsDesc() {
        return viewRequestsDesc;
    }

    public void setViewRequestsDesc(String viewRequestsDesc) {
        this.viewRequestsDesc = viewRequestsDesc;
    }

    public String getRequestsStatus() {
        return requestsStatus;
    }

    public void setRequestsStatus(String requestsStatus) {
        this.requestsStatus = requestsStatus;
    }

    public ViewRequestsListData(String viewRequestsNumber, String viewRequestsDesc, String requestsStatus, String requestsCount, String requestsTime) {
        this.viewRequestsNumber = viewRequestsNumber;
        this.viewRequestsDesc = viewRequestsDesc;
        this.requestsStatus = requestsStatus;
        this.requestsCount = requestsCount;
        this.requestsTime = requestsTime;
    }

    public String getRequestsCount() {
        return requestsCount;
    }

    public void setRequestsCount(String requestsCount) {
        this.requestsCount = requestsCount;
    }

    public String getRequestsTime() {
        return requestsTime;
    }

    public void setRequestsTime(String requestsTime) {
        this.requestsTime = requestsTime;
    }
}
