package com.scorpion_a.studentapp.model;

import com.scorpion_a.studentapp.model.ViewRequestsClasses.RequestType;
import com.scorpion_a.studentapp.model.ViewRequestsClasses.Student;

public class ViewRequestsListData {
    private String id;
    private String name;
    private String price;
    private String status;
    private String count;
    private String total_price;
    private String created_at;
    private String student_id;
    private Student student;
    private RequestType request_type;

    public ViewRequestsListData(String id, String name, String price, String status, String count, String total_price, String created_at, String student_id, Student student, RequestType request_type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.count = count;
        this.total_price = total_price;
        this.created_at = created_at;
        this.student_id = student_id;
        this.student = student;
        this.request_type = request_type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public RequestType getRequest_type() {
        return request_type;
    }

    public void setRequest_type(RequestType request_type) {
        this.request_type = request_type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
