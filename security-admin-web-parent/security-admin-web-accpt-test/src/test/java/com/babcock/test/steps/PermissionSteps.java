package com.babcock.test.steps;

import com.babcock.test.helper.rest.RestHelper;
import com.babcock.test.helper.selenium.page.permission.CreatePermissionPage;
import com.babcock.test.runtime.RuntimeState;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PermissionSteps extends AbstractStep {

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    RestHelper restHelper;


    @When("^the createPermission form is submitted$")
    public void theCreatePermissionFormIsSubmitted() throws Throwable {
        runtimeState.getPageFactory().getCreatePermissionPage().clickCreateBtn();
    }

    @And("^the createPermission empty form is submitted$")
    public void theCreatePermissionEmptyFormIsSubmitted() throws Throwable {
        runtimeState.getPageFactory().getCreatePermissionPage().clickCreateBtn();
    }

    @Then("^the createPermission form validation messages are displayed$")
    public void theCreatePermissionFormValidationMessagesAreDisplayed() throws Throwable {
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getCreatePermissionPage().assertValidationMessagesDisplayed();
    }

    @When("^the createPermission form is reset$")
    public void theCreatePermissionFormIsReset() throws Throwable {
        runtimeState.getPageFactory().getCreatePermissionPage().clickResetBtn();
    }

    @When("^the createPermission form details are empty$")
    public void theCreatePermissionFormDetailsAreEmpty() throws Throwable {
        runtimeState.getPageFactory().getCreatePermissionPage().setNameField("");
        runtimeState.getPageFactory().getCreatePermissionPage().setDescriptionField("");
        runtimeState.takeScreenShot();
    }

    @When("^the createPermission form data is set to$")
    public void theCreatePermissionFormDataIsSetTo(List<CreatePermissionPage.FormData> formData) throws Throwable {
        CreatePermissionPage.FormData permissionFormData = formData.get(0);
        runtimeState.getPageFactory().getCreatePermissionPage().setNameField(permissionFormData.getName()+"-"+runtimeState.getUniqueKey());
        runtimeState.getPageFactory().getCreatePermissionPage().setDescriptionField(permissionFormData.getDescription());

        runtimeState.setPermissionFormData(permissionFormData);
        runtimeState.takeScreenShot();
    }

    @Then("^the createPermission form fields are empty$")
    public void theCreatePermissionFormFieldsAreEmpty() throws Throwable {
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getCreatePermissionPage().assertNameFieldValue("");
        runtimeState.getPageFactory().getCreatePermissionPage().assertDescriptionFieldValue("");
    }

    @Then("^the permission \"([^\"]*)\" is displayed in the permissions table$")
    public void thePermissionIsDisplayedInThePermissionsTable(String permission) throws Throwable {
        runtimeState.getPageFactory().getPermissionsPage().filterTableBy(permission+"-"+runtimeState.getUniqueKey());
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getPermissionsPage().findPermissionInTable(permission+"-"+runtimeState.getUniqueKey());
    }
}

