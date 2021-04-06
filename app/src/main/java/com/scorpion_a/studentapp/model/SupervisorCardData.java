package com.scorpion_a.studentapp.model;

public class SupervisorCardData {
    private String itemNumber;
    private String itemStatus;
    private String itemDepartment;
    private String itemDate;
    private String itemName;

    public SupervisorCardData(String itemNumber, String itemStatus, String itemDepartment, String itemDate, String itemName) {
        this.itemNumber = itemNumber;
        this.itemStatus = itemStatus;
        this.itemDepartment = itemDepartment;
        this.itemDate = itemDate;
        this.itemName = itemName;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemDepartment() {
        return itemDepartment;
    }

    public void setItemDepartment(String itemDepartment) {
        this.itemDepartment = itemDepartment;
    }

    public String getItemDate() {
        return itemDate;
    }

    public void setItemDate(String itemDate) {
        this.itemDate = itemDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}