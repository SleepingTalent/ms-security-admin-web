package com.babcock.test.helper.selenium.page.subject;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class EditSubjectPage extends Page {

    public EditSubjectPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void navigateToPage(String url) {
        //Cant Navigate to Page Directly
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("editSubjectContent");
    }

    public void clickUpdateBtn() {
        getPageHelper().findElementById("updateBtn").click();
    }

    public void setUsernameField(String username) {
        getPageHelper().findElementById("username").clear();
        getPageHelper().findElementById("username").sendKeys(username);
    }

    public void assignRole(String role) {
        getPageHelper().selectDualListElement(role);
    }

    public void assertRoleAssigned(String role) {
        WebElement option = getPageHelper().findOptionWithText(role);
        assertThat(option.getAttribute("outerHTML")).contains("selected=\"selected\"");
    }

    public class FormData {
        private String role;
        private String username;

        public FormData(String username, String role) {
            this.username = username;
            this.role = role;
        }

        public String getRole() {
            return role;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
