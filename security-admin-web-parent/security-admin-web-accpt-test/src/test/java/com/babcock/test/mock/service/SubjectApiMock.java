package com.babcock.test.mock.service;

import com.babcock.test.mock.wiremock.WireMockBase;
import com.babcock.test.mock.service.response.SubjectJsonMock;
import com.babcock.test.mock.service.response.helper.JsonDataHelper;
import com.babcock.test.mock.service.response.helper.predicate.FindSubjectById;
import com.babcock.securityweb.service.proxy.SubjectProxy;
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
public class SubjectApiMock extends WireMockBase {

    private Logger logger = LoggerFactory.getLogger(SubjectApiMock.class);

    private static final UUID GET_SUBJECTS_MOCK_ID = UUID.randomUUID();
    private static final UUID GET_FIND_SUBJECT_BY_ID_MOCK_ID = UUID.randomUUID();
    private static final UUID POST_CREATE_SUBJECT_MOCK_ID = UUID.randomUUID();
    private static final UUID PUT_UPDATE_SUBJECT_MOCK_ID = UUID.randomUUID();

    private static final String SUBJECT_ID = "6677998";

    @Value("${security-admin-service-context}")
    String securityServiceContext;

    @Autowired
    JsonDataHelper jsonDataHelper;

    public void mockGetSubjectsToReturn(List<SubjectJsonMock> jsonMockResponseList) {
        mockGetUrlToReturn(securityServiceContext + SubjectProxy.GET_ALL_SUBJECTS_URL, jsonMockResponseList, GET_SUBJECTS_MOCK_ID);
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void updateGetSubjectsMockToReturn(String username, String password, String roles) {
        removeStub(WireMock.getSingleStubMapping(GET_SUBJECTS_MOCK_ID));
        List<SubjectJsonMock> jsonMockResponseList = jsonDataHelper.getInitialSubjectList();

        SubjectJsonMock subjectToUpdate = jsonMockResponseList.stream().filter(new FindSubjectById(SUBJECT_ID)).findFirst().orElse(null);

        if(subjectToUpdate == null) {
            subjectToUpdate = new SubjectJsonMock();
            subjectToUpdate.setId(SUBJECT_ID);
            subjectToUpdate.setUsername(username);
            subjectToUpdate.setPassword(password);
            subjectToUpdate.setRoles(roles);
            jsonMockResponseList.add(subjectToUpdate);
        }else {
            subjectToUpdate.setUsername(username);
            subjectToUpdate.setPassword(password);
            subjectToUpdate.setRoles(roles);
        }

        mockGetUrlToReturn(securityServiceContext + SubjectProxy.GET_ALL_SUBJECTS_URL, jsonMockResponseList, GET_SUBJECTS_MOCK_ID);
    }

    public void mockPostCreateSubjectToReturn(String username, String password) {
        String url = securityServiceContext + SubjectProxy.CREATE_SUBJECT_URL;

        SubjectJsonMock subjectJsonMock = new SubjectJsonMock();
        subjectJsonMock.setId(SUBJECT_ID);
        subjectJsonMock.setUsername(username);
        subjectJsonMock.setPassword(password);

        mockPostUrlToReturn(url, subjectJsonMock, POST_CREATE_SUBJECT_MOCK_ID);
    }

    public void verifyCreateSubjectCalled(String username, String password) {
        String url = securityServiceContext + SubjectProxy.CREATE_SUBJECT_URL;

        SubjectJsonMock subjectJsonMock = new SubjectJsonMock();
        subjectJsonMock.setUsername(username);
        subjectJsonMock.setPassword(password);

        verifyPostUrlWithBody(url,subjectJsonMock);
    }

    public void mockPutUpdateSubjectToReturnSuccess() {
        mockPutUrl(securityServiceContext + SubjectProxy.UPDATE_SUBJECT_URL, PUT_UPDATE_SUBJECT_MOCK_ID);
    }

    public void verifyUpdateSubjectCalled(String username, String password, String roles) {
        String url = securityServiceContext + SubjectProxy.UPDATE_SUBJECT_URL;

        SubjectJsonMock subjectJsonMock = new SubjectJsonMock();
        subjectJsonMock.setId(SUBJECT_ID);
        subjectJsonMock.setUsername(username);
        subjectJsonMock.setPassword(password);
        subjectJsonMock.setRoles(roles);

        verifyPutUrlWithBody(url,subjectJsonMock);
    }

    public void verifyUpdateSubjectCalled(String username, String roles) {
        String url = securityServiceContext + SubjectProxy.UPDATE_SUBJECT_URL;

        SubjectJsonMock subjectJsonMock = new SubjectJsonMock();
        subjectJsonMock.setId(SUBJECT_ID);
        subjectJsonMock.setUsername(username);
        subjectJsonMock.setRoles(roles);

        verifyPutUrlWithBody(url,subjectJsonMock);
    }

    public void mockGetFindSubjectByIdToReturn(String username, String password, String roles) {
        String url = securityServiceContext + SubjectProxy.FIND_SUBJECT_BY_ID_URL;

        url = StringUtils.replace(url,"{id}",SUBJECT_ID);

        SubjectJsonMock subjectJsonMock = new SubjectJsonMock();
        subjectJsonMock.setId(SUBJECT_ID);
        subjectJsonMock.setUsername(username);
        subjectJsonMock.setPassword(password);
        subjectJsonMock.setRoles(roles);

        mockGetUrlToReturn(url, subjectJsonMock, GET_FIND_SUBJECT_BY_ID_MOCK_ID);
    }
}
