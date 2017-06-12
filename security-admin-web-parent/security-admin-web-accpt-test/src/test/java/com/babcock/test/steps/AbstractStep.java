package com.babcock.test.steps;

import com.babcock.securityweb.SecurityWebApplication;
import com.babcock.test.configuration.CucumberTestConfiguration;
import com.babcock.test.configuration.TestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
@ActiveProfiles("test")
@Import(CucumberTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestConfiguration.class,SecurityWebApplication.class})
public class AbstractStep {

    @LocalServerPort
    protected int port;

    protected String getBaseUrl() {
       return "http://localhost:"+port+"/security-admin-web-test/";
    }

}
