package com.babcock.test.helper.selenium.page.role;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AssignRolePermissionsPage extends Page {

    public AssignRolePermissionsPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void navigateToPage(String url) {
        //Cant Navigate to Page Directly
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("assignPermissionContent");
    }

    public void clickAssignBtn() {
        getPageHelper().findElementById("assignBtn").click();
    }

    public void assignPermission(String permission) {
        getPageHelper().selectDualListElement(permission);
    }

    public void assertPermissionAssigned(String permission) {
            WebElement option = getPageHelper().findOptionWithText(permission);
            assertThat(option.getAttribute("outerHTML")).contains("selected=\"selected\"");
    }

    public class FormData {
        private String permission;

        public FormData(String permission) {
            this.permission = permission;
        }

        public String getPermission() {
            return permission;
        }
    }
}
