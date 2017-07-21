package com.babcock.test.steps;

import com.babcock.test.helper.rest.RestHelper;
import com.babcock.test.helper.selenium.page.role.AssignRolePermissionsPage;
import com.babcock.test.helper.selenium.page.role.CreateRolePage;
import com.babcock.test.helper.selenium.page.role.EditRolePage;
import com.babcock.test.runtime.RuntimeState;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RoleSteps extends AbstractStep {

    protected Logger logger = LoggerFactory.getLogger(RoleSteps.class);

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    RestHelper restHelper;

    @And("^the createRole form is submitted$")
    public void theCreateRoleFormIsSubmitted() throws Throwable {
        runtimeState.getPageFactory().getCreateRolePage().clickCreateBtn();

    }

    @And("^the createRole empty form is submitted$")
    public void theCreateRoleEmptyFormIsSubmitted() throws Throwable {
        runtimeState.getPageFactory().getCreateRolePage().clickCreateBtn();
    }

    @And("^the createRole form is reset$")
    public void theCreateRoleFormIsReset() throws Throwable {
        runtimeState.getPageFactory().getCreateRolePage().clickResetBtn();
    }

    @Then("^the createRole form fields are empty$")
    public void theCreateRoleFormFieldsAreEmpty() throws Throwable {
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getCreateRolePage().assertNameFieldValue("");
        runtimeState.getPageFactory().getCreateRolePage().assertDescriptionFieldValue("");
    }

    @Then("^the role \"([^\"]*)\" is displayed in the role table$")
    public void theRoleIsDisplayedInTheRoleTable(String role) throws Throwable {
        runtimeState.getPageFactory().getRolesPage().assertPageIsDisplayed();
        runtimeState.getPageFactory().getRolesPage().filterTableBy(role+"-"+runtimeState.getUniqueKey());
        runtimeState.getPageFactory().getRolesPage().findRoleInTable(role+"-"+runtimeState.getUniqueKey());
        runtimeState.takeScreenShot();
    }

    @And("^click on the edit link for the role \"([^\"]*)\"$")
    public void editTheRoleIsDisplayedInTheRoleTable(String role) throws Throwable {
        runtimeState.getPageFactory().getRolesPage().assertPageIsDisplayed();
        runtimeState.getPageFactory().getRolesPage().filterTableBy(role);
        runtimeState.getPageFactory().getRolesPage().findRoleInTable(role);
        runtimeState.takeScreenShot();

        runtimeState.getPageFactory().getRolesPage().clickOnEditLink();
    }

    @When("^the createRole form details are empty$")
    public void theCreateRoleFormDetailsAreEmpty() throws Throwable {
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getCreateRolePage().assertNameFieldValue("");
        runtimeState.getPageFactory().getCreateRolePage().assertDescriptionFieldValue("");
    }

    @Then("^the createRole form validation messages are displayed$")
    public void theCreateRoleFormValidationMessagesAreDisplayed() throws Throwable {
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getCreateRolePage().assertValidationMessagesDisplayed();
    }

    @When("^the createRole form data is set to$")
    public void theCreateRolenFormDataIsSetTo(List<CreateRolePage.FormData> formData) throws Throwable {
        CreateRolePage.FormData roleFormData = formData.get(0);

        runtimeState.getPageFactory().getCreateRolePage().setNameField(roleFormData.getName()+"-"+runtimeState.getUniqueKey());
        runtimeState.getPageFactory().getCreateRolePage().setDescriptionField(roleFormData.getDescription());

        if(roleFormData.isAssignPermission()) {
            runtimeState.getPageFactory().getCreateRolePage().selectAssignPermissionCheckBox();
        }

        runtimeState.setRoleFormData(roleFormData);
        runtimeState.takeScreenShot();
    }


    @When("^the assignPermission form is submitted$")
    public void theAssignPermissionFormIsSubmitted() throws Throwable {
        String name = runtimeState.getRoleFormData().getName();
        String description = runtimeState.getRoleFormData().getDescription();
        String permissions = runtimeState.getRoleFormData().getPermissions();

        runtimeState.getPageFactory().getAssignRolePermissionsPage().clickAssignBtn();
    }

    @And("^the following permissions are assigned$")
    public void theFollowingPermissionsAreAssigned(List<AssignRolePermissionsPage.FormData> formData) throws Throwable {

        List<String> assignedPermissions = new ArrayList<>();

        for(AssignRolePermissionsPage.FormData permissionFormData : formData) {
            runtimeState.getPageFactory().getAssignRolePermissionsPage().assignPermission(permissionFormData.getPermission());
            logger.info("Assigned Permission :"+permissionFormData.getPermission());

            runtimeState.takeScreenShot();
        }

        runtimeState.getRoleFormData().setPermissions(String.join(",",assignedPermissions));

        runtimeState.takeScreenShot();
    }

    @And("^the following additional permissions are assigned$")
    public void theFollowingAdditionalPermissionsAreAssigned(List<EditRolePage.FormData> formData) throws Throwable {

        List<String> assignedPermissions = new ArrayList<>();

        for(EditRolePage.FormData roleFormData : formData) {
            runtimeState.getPageFactory().getEditRolePage().assignPermission(roleFormData.getPermission());

            runtimeState.takeScreenShot();
        }

        String assignedPermissionsString;

        if(StringUtils.isNotBlank(runtimeState.getRoleFormData().getPermissions())) {
            assignedPermissionsString = String.join(",", assignedPermissions)+","+runtimeState.getRoleFormData().getPermissions();
        }else {
            assignedPermissionsString = String.join(",", assignedPermissions);
        }

        runtimeState.getRoleFormData().setPermissions(assignedPermissionsString);
        runtimeState.takeScreenShot();
    }


    @And("^role details is edited to$")
    public void roleDetailsIsEditedTo(List<EditRolePage.FormData> formData) throws Throwable {

        for(EditRolePage.FormData roleFormData : formData) {
            runtimeState.getPageFactory().getEditRolePage().setNameField(roleFormData.getName());
            runtimeState.getPageFactory().getEditRolePage().setDescriptionField(roleFormData.getDescription());

            runtimeState.getRoleFormData().setName(roleFormData.getName());
            runtimeState.getRoleFormData().setDescription(roleFormData.getDescription());
            runtimeState.takeScreenShot();
        }

        runtimeState.takeScreenShot();
    }

    @When("^the editRole form is submitted$")
    public void theEditRoleFormIsSubmitted() throws Throwable {
        String name = runtimeState.getRoleFormData().getName();
        String description = runtimeState.getRoleFormData().getDescription();
        String permissions = runtimeState.getRoleFormData().getPermissions();

        runtimeState.getPageFactory().getEditRolePage().clickUpdateBtn();
    }

    @And("^role is displayed with the assigned permissions$")
    public void roleIsDisplayedWithTheFollowingAssignedPermissions(List<EditRolePage.FormData> formData) throws Throwable {
        runtimeState.getPageFactory().getEditRolePage().assertPageIsDisplayed();
        runtimeState.takeScreenShot();

        for(EditRolePage.FormData permissionFormData : formData) {
            runtimeState.getPageFactory().getEditRolePage().assertPermissionAssigned(permissionFormData.getPermission());
        }
    }

}

