package com.babcock.test.helper.selenium.page.permission;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CreatePermissionPage extends Page {

    public static final String CREATE_PERMISSION_URL = "navigate-to-create-permission";

    public CreatePermissionPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToPage(String host) {
        navigateToUrl(host+ CREATE_PERMISSION_URL);
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("createPermissionsContent");
    }

    public void clickCreateBtn() {
        getPageHelper().findElementById("createBtn").click();
    }

    public void clickResetBtn() {
        getPageHelper().findElementById("resetBtn").click();
    }

    public void assertValidationMessagesDisplayed() {
        assertThat(getPageHelper().findElementById("nameErrorMsg").getText()).isEqualTo("Name cannot be empty");
        assertThat(getPageHelper().findElementById("descriptionErrorMsg").getText()).isEqualTo("Description cannot be empty");
    }

    public void setNameField(String name) {
        getPageHelper().findElementById("name").sendKeys(name);
    }

    public void setDescriptionField(String description) {
        getPageHelper().findElementById("description").sendKeys(description);
    }

    public void assertNameFieldValue(String value) {
        assertThat(getPageHelper().findElementById("name").getText()).isEqualTo(value);
    }

    public void assertDescriptionFieldValue(String value) {
        assertThat(getPageHelper().findElementById("description").getText()).isEqualTo(value);
    }

    public class FormData {
        private String name;
        private String description;

        public FormData(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
}
