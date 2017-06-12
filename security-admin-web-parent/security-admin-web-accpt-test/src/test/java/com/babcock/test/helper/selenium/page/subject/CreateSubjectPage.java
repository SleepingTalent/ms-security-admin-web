package com.babcock.test.helper.selenium.page.subject;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class CreateSubjectPage extends Page {
    private static final String CREATE_SUBJECT_URL = "navigate-to-create-subject";

    public CreateSubjectPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToPage(String host) {
        navigateToUrl(host+ CREATE_SUBJECT_URL);
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("createSubjectContent");
    }

    public void clickCreateBtn() {
        getPageHelper().findElementById("createBtn").click();
    }

    public void clickResetBtn() {
        getPageHelper().findElementById("resetBtn").click();
    }

    public void assertValidationMessagesDisplayed() {
        assertThat(getPageHelper().findElementById("usernameErrorMsg").getText()).isEqualTo("Username cannot be empty");
        assertThat(getPageHelper().findElementById("passwordErrorMsg").getText()).isEqualTo("Password cannot be empty");
    }

    public void setUsernameField(String username) {
        getPageHelper().findElementById("username").sendKeys(username);
    }

    public void setPasswordField(String password) {
        getPageHelper().findElementById("password").sendKeys(password);
    }

    public void assertUsernameFieldValue(String value) {
        assertThat(getPageHelper().findElementById("username").getText()).isEqualTo(value);
    }

    public void assertPasswordFieldValue(String value) {
        assertThat(getPageHelper().findElementById("password").getText()).isEqualTo(value);
    }

    public void selectAssignRoleCheckBox() {
        getPageHelper().clickOnElement("assignRoles");
    }

    public class FormData {
        private String username;
        private String password;
        private boolean assignRole;
        private String roles;

        public FormData(String username, String password, boolean assignRole) {
            this.username = username;
            this.password = password;
            this.assignRole = assignRole;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public boolean isAssignRole() {
            return assignRole;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public String getRoles() {
            return roles;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
