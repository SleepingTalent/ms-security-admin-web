package com.babcock.test.helper.selenium.page.role;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;

public class RolesPage extends Page {

    private static final String ROLES_URL = "navigate-to-roles";

    public RolesPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToPage(String host) {
        navigateToUrl(host+ ROLES_URL);
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("rolesContent");
    }

    public void filterTableBy(String role) {
        filterTable(role);
    }

    public void findRoleInTable(String role) {
        getPageHelper().findTableDataWithText(role);
    }

    public void clickOnEditLink() {
        getPageHelper().clickOnElement("editRoleLink");
    }
}
