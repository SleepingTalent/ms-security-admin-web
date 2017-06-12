package com.babcock.test.helper.selenium.page.role;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class EditRolePage extends Page {

    public EditRolePage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void navigateToPage(String url) {
        //Cant Navigate to Page Directly
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("editRoleContent");
    }

    public void clickUpdateBtn() {
        getPageHelper().findElementById("updateBtn").click();
    }

    public void setNameField(String name) {
        getPageHelper().findElementById("name").clear();
        getPageHelper().findElementById("name").sendKeys(name);
    }

    public void setDescriptionField(String description) {
        getPageHelper().findElementById("description").clear();
        getPageHelper().findElementById("description").sendKeys(description);
    }

    public void assignPermission(String permission) {
        getPageHelper().selectDualListElement(permission);
    }

    public void assertPermissionAssigned(String permission) {
        WebElement option = getPageHelper().findOptionWithText(permission);
        assertThat(option.getAttribute("outerHTML")).contains("selected=\"selected\"");
    }

    public class FormData {
        private String name;
        private String description;
        private String permission;

        public FormData(String name, String description, String permission) {
            this.name = name;
            this.description = description;
            this.permission = permission;
        }

        public String getPermission() {
            return permission;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
}
