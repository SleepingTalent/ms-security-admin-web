package com.babcock.test.mock.service.response.helper;

import com.babcock.test.mock.service.response.PermissionJsonMock;
import com.babcock.test.mock.service.response.RoleJsonMock;
import com.babcock.test.mock.service.response.SubjectJsonMock;
import com.babcock.test.mock.service.response.UserJsonMock;
import com.babcock.test.mock.service.response.JsonMockResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonTest {

    @Test
    public void permissionJson() throws JsonProcessingException {
        PermissionJsonMock permissionJson = new PermissionJsonMock();
        permissionJson.setId("1");
        permissionJson.setName("name");
        permissionJson.setDescription("desc");

        JsonResponseHelper jsonResponseHelper = getJsonResponseHelper();
        assertEquals("{\"id\":\"1\",\"name\":\"name\",\"description\":\"desc\"}",jsonResponseHelper.getJsonString(permissionJson));
    }

    @Test
    public void roleJson() throws JsonProcessingException {
        RoleJsonMock roleJson = new RoleJsonMock();
        roleJson.setId("1");
        roleJson.setName("roleName");
        roleJson.setDescription("roleDesc");
        roleJson.setPermissions("1,2");

        JsonResponseHelper jsonResponseHelper = getJsonResponseHelper();
        assertEquals("{\"id\":\"1\",\"name\":\"roleName\",\"description\":\"roleDesc\",\"permissions\":\"1,2\"}"
                ,jsonResponseHelper.getJsonString(roleJson));
    }

    @Test
    public void roleJsonList() throws JsonProcessingException {
        RoleJsonMock roleJson = new RoleJsonMock();
        roleJson.setId("1");
        roleJson.setName("roleName");
        roleJson.setDescription("roleDesc");

        RoleJsonMock roleJson2 = new RoleJsonMock();
        roleJson2.setId("2");
        roleJson2.setName("roleName2");
        roleJson2.setDescription("roleDesc2");

        List<JsonMockResponse> roleList = new ArrayList<>();
        roleList.add(roleJson);
        roleList.add(roleJson2);

        JsonResponseHelper jsonResponseHelper = getJsonResponseHelper();
        assertEquals("[{\"id\":\"1\",\"name\":\"roleName\",\"description\":\"roleDesc\",\"permissions\":\"\"},{\"id\":\"2\",\"name\":\"roleName2\",\"description\":\"roleDesc2\",\"permissions\":\"\"}]",jsonResponseHelper.getJsonString(roleList));
    }

    private JsonResponseHelper getJsonResponseHelper() {
        JsonResponseHelper jsonResponseHelper = new JsonResponseHelper();
        jsonResponseHelper.init();
        return jsonResponseHelper;
    }

    @Test
    public void subjectJson() throws JsonProcessingException {
        SubjectJsonMock subjectJson = new SubjectJsonMock();
        subjectJson.setId("1");
        subjectJson.setUsername("username");
        subjectJson.setRoles("1,2");

        JsonResponseHelper jsonResponseHelper = getJsonResponseHelper();
        assertEquals("{\"id\":\"1\",\"username\":\"username\",\"roles\":\"1,2\"}"
                ,jsonResponseHelper.getJsonString(subjectJson));
    }

    @Test
    public void userJson() throws JsonProcessingException {
        UserJsonMock userJson = new UserJsonMock();
        userJson.setId("1");
        userJson.setName("name");
        userJson.setEmail("test@test.com");
        userJson.setJobTile("jobTitle");
        userJson.setDepartment("dept");
        userJson.setLocation("location");
        userJson.setPayNumber("1234");

        JsonResponseHelper jsonResponseHelper = getJsonResponseHelper();
        assertEquals("{\"id\":\"1\",\"payNumber\":\"1234\",\"name\":\"name\",\"email\":\"test@test.com\",\"jobTile\":\"jobTitle\",\"department\":\"dept\",\"location\":\"location\"}",jsonResponseHelper.getJsonString(userJson));
    }
}
