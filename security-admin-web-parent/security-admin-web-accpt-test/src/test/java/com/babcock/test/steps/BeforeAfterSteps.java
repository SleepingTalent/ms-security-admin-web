package com.babcock.test.steps;

import com.babcock.test.mock.service.response.helper.JsonDataHelper;
import com.babcock.test.mock.service.ServiceAdminService;
import com.babcock.test.runtime.RuntimeState;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class BeforeAfterSteps extends AbstractStep {

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    ServiceAdminService serviceAdminService;

    @Autowired
    JsonDataHelper jsonDataHelper;

    @Before
    public void setUp(Scenario scenario) {
        runtimeState.initialise();
        runtimeState.setScenario(scenario);
        runtimeState.setHost("localhost:" + port);

        serviceAdminService.getUserApiMock().mockGetUsersToReturn(jsonDataHelper.getInitialUserList());
        serviceAdminService.getSubjectApiMock().mockGetSubjectsToReturn(jsonDataHelper.getInitialSubjectList());
        serviceAdminService.getRoleApiMock().mockGetRolesToReturn(jsonDataHelper.getInitialRolesList());
        serviceAdminService.getPermissionApiMock().mockGetPermissionsToReturn(jsonDataHelper.getInitialPermissionList());
    }

    @After
    public void tearDown(Scenario scenario) {

        if(scenario.isFailed()) {
            runtimeState.takeScreenShot();
        }

        runtimeState.closeBrowser();
    }
}

