package com.scorpion_a.htigp;

public class ViewRequestsListData {
    private String viewRequestsTitle;
    private String requestsStatus;
    public ViewRequestsListData(String viewRequestsTitle, String requestsStatus) {
        this.viewRequestsTitle = viewRequestsTitle;
        this.requestsStatus = requestsStatus;
    }
    public String getviewRequestsTitle() {
        return viewRequestsTitle;
    }
    public void setviewRequestsTitle(String viewRequestsTitle) {
        this.viewRequestsTitle = viewRequestsTitle;
    }
    public String getrequestsStatus() {
        return requestsStatus;
    }
    public void setrequestsStatus(String requestsStatus) {
        this.requestsStatus = requestsStatus;
    }
}
