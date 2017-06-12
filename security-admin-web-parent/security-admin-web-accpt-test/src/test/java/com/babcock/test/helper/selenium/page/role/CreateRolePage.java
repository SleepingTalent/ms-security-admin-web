package com.babcock.test.helper.selenium.page.role;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CreateRolePage extends Page {

    private static final String CREATE_ROLE_URL = "navigate-to-create-role";

    public CreateRolePage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToPage(String host) {
        navigateToUrl(host+ CREATE_ROLE_URL);
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("createRoleContent");
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

    public void selectAssignPermissionCheckBox() {
        getPageHelper().clickOnElement("assignPermissions");
    }

    public class FormData {
        private String name;
        private String description;
        private boolean assignPermission;
        private String permissions = "";

        public FormData(String name, String description, boolean assignPermission) {
            this.name = name;
            this.description = description;
            this.assignPermission = assignPermission;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public boolean isAssignPermission() {
            return assignPermission;
        }

        public void setPermissions(String permissions) {
            this.permissions = permissions;
        }

        public String getPermissions() {
            return permissions;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
