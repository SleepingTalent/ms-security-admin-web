package com.babcock.test.helper.selenium.page.user;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CreateUserPage extends Page {

    private static final String CREATE_USER_URL = "navigate-to-create-user";

    public CreateUserPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToPage(String host) {
        navigateToUrl(host+ CREATE_USER_URL);
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("createUserContent");
    }

    public void clickCreateBtn() {
        getPageHelper().findElementById("createBtn").click();
    }

    public void clickResetBtn() {
        getPageHelper().findElementById("resetBtn").click();
    }

    public void assertValidationMessagesDisplayed() {
        assertThat(getPageHelper().findElementById("payNumberErrorMsg").getText()).isEqualTo("Pay Number cannot be empty");
        assertThat(getPageHelper().findElementById("nameErrorMsg").getText()).isEqualTo("Name cannot be empty");
        assertThat(getPageHelper().findElementById("emailErrorMsg").getText()).isEqualTo("Email cannot be empty");
        assertThat(getPageHelper().findElementById("jobTitleErrorMsg").getText()).isEqualTo("Job Title cannot be empty");
        assertThat(getPageHelper().findElementById("departmentErrorMsg").getText()).isEqualTo("Department cannot be empty");
        assertThat(getPageHelper().findElementById("locationErrorMsg").getText()).isEqualTo("Location cannot be empty");
    }

    public void assertPayNumberFieldValue(String value) {
        assertThat(getPageHelper().findElementById("payNumber").getText()).isEqualTo(value);
    }

    public void assertNameFieldValue(String value) {
        assertThat(getPageHelper().findElementById("name").getText()).isEqualTo(value);
    }

    public void assertEmailFieldValue(String value) {
        assertThat(getPageHelper().findElementById("email").getText()).isEqualTo(value);
    }

    public void assertjobTitleFieldValue(String value) {
        assertThat(getPageHelper().findElementById("jobTitle").getText()).isEqualTo(value);
    }

    public void assertDepartmentFieldValue(String value) {
        assertThat(getPageHelper().findElementById("department").getText()).isEqualTo(value);
    }

    public void assertLocationFieldValue(String value) {
        assertThat(getPageHelper().findElementById("location").getText()).isEqualTo(value);
    }

    public void setPayNumberField(String payNumber) {
        getPageHelper().findElementById("payNumber").sendKeys(payNumber);
    }

    public void setNameField(String name) {
        getPageHelper().findElementById("name").sendKeys(name);
    }

    public void setEmailField(String email) {
        getPageHelper().findElementById("email").sendKeys(email);
    }

    public void setJobTitleField(String jobTitle) {
        getPageHelper().findElementById("jobTitle").sendKeys(jobTitle);
    }

    public void setDepartmentField(String department) {
        getPageHelper().findElementById("department").sendKeys(department);
    }

    public void setLocationField(String location) {
        getPageHelper().findElementById("location").sendKeys(location);
    }


    public class FormData {
        private String payNumber;
        private String name;
        private String email;
        private String jobTitle;
        private String department;
        private String location;


        public FormData(String payNumber, String name, String email, String jobTitle, String department, String location) {
            this.payNumber = payNumber;
            this.name = name;
            this.email = email;
            this.jobTitle = jobTitle;
            this.department = department;
            this.location = location;
        }

        public String getPayNumber() {
            return payNumber;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public String getDepartment() {
            return department;
        }

        public String getLocation() {
            return location;
        }
    }
}
