package com.babcock.test.helper.selenium.page.permission;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PermissionsPage extends Page {

    public static final String PERMISSIONS_URL = "navigate-to-permissions";

    public PermissionsPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToPage(String host) {
        navigateToUrl(host+ PERMISSIONS_URL);
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("permissionsContent");
    }

    public void filterTableBy(String permission) {
        filterTable(permission);
    }

    public void findPermissionInTable(String permission) {
        getPageHelper().findTableDataWithText(permission);
    }

}
