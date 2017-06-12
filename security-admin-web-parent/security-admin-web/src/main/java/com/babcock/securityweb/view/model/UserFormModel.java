package com.babcock.securityweb.view.model;

import org.hibernate.validator.constraints.NotEmpty;

public class UserFormModel {

    private String id;

    @NotEmpty(message = "Pay Number cannot be empty")
    private String payNumber;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Job Title cannot be empty")
    private String jobTitle;

    @NotEmpty(message = "Department cannot be empty")
    private String department;

    @NotEmpty(message = "Location cannot be empty")
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "UserFormModel{" +
                "id='" + id + '\'' +
                ", payNumber='" + payNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", department='" + department + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
