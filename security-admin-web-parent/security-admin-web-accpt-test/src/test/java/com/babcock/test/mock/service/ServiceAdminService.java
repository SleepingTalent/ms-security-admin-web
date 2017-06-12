package com.babcock.test.mock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceAdminService {

    @Autowired
    RoleApiMock roleApiMock;

    @Autowired
    PermissionApiMock permissionApiMock;

    @Autowired
    UserApiMock userApiMock;

    @Autowired
    SubjectApiMock subjectApiMock;

    public RoleApiMock getRoleApiMock() {
        return roleApiMock;
    }

    public PermissionApiMock getPermissionApiMock() {
        return permissionApiMock;
    }

    public UserApiMock getUserApiMock() {
        return userApiMock;
    }

    public SubjectApiMock getSubjectApiMock() {
        return subjectApiMock;
    }

}
