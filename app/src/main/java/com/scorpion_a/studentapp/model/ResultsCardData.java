package com.scorpion_a.studentapp.model;

public class ResultsCardData {
    private String itemNumber;
    private String itemCode;
    private String itemSubject;
    private String itemGrade;

    public ResultsCardData(String itemNumber, String itemCode, String itemSubject, String itemGrade) {
        this.itemNumber = itemNumber;
        this.itemCode = itemCode;
        this.itemSubject = itemSubject;
        this.itemGrade = itemGrade;
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

    public String getItemGrade() {
        return itemGrade;
    }

    public void setItemGrade(String itemGrade) {
        this.itemGrade = itemGrade;
    }
}
