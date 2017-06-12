package com.babcock.test.mock.service.response.helper;

import com.babcock.test.mock.service.response.PermissionJsonMock;
import com.babcock.test.mock.service.response.RoleJsonMock;
import com.babcock.test.mock.service.response.SubjectJsonMock;
import com.babcock.test.mock.service.response.UserJsonMock;
import com.babcock.test.mock.service.response.helper.predicate.FindPermByName;
import com.babcock.test.mock.service.response.helper.predicate.FindRoleByName;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonDataHelper {

    private List<UserJsonMock> userList;
    private List<SubjectJsonMock> subjectList;
    private List<RoleJsonMock> roleList;
    private List<PermissionJsonMock> permissionList;

    @PostConstruct
    public void init(){
        userList = new ArrayList<>();
        subjectList = new ArrayList<>();
        roleList = new ArrayList<>();
        permissionList = new ArrayList<>();

        PermissionJsonMock drawingViewerPermission = new PermissionJsonMock();
        drawingViewerPermission.setId("1");
        drawingViewerPermission.setName("drawing:viewer");
        drawingViewerPermission.setDescription("Drawing View Permission");

        PermissionJsonMock documentViewerPermission = new PermissionJsonMock();
        documentViewerPermission.setId("2");
        documentViewerPermission.setName("document:viewer");
        documentViewerPermission.setDescription("Document View Permission");

        permissionList.add(drawingViewerPermission);
        permissionList.add(documentViewerPermission);

        RoleJsonMock documentViewerRole = new RoleJsonMock();
        documentViewerRole.setId("1");
        documentViewerRole.setName("documentViewer");
        documentViewerRole.setDescription("Document View Role");
        documentViewerRole.setPermissions("2");

        RoleJsonMock drawingViewerRole = new RoleJsonMock();
        drawingViewerRole.setId("2");
        drawingViewerRole.setName("drawingViewer");
        drawingViewerRole.setDescription("Drawing View Role");
        drawingViewerRole.setPermissions("1");

        roleList.add(documentViewerRole);
        roleList.add(drawingViewerRole);

        UserJsonMock joeBloggs = new UserJsonMock();
        joeBloggs.setId("1");
        joeBloggs.setName("Joe Blogs");
        joeBloggs.setEmail("test@test.com");
        joeBloggs.setJobTile("jobTitle");
        joeBloggs.setDepartment("dept");
        joeBloggs.setLocation("location");
        joeBloggs.setPayNumber("1234");

        UserJsonMock janeDoe = new UserJsonMock();
        janeDoe.setId("2");
        janeDoe.setName("Jane Doe");
        janeDoe.setEmail("test@test.com");
        janeDoe.setJobTile("jobTitle2");
        janeDoe.setDepartment("dept2");
        janeDoe.setLocation("location2");
        janeDoe.setPayNumber("1235");

        userList.add(joeBloggs);
        userList.add(janeDoe);

        SubjectJsonMock jDoeSubject = new SubjectJsonMock();
        jDoeSubject.setId("1");
        jDoeSubject.setUsername("jDoe");
        jDoeSubject.setRoles("1");

        SubjectJsonMock jBloggsSubject = new SubjectJsonMock();
        jBloggsSubject.setId("1");
        jBloggsSubject.setUsername("jBloggs");
        jBloggsSubject.setRoles("2");

        subjectList.add(jDoeSubject);
        subjectList.add(jBloggsSubject);
    }

    public List<UserJsonMock> getInitialUserList() {
        return userList;
    }

    public List<SubjectJsonMock> getInitialSubjectList() {
        return subjectList;
    }

    public List<RoleJsonMock> getInitialRolesList() {
        return roleList;
    }

    public List<PermissionJsonMock> getInitialPermissionList() {
        return permissionList;
    }

    public PermissionJsonMock findPermissionByDisplayName(String displayName) {
        return permissionList.stream().filter(new FindPermByName(displayName)).findFirst().get();
    }

    public RoleJsonMock findRoleByDisplayName(String displayName) {
        return roleList.stream().filter(new FindRoleByName(displayName)).findFirst().get();
    }
}
