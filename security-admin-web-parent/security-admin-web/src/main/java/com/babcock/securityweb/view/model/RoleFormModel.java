package com.babcock.securityweb.view.model;

import org.hibernate.validator.constraints.NotEmpty;

public class RoleFormModel {

    private String id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    private String securityPermissions = "";

    private boolean assignPermissions;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSecurityPermissions() {
        return securityPermissions;
    }

    public void setSecurityPermissions(String securityPermissions) {
        this.securityPermissions = securityPermissions;
        //this.securityPermissions = Arrays.asList(securityPermissions.split("\\s*,\\s*"));
    }

    public boolean isAssignPermissions() {
        return assignPermissions;
    }

    public void setAssignPermissions(boolean assignPermissions) {
        this.assignPermissions = assignPermissions;
    }

    @Override
    public String toString() {
        return "RoleFormModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", assignPermissions=" + assignPermissions +
                ", securityPermissions=" + securityPermissions +
                '}';
    }
}
