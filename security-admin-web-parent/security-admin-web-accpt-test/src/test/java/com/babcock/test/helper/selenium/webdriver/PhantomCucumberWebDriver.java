package com.babcock.test.helper.selenium.webdriver;

import cucumber.api.Scenario;
import net.anthavio.phanbedder.Phanbedder;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.ArrayList;

public class PhantomCucumberWebDriver extends PhantomJSDriver implements CucumberWebDriver {


    public PhantomCucumberWebDriver() {
        super(getJSCapabilities());
    }

    private static Capabilities getJSCapabilities() {

        ArrayList<String> cliArgsCap = new ArrayList<String>();
        cliArgsCap.add("--ignore-ssl-errors=true");
        cliArgsCap.add("--disk-cache=true");
        cliArgsCap.add("--max-disk-cache-size=256");
        cliArgsCap.add("--proxy-type=none");
        cliArgsCap.add("--web-security=false");
        cliArgsCap.add("--webdriver-loglevel=NONE");

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,cliArgsCap);
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "loadimages",true);
        capabilities.setCapability("takesScreenshot", true);
        //capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, "--logLevel=NONE");

        File phantomJsBinary = Phanbedder.unpack();

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,phantomJsBinary.getAbsolutePath());

        return capabilities;
    }

    public void takeScreenShot(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) this).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    public void closeBrowser() {
        close();
    }
}
