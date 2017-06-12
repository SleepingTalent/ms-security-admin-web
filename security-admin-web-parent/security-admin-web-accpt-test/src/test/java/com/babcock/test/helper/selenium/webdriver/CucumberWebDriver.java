package com.babcock.test.helper.selenium.webdriver;

import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;

public interface CucumberWebDriver extends WebDriver {

    void takeScreenShot(Scenario scenario);

    void closeBrowser();
}
