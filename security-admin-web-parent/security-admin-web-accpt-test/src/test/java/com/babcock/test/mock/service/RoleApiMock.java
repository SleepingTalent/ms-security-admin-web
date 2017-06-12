package com.babcock.test.mock.service;

import com.babcock.test.mock.wiremock.WireMockBase;
import com.babcock.test.mock.service.response.RoleJsonMock;
import com.babcock.test.mock.service.response.helper.JsonDataHelper;
import com.babcock.test.mock.service.response.helper.predicate.FindRoleById;
import com.babcock.securityweb.service.proxy.RoleProxy;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.removeStub;

@Component
public class RoleApiMock extends WireMockBase {

    private Logger logger = LoggerFactory.getLogger(RoleApiMock.class);

    private static final String ROLE_ID = "993366";

    private static final UUID GET_ROLES_MOCK_ID = UUID.randomUUID();
    private static final UUID GET_FIND_ROLE_BY_ID_MOCK_ID = UUID.randomUUID();
    private static final UUID POST_CREATE_ROLE_MOCK_ID = UUID.randomUUID();
    private static final UUID PUT_UPDATE_ROLE_MOCK_ID = UUID.randomUUID();

    @Value("${security-admin-service-context}")
    String securityServiceContext;

    @Autowired
    JsonDataHelper jsonDataHelper;

    public void mockGetRolesToReturn(List<RoleJsonMock> jsonMockResponseList) {
        mockGetUrlToReturn(securityServiceContext + RoleProxy.GET_ALL_ROLES_URL, jsonMockResponseList, GET_ROLES_MOCK_ID);
    }

    public void updateGetRolesMockToReturn(String name, String description, String permissions) {
        removeStub(WireMock.getSingleStubMapping(GET_ROLES_MOCK_ID));
        List<RoleJsonMock> jsonMockResponseList = jsonDataHelper.getInitialRolesList();

        RoleJsonMock roleToUpdate = jsonMockResponseList.stream().filter(new FindRoleById(ROLE_ID)).findFirst().orElse(null);

        if(roleToUpdate == null) {
            roleToUpdate = new RoleJsonMock();
            roleToUpdate.setId(ROLE_ID);
            roleToUpdate.setName(name);
            roleToUpdate.setDescription(description);
            roleToUpdate.setPermissions(permissions);
            jsonMockResponseList.add(roleToUpdate);
        }else {
            roleToUpdate.setName(name);
            roleToUpdate.setDescription(description);
            roleToUpdate.setPermissions(permissions);
        }

        mockGetUrlToReturn(securityServiceContext + RoleProxy.GET_ALL_ROLES_URL, jsonMockResponseList, GET_ROLES_MOCK_ID);
    }

    public void mockPostCreateRoleToReturn(String name, String description) {
        String url = securityServiceContext + RoleProxy.CREATE_ROLE_URL;

        RoleJsonMock roleJson = new RoleJsonMock();
        roleJson.setId(ROLE_ID);
        roleJson.setName(name);
        roleJson.setDescription(description);

        mockPostUrlToReturn(url, roleJson, POST_CREATE_ROLE_MOCK_ID);
    }

    public void verifyCreateRoleCalled(String name, String description) {
        String url = securityServiceContext + RoleProxy.CREATE_ROLE_URL;

        RoleJsonMock roleJson = new RoleJsonMock();
        roleJson.setName(name);
        roleJson.setDescription(description);

        verifyPostUrlWithBody(url,roleJson);
    }

    public void mockGetFindRoleByIdToReturn(String name, String description, String permissions) {
        String url = securityServiceContext+RoleProxy.FIND_ROLE_BY_ID_URL;

        url = StringUtils.replace(url,"{id}",ROLE_ID);

        RoleJsonMock roleJson = new RoleJsonMock();
        roleJson.setId(ROLE_ID);
        roleJson.setName(name);
        roleJson.setDescription(description);
        roleJson.setPermissions(permissions);

        mockGetUrlToReturn(url, roleJson, GET_FIND_ROLE_BY_ID_MOCK_ID);
    }


    public void verifyFindRoleById() {
        String url = securityServiceContext+RoleProxy.FIND_ROLE_BY_ID_URL;

        url = StringUtils.replace(url,"{id}",ROLE_ID);
        verifyGetUrl(url);
    }

    public void mockPutUpdateRoleToReturnSuccess() {
        mockPutUrl(securityServiceContext + RoleProxy.UPDATE_ROLE_URL, PUT_UPDATE_ROLE_MOCK_ID);
    }

    public void verifyUpdateRoleCalled(String name, String description, String permissions) {
        String url = securityServiceContext + RoleProxy.UPDATE_ROLE_URL;

        RoleJsonMock roleJson = new RoleJsonMock();
        roleJson.setId(ROLE_ID);
        roleJson.setName(name);
        roleJson.setDescription(description);
        roleJson.setPermissions(permissions);

        verifyPutUrlWithBody(url,roleJson);
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
