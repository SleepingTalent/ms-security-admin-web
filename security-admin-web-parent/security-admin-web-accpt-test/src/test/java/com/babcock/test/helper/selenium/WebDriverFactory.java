package com.babcock.test.helper.selenium;

import com.babcock.test.helper.selenium.webdriver.ChromeCucumberWebDriver;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;
import com.babcock.test.helper.selenium.webdriver.FirefoxCucumberWebDriver;
import com.babcock.test.helper.selenium.webdriver.PhantomCucumberWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public final class WebDriverFactory {

    private static Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    public enum Browser {
        FIREFOX, PHANTOM, CHROME;
    }

    public static CucumberWebDriver getWebDriver() {

        Browser browserType = getBrowser();
        CucumberWebDriver webDriver = null;
        URL url = null;

            switch (browserType) {
                case FIREFOX:

                    if(System.getProperty("os.name").contains("Windows")) {
                        setDriverLocation("webdriver.gecko.driver","webdriver/geckodriver.exe");
                    }else {
                        setDriverLocation("webdriver.gecko.driver","webdriver/geckodriver");
                    }

                    webDriver = new FirefoxCucumberWebDriver();
                    break;
                case PHANTOM:
                    webDriver = new PhantomCucumberWebDriver();
                    break;
                case CHROME:

                    if(System.getProperty("os.name").contains("Windows")) {
                        setDriverLocation("webdriver.chrome.driver","webdriver/chromedriver.exe");
                    }else {
                        setDriverLocation("webdriver.chrome.driver","webdriver/chromedriver");
                    }

                    webDriver = new ChromeCucumberWebDriver();
                    break;
            }

            webDriver.getWindowHandle();
//            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return webDriver;
    }

    private static void setDriverLocation(String driverName, String location) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(location);
        logger.debug("Web Driver Lcation {}: "+url.getPath());
        if(url != null) {
            System.setProperty(driverName, url.getPath());
        }
    }

    private static Browser getBrowser() {
        String browserType = System.getProperty("browser");
        logger.debug("Using {} web driver",browserType);

        if("FIREFOX".equalsIgnoreCase(browserType)) {
           return Browser.FIREFOX;
        }else if ("PHANTOM".equalsIgnoreCase(browserType)) {
           return Browser.PHANTOM;
        }else if ("CHROME".equalsIgnoreCase(browserType)) {
            return Browser.CHROME;
        }else {
            logger.warn("Web Browser not supported : ({}) defaulting to PHANTOM",browserType);
            return Browser.PHANTOM ;
        }

    }
}
