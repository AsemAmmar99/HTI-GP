package com.scorpion_a.studentapp.model;

public class RegCardData {
    private String itemNumber;
    private String itemCode;
    private String itemSubject;
    private String itemGroup;
    private String itemUnits;

    public RegCardData(String itemNumber, String itemCode, String itemSubject, String itemGroup, String itemUnits) {
        this.itemNumber = itemNumber;
        this.itemCode = itemCode;
        this.itemSubject = itemSubject;
        this.itemGroup = itemGroup;
        this.itemUnits = itemUnits;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemSubject() {
        return itemSubject;
    }

    public void setItemSubject(String itemSubject) {
        this.itemSubject = itemSubject;
    }

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public String getItemUnits() {
        return itemUnits;
    }

    public void setItemUnits(String itemUnits) {
        this.itemUnits = itemUnits;
    }
}
