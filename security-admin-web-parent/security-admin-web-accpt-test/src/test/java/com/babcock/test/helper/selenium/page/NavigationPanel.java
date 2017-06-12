package com.babcock.test.helper.selenium.page;

import com.babcock.test.helper.selenium.page.helper.PageHelper;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;

public class NavigationPanel {

    private CucumberWebDriver webDriver;
    private PageHelper pageHelper;

    public NavigationPanel(CucumberWebDriver webDriver) {
        this.webDriver = webDriver;
        this.pageHelper = new PageHelper(webDriver);
    }


    public void clickLink(String linkName) throws Exception {
            switch (linkName) {
                case "dashboard":
                    pageHelper.findElementById("dashboardNavLink").click();
                    break;
                case "users":
                    pageHelper.findElementById("usersNavLink").click();
                    break;
                case "subjects":
                    pageHelper.findElementById("subjectsNavLink").click();
                    break;
                case "roles":
                    pageHelper.findElementById("rolesNavLink").click();
                    break;
                case "permissions":
                    pageHelper.findElementById("permissionsNavLink").click();
                    break;
                default:
                    throw new Exception("Unsupported Link : " + linkName);
            }
        }
}
