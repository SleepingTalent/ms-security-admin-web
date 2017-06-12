package com.babcock.test.helper.selenium.page;

import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;


public class DashboardPage extends Page {

    public static final String DASHBOARD_URL = "navigate-to-index";

    public DashboardPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToPage(String host) {
        navigateToUrl(host+ DASHBOARD_URL);
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("dashboardContent");
    }

    public void clickLink(String linkName) throws Exception {
            switch (linkName) {
                case "usersLink":
                    getPageHelper().clickOnElement("usersLink");
                    break;
                case "createUserLink":
                    getPageHelper().clickOnElement("createUserLink");
                    break;
                case "subjectsLink":
                    getPageHelper().clickOnElement("subjectsLink");
                    break;
                case "createSubjectLink":
                    getPageHelper().clickOnElement("createSubjectLink");
                    break;
                case "rolesLink":
                    getPageHelper().clickOnElement("rolesLink");
                    break;
                case "createRoleLink":
                    getPageHelper().clickOnElement("createRoleLink");
                    break;
                case "permissionsLink":
                    getPageHelper().clickOnElement("permissionsLink");
                    break;
                case "createPermissionLink":
                    getPageHelper().clickOnElement("createPermissionLink");
                    break;
                default:
                    throw new Exception("Unsupported Link : " + linkName);
            }
    }
}
