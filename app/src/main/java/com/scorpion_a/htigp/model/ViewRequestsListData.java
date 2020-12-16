package com.scorpion_a.htigp.model;

public class ViewRequestsListData {
    private String viewRequestsNumber;
    private String viewRequestsDesc;
    private String requestsStatus;

    public ViewRequestsListData(String viewRequestsNumber, String viewRequestsDesc, String requestsStatus) {
        this.viewRequestsNumber = viewRequestsNumber;
        this.viewRequestsDesc = viewRequestsDesc;
        this.requestsStatus = requestsStatus;
    }

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
}
