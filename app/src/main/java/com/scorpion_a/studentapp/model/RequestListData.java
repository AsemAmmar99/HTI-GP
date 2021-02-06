package com.scorpion_a.studentapp.model;

public class RequestListData {
    private String requestTitle;
    private String requestPrice;

    public RequestListData(String requestTitle, String requestPrice) {
        this.requestTitle = requestTitle;
        this.requestPrice = requestPrice;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public String getRequestPrice() {
        return requestPrice;
    }

    public void setRequestPrice(String requestPrice) {
        this.requestPrice = requestPrice;
    }
}
