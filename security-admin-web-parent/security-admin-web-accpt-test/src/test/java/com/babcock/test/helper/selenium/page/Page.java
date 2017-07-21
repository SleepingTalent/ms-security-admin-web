package com.babcock.test.helper.selenium.page;


import com.babcock.test.helper.selenium.page.helper.PageHelper;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Java6Assertions.assertThat;

public abstract class Page {

    private static Logger logger = LoggerFactory.getLogger(Page.class);

    private CucumberWebDriver webDriver;
    private PageHelper pageHelper;

    private NavigationPanel navigationPanel;

    protected Page(CucumberWebDriver webDriver) {
        this.webDriver = webDriver;
        pageHelper = new PageHelper(webDriver);
        navigationPanel = new NavigationPanel(webDriver);
    }

    abstract public  void navigateToPage(String url);

    protected void navigateToUrl(String url) {
        logger.info("Navigating To Url:{}",url);
        webDriver.navigate().to(url);
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }

    public CucumberWebDriver getWebDriver() {
        return webDriver;
    }

    public NavigationPanel getNavigationPanel() { return navigationPanel; }


    protected void filterTable(String value) {
        List<WebElement> elementList =  getPageHelper().findElementsByClass("input-sm");

        boolean found = false;
        for(WebElement element: elementList) {
            if(element.getTagName().equals("input")) {
                element.clear();
                element.sendKeys(value);
                found = true;
            }
        }

        //Hacky Sleep then retry table filter
        if(!found) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            elementList =  getPageHelper().findElementsByClass("input-sm");

            for(WebElement element: elementList) {
                if(element.getTagName().equals("input")) {
                    element.clear();
                    element.sendKeys(value);
                    found = true;
                }
            }
        }

        assertThat(found).isTrue();
    }


    public abstract void assertPageIsDisplayed();
}
