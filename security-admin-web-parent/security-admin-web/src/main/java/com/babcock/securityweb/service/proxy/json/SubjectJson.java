package com.babcock.securityweb.service.proxy.json;

public class SubjectJson implements JsonResponse {

    private String id;
    private String username;
    private String password;
    private String roles = "";

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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "SubjectJson{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", roles='" + roles + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
