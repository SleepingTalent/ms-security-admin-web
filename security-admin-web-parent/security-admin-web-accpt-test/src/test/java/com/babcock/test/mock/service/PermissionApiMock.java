package com.babcock.test.mock.service;

import com.babcock.test.mock.wiremock.WireMockBase;
import com.babcock.test.mock.service.response.PermissionJsonMock;
import com.babcock.test.mock.service.response.helper.JsonDataHelper;
import com.babcock.test.mock.service.response.helper.predicate.FindPermById;
import com.babcock.securityweb.service.proxy.PermissionProxy;
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
public class PermissionApiMock extends WireMockBase {

    private Logger logger = LoggerFactory.getLogger(PermissionApiMock.class);

    private static final String PERMISSION_ID = "993355";

    private static final UUID GET_PERMISSIONS_MOCK_ID = UUID.randomUUID();
    private static final UUID POST_CREATE_PERMISSION_MOCK_ID = UUID.randomUUID();

    @Value("${security-admin-service-context}")
    String securityServiceContext;


    @Autowired
    JsonDataHelper jsonDataHelper;

    public void mockGetPermissionsToReturn(List<PermissionJsonMock> jsonMockResponseList) {
        mockGetUrlToReturn(securityServiceContext + PermissionProxy.GET_ALL_PERMISSIONS_URL, jsonMockResponseList, GET_PERMISSIONS_MOCK_ID);
    }

    public void updateGetPermissionsMockToReturn(String name, String description) {
        removeStub(WireMock.getSingleStubMapping(GET_PERMISSIONS_MOCK_ID));
        List<PermissionJsonMock> jsonMockResponseList = jsonDataHelper.getInitialPermissionList();

        PermissionJsonMock permissionJson = jsonMockResponseList.stream().filter(new FindPermById(PERMISSION_ID)).findFirst().orElse(null);

        if(permissionJson == null) {
            permissionJson = new PermissionJsonMock();
            permissionJson.setId(PERMISSION_ID);
            permissionJson.setName(name);
            permissionJson.setDescription(description);
            jsonMockResponseList.add(permissionJson);
        }else {
            permissionJson.setName(name);
            permissionJson.setDescription(description);
        }

        mockGetUrlToReturn(securityServiceContext + PermissionProxy.GET_ALL_PERMISSIONS_URL, jsonMockResponseList, GET_PERMISSIONS_MOCK_ID);
    }

    public void mockPostCreatePermissionToReturn(String name, String description) {
        String url = securityServiceContext + PermissionProxy.CREATE_PERMISSIONS_URL;

        PermissionJsonMock permissionJson = new PermissionJsonMock();
        permissionJson.setId(PERMISSION_ID);
        permissionJson.setName(name);
        permissionJson.setDescription(description);

        mockPostUrlToReturn(url, permissionJson, POST_CREATE_PERMISSION_MOCK_ID);
    }

    public void verifyCreatePermissionCalled(String name, String description) {
        String url = securityServiceContext + PermissionProxy.CREATE_PERMISSIONS_URL;

        PermissionJsonMock permissionJson = new PermissionJsonMock();
        permissionJson.setName(name);
        permissionJson.setDescription(description);

        verifyPostUrlWithBody(url,permissionJson);
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
