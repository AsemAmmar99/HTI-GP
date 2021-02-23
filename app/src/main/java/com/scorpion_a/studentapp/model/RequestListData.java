package com.scorpion_a.studentapp.model;

public class RequestListData {
    private String id;
    private String name;
    private String price;
    private String max_number;
    private String for_students;
    private String for_alumni;
    private String for_employee;

    public RequestListData(String id, String name, String price, String max_number, String for_students, String for_alumni, String for_employee) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.max_number = max_number;
        this.for_students = for_students;
        this.for_alumni = for_alumni;
        this.for_employee = for_employee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMax_number() {
        return max_number;
    }

    public void setMax_number(String max_number) {
        this.max_number = max_number;
    }

    public String getFor_students() {
        return for_students;
    }

    public void setFor_students(String for_students) {
        this.for_students = for_students;
    }

    public String getFor_alumni() {
        return for_alumni;
    }

    public void setFor_alumni(String for_alumni) {
        this.for_alumni = for_alumni;
    }

    public String getFor_employee() {
        return for_employee;
    }

    public void setFor_employee(String for_employee) {
        this.for_employee = for_employee;
    }
}
