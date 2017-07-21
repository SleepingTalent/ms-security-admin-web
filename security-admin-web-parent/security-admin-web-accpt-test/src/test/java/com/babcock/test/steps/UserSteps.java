package com.babcock.test.steps;

import com.babcock.test.helper.rest.RestHelper;
import com.babcock.test.helper.selenium.page.user.CreateUserPage;
import com.babcock.test.runtime.RuntimeState;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserSteps extends AbstractStep {

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    RestHelper restHelper;


    @And("^the createUser form is submitted$")
    public void theCreateUserFormIsSubmitted() throws Throwable {
        runtimeState.getPageFactory().getCreateUserPage().clickCreateBtn();
    }

    @And("^the createUser form is reset$")
    public void theCreateUserFormIsReset() throws Throwable {
        runtimeState.getPageFactory().getCreateUserPage().clickResetBtn();
    }

    @Then("^the user \"([^\"]*)\" is displayed in the users table$")
    public void theUserIsDisplayedInTheUsersTable(String user) throws Throwable {
        runtimeState.getPageFactory().getUsersPage().filterTableBy(user+"-"+runtimeState.getUniqueKey());
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getUsersPage().findUserInTable(user+"-"+runtimeState.getUniqueKey());
    }

    @Then("^the createUser form validation messages are displayed$")
    public void theCreateUserFormValidationMessagesAreDisplayed() throws Throwable {
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getCreateUserPage().assertValidationMessagesDisplayed();
    }

    @Then("^the createUser form fields are empty$")
    public void theCreateUserFormFieldsAreEmpty() throws Throwable {
        runtimeState.getPageFactory().getCreateUserPage().assertPayNumberFieldValue("");
        runtimeState.getPageFactory().getCreateUserPage().assertNameFieldValue("");
        runtimeState.getPageFactory().getCreateUserPage().assertEmailFieldValue("");
        runtimeState.getPageFactory().getCreateUserPage().assertjobTitleFieldValue("");
        runtimeState.getPageFactory().getCreateUserPage().assertDepartmentFieldValue("");
        runtimeState.getPageFactory().getCreateUserPage().assertLocationFieldValue("");
    }

    @When("^the createUser form details name is set to$")
    public void theCreateUserFormDetailsNameIsSetTo(List<CreateUserPage.FormData> formData) throws Throwable {
        CreateUserPage.FormData userFormData = formData.get(0);

        runtimeState.getPageFactory().getCreateUserPage().setPayNumberField(userFormData.getPayNumber()+"-"+runtimeState.getUniqueKey());
        runtimeState.getPageFactory().getCreateUserPage().setNameField(userFormData.getName()+"-"+runtimeState.getUniqueKey());
        runtimeState.getPageFactory().getCreateUserPage().setEmailField(runtimeState.getUniqueKey()+"-"+userFormData.getEmail());
        runtimeState.getPageFactory().getCreateUserPage().setJobTitleField(userFormData.getJobTitle());
        runtimeState.getPageFactory().getCreateUserPage().setDepartmentField(userFormData.getDepartment());
        runtimeState.getPageFactory().getCreateUserPage().setLocationField(userFormData.getLocation());

        runtimeState.setUserFormData(userFormData);
        runtimeState.takeScreenShot();
    }

    @When("^the createUser form details are empty$")
    public void theCreateUserFormDetailsAreEmpty() throws Throwable {
        runtimeState.getPageFactory().getCreateUserPage().setPayNumberField("");
        runtimeState.getPageFactory().getCreateUserPage().setNameField("");
        runtimeState.getPageFactory().getCreateUserPage().setEmailField("");
        runtimeState.getPageFactory().getCreateUserPage().setJobTitleField("");
        runtimeState.getPageFactory().getCreateUserPage().setDepartmentField("");
        runtimeState.getPageFactory().getCreateUserPage().setLocationField("");
    }

    @And("^the createUser empty form is submitted$")
    public void theCreateUserEmptyFormIsSubmitted() throws Throwable {
        runtimeState.getPageFactory().getCreateUserPage().clickCreateBtn();
    }
}

