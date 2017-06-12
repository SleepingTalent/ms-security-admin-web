package com.babcock.test.helper.selenium.page.subject;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AssignSubjectRolesPage extends Page {

    public AssignSubjectRolesPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void navigateToPage(String url) {
        //Cant Navigate to Page Directly
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("assignRoleContent");
    }

    public void clickAssignBtn() {
        getPageHelper().findElementById("assignBtn").click();
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

        public FormData(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }
}
