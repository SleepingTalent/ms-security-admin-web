package com.babcock.test.mock.service;

import com.babcock.test.mock.wiremock.WireMockBase;
import com.babcock.test.mock.service.response.UserJsonMock;
import com.babcock.test.mock.service.response.helper.JsonDataHelper;
import com.babcock.test.mock.service.response.helper.predicate.FindUserById;
import com.babcock.securityweb.service.proxy.UserProxy;
import com.babcock.securityweb.service.proxy.json.UserJson;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;

@Component
public class UserApiMock extends WireMockBase {

    private Logger logger = LoggerFactory.getLogger(UserApiMock.class);

    private static final String USER_ID = "889991";

    private static final UUID GET_USERS_MOCK_ID = UUID.randomUUID();
    private static final UUID POST_CREATE_USER_MOCK_ID = UUID.randomUUID();

    @Value("${security-admin-service-context}")
    String securityServiceContext;

    @Autowired
    JsonDataHelper jsonDataHelper;

    public void mockGetUsersToReturn(List<UserJsonMock> jsonMockResponseList) {
        mockGetUrlToReturn(securityServiceContext + UserProxy.GET_ALL_USERS_URL, jsonMockResponseList, GET_USERS_MOCK_ID);
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void mockPostCreateUserToReturn(String payNumber, String name, String email, String jobTitle, String department, String location) {
        String url = securityServiceContext + UserProxy.CREATE_USER_URL;

        UserJson userJson = new UserJson();
        userJson.setId(USER_ID);
        userJson.setPayNumber(payNumber);
        userJson.setName(name);
        userJson.setEmail(email);
        userJson.setJobTile(jobTitle);
        userJson.setDepartment(department);
        userJson.setLocation(location);

        mockPostUrlToReturn(url, userJson, POST_CREATE_USER_MOCK_ID);
    }

    public void updateGetUsersMockToReturn(String payNumber, String name, String email, String jobTitle, String department, String location) {
        removeStub(WireMock.getSingleStubMapping(GET_USERS_MOCK_ID));
        List<UserJsonMock> jsonMockResponseList = jsonDataHelper.getInitialUserList();

        UserJsonMock userJson = jsonMockResponseList.stream().filter(new FindUserById(USER_ID)).findFirst().orElse(null);

        if(userJson == null) {
            userJson = new UserJsonMock();
            userJson.setId(USER_ID);
            userJson.setPayNumber(payNumber);
            userJson.setName(name);
            userJson.setEmail(email);
            userJson.setJobTile(jobTitle);
            userJson.setDepartment(department);
            userJson.setLocation(location);
            jsonMockResponseList.add(userJson);
        }else {
            userJson.setPayNumber(payNumber);
            userJson.setName(name);
            userJson.setEmail(email);
            userJson.setJobTile(jobTitle);
            userJson.setDepartment(department);
            userJson.setLocation(location);
        }

        mockGetUrlToReturn(securityServiceContext + UserProxy.GET_ALL_USERS_URL, jsonMockResponseList, GET_USERS_MOCK_ID);
    }

    public void verifyCreateUserCalled(String payNumber, String name, String email, String jobTitle, String department, String location) {
        String url = securityServiceContext + UserProxy.CREATE_USER_URL;

        UserJson userJson = new UserJson();
        //userJson.setId(USER_ID);
        userJson.setPayNumber(payNumber);
        userJson.setName(name);
        userJson.setEmail(email);
        userJson.setJobTile(jobTitle);
        userJson.setDepartment(department);
        userJson.setLocation(location);

        verifyPostUrlWithBody(url,userJson);
    }
}

