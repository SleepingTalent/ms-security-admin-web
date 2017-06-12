package com.babcock.test.helper.selenium.webdriver;

import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FirefoxCucumberWebDriver extends FirefoxDriver implements CucumberWebDriver {

    private static Logger logger = LoggerFactory.getLogger(FirefoxCucumberWebDriver.class);

    public void takeScreenShot(Scenario scenario) {
        try {
            final byte[] screenshot = ((TakesScreenshot) this).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (Exception ex) {
            logger.warn("Error Creating Screenshot",ex);
        }
    }

    public void closeBrowser() {
        close();
    }
}
