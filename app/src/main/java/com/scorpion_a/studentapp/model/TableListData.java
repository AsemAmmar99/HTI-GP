package com.scorpion_a.studentapp.model;

public class TableListData {
    String Gpa;
    String Units;

    public TableListData(String gpa, String units) {
        Gpa = gpa;
        Units = units;
    }

    public String getGpa() {
        return Gpa;
    }

    public void setGpa(String gpa) {
        Gpa = gpa;
    }

    public String getUnits() {
        return Units;
    }

    public void setUnits(String units) {
        Units = units;
    }
}
