package com.babcock.securityweb.view.model;

import org.hibernate.validator.constraints.NotEmpty;

public class SubjectFormModel {

    private String id;

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    private String securityRoles = "";

    private boolean assignRoles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityRoles() {
        return securityRoles;
    }

    public void setSecurityRoles(String securityRoles) {
        this.securityRoles = securityRoles;
    }

    public boolean isAssignRoles() {
        return assignRoles;
    }

    public void setAssignRoles(boolean assignRoles) {
        this.assignRoles = assignRoles;
    }

    @Override
    public String toString() {
        return "SubjectFormModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", securityRoles=" + securityRoles +
                '}';
    }
}
