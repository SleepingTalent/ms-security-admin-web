package com.babcock.test.runner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty","html:target/cucumber-report","json:target/cucumber-report/result.json"}, glue="com.babcock.test",
        features="classpath:features", tags={"~@wip"}, monochrome = true)
public class SecurityWebIT {

    @Rule
    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(options().port(2425));

    @BeforeClass
    public static void beforeClass() {
        //System.setProperty("browser", "Firefox");
        System.setProperty("browser", "Phantom");
        //System.setProperty("browser", "Chrome");
    }

}
