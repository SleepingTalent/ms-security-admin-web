package com.babcock.test.steps;

import com.babcock.test.helper.asserter.WaitForService;
import com.babcock.test.helper.asserter.WaitUntilAssertionError;
import com.babcock.test.runtime.RuntimeState;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.fail;

public class BeforeAfterSteps extends AbstractStep {

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    RestTemplate restTemplate;

    @Value("${security.admin.web.url}")
    String baseUrl;

    @Value("${security.admin.service.info.url}")
    String securityAdminServiceInfoUrl;

    @Value("${security.admin.web.info.url}")
    String securityAdminWebInfoUrl;

    private static boolean serviceUnavailable = false;

    @Before
    public void setUp(Scenario scenario) throws InterruptedException {
        runtimeState.initialise();
        runtimeState.setScenario(scenario);
        runtimeState.setUniqueKey(generateRandomString(6));

        runtimeState.setBaseUrl(baseUrl);

        WaitForService waitForSecurityAdminWeb = new WaitForService(securityAdminWebInfoUrl, restTemplate);
        waitForSecurityAdminWeb.setMaxWaitTime(420000);

        WaitForService waitForSecurityAdminService = new WaitForService(securityAdminServiceInfoUrl, restTemplate);
        waitForSecurityAdminService.setMaxWaitTime(420000);

        if(serviceUnavailable) {
            fail("security-admin-web docker environment unavailable");
        }

        try {
            System.out.println("waiting for service : " + securityAdminWebInfoUrl);
            waitForSecurityAdminWeb.performAssertion();

            System.out.println("waiting for service : " + securityAdminServiceInfoUrl);
            waitForSecurityAdminService.performAssertion();
        }catch (WaitUntilAssertionError wae) {
            serviceUnavailable = true;
            fail("security-admin-web docker environment unavailable");
        }
    }

    private String generateRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    @After
    public void tearDown(Scenario scenario) {

        if(scenario.isFailed()) {
            runtimeState.takeScreenShot();
        }

        runtimeState.closeBrowser();
    }
}

