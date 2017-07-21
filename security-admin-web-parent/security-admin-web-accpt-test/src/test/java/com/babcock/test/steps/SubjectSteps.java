package com.babcock.test.steps;

import com.babcock.test.helper.rest.RestHelper;
import com.babcock.test.helper.selenium.page.subject.AssignSubjectRolesPage;
import com.babcock.test.helper.selenium.page.subject.CreateSubjectPage;
import com.babcock.test.helper.selenium.page.subject.EditSubjectPage;
import com.babcock.test.runtime.RuntimeState;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SubjectSteps extends AbstractStep {

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    RestHelper restHelper;

    @Then("^the createSubject form validation messages are displayed$")
    public void theCreateSubjectFormValidationMessagesAreDisplayed() throws Throwable {
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getCreateSubjectPage().assertValidationMessagesDisplayed();
    }

    @And("^the createSubject form is submitted$")
    public void theCreateSubjectFormIsSubmitted() throws Throwable {
        String username = runtimeState.getSubjectFormData().getUsername();
        String password = runtimeState.getSubjectFormData().getPassword();

        runtimeState.getPageFactory().getCreateSubjectPage().clickCreateBtn();
    }

    @And("^the createSubject form is reset$")
    public void theCreateSubjectFormIsReset() throws Throwable {
        runtimeState.getPageFactory().getCreateSubjectPage().clickResetBtn();
    }

    @When("^the createSubject form details are empty$")
    public void theCreateSubjectFormDetailsAreEmpty() throws Throwable {
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getCreateSubjectPage().setUsernameField("");
        runtimeState.getPageFactory().getCreateSubjectPage().setPasswordField("");
    }

    @Then("^the createSubject form fields are empty$")
    public void theCreateSubjectFormFieldsAreEmpty() throws Throwable {
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getCreateSubjectPage().assertUsernameFieldValue("");
        runtimeState.getPageFactory().getCreateSubjectPage().assertPasswordFieldValue("");
    }

    @When("^the createSubject form data is set to$")
    public void theCreateSubjectFormDataIsSetTo(List<CreateSubjectPage.FormData> formData) throws Throwable {
        CreateSubjectPage.FormData subjectFormData = formData.get(0);
        runtimeState.getPageFactory().getCreateSubjectPage().setUsernameField(subjectFormData.getUsername()+"-"+runtimeState.getUniqueKey());
        runtimeState.getPageFactory().getCreateSubjectPage().setPasswordField(subjectFormData.getPassword());

        if(subjectFormData.isAssignRole()) {
            runtimeState.getPageFactory().getCreateSubjectPage().selectAssignRoleCheckBox();
        }

        runtimeState.setSubjectFormData(subjectFormData);
        runtimeState.takeScreenShot();
    }

    @Then("^the subject \"([^\"]*)\" is displayed in the subject table$")
    public void theSubjectIsDisplayedInTheSubjectTable(String subject) throws Throwable {
        runtimeState.getPageFactory().getSubjectsPage().assertPageIsDisplayed();
        runtimeState.getPageFactory().getSubjectsPage().filterTableBy(subject+"-"+runtimeState.getUniqueKey());
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getSubjectsPage().findRoleInTable(subject+"-"+runtimeState.getUniqueKey());
    }


    @And("^the following roles are assigned$")
    public void theFollowingRolesAreAssigned(List<AssignSubjectRolesPage.FormData> formData) throws Throwable {

        List<String> assignedRoles = new ArrayList<>();

        for(AssignSubjectRolesPage.FormData roleFormData : formData) {
            runtimeState.getPageFactory().getAssignSubjectRolesPage().assignRole(roleFormData.getRole());
            runtimeState.takeScreenShot();
        }

        runtimeState.getSubjectFormData().setRoles(String.join(",",assignedRoles));
        runtimeState.takeScreenShot();
    }


    @And("^the following additional roles are assigned$")
    public void theFollowingAdditionalRolesAreAssigned(List<EditSubjectPage.FormData> formData) throws Throwable {

        List<String> assignedRoles = new ArrayList<>();

        for(EditSubjectPage.FormData roleFormData : formData) {
            runtimeState.getPageFactory().getEditSubjectPage().assignRole(roleFormData.getRole());
            runtimeState.takeScreenShot();
        }

        String assignedRolesString;

        if(StringUtils.isNotBlank(runtimeState.getSubjectFormData().getRoles())) {
            assignedRolesString = runtimeState.getSubjectFormData().getRoles()+","+String.join(",", assignedRoles);
        }else {
            assignedRolesString = String.join(",", assignedRoles);
        }

        runtimeState.getSubjectFormData().setRoles(assignedRolesString);

        runtimeState.takeScreenShot();
    }

    @When("^the assignRole form is submitted$")
    public void theAssignRoleFormIsSubmitted() throws Throwable {
        String username = runtimeState.getSubjectFormData().getUsername();
        String password = runtimeState.getSubjectFormData().getPassword();
        String roles = runtimeState.getSubjectFormData().getRoles();

        runtimeState.getPageFactory().getAssignSubjectRolesPage().clickAssignBtn();
    }

    @And("^subject is displayed with the assigned roles$")
    public void subjectIsDisplayedWithTheAssignedRoles(List<EditSubjectPage.FormData> formData) throws Throwable {
        runtimeState.getPageFactory().getEditSubjectPage().assertPageIsDisplayed();
        runtimeState.takeScreenShot();

        for (EditSubjectPage.FormData roleFormData : formData) {
            runtimeState.getPageFactory().getEditSubjectPage().assertRoleAssigned(roleFormData.getRole());
        }
    }

    @And("^click on the edit link for the subject \"([^\"]*)\"$")
    public void editTheSubjectIsDisplayedInTheSubjectTable(String subject) throws Throwable {
        runtimeState.getPageFactory().getSubjectsPage().assertPageIsDisplayed();
        runtimeState.getPageFactory().getSubjectsPage().filterTableBy(subject);
        runtimeState.getPageFactory().getSubjectsPage().findRoleInTable(subject);
        runtimeState.takeScreenShot();

        runtimeState.getPageFactory().getSubjectsPage().clickOnEditLink();
    }

    @Then("^the editSubject form is submitted$")
    public void theEditSubjectFormIsSubmitted() throws Throwable {
        String username = runtimeState.getSubjectFormData().getUsername();
        String password = runtimeState.getSubjectFormData().getPassword();
        String roles = runtimeState.getSubjectFormData().getRoles();

        runtimeState.getPageFactory().getEditSubjectPage().clickUpdateBtn();
    }

    @And("^subject details is edited to$")
    public void subjectDetailsIsEditedTo(List<EditSubjectPage.FormData> formData) throws Throwable {
        runtimeState.getPageFactory().getEditSubjectPage().assertPageIsDisplayed();
        runtimeState.takeScreenShot();

        for (EditSubjectPage.FormData roleFormData : formData) {
            runtimeState.getPageFactory().getEditSubjectPage().setUsernameField(roleFormData.getUsername());
            runtimeState.getSubjectFormData().setUsername(roleFormData.getUsername());
        }
    }

    @And("^the createSubject empty form is submitted$")
    public void theCreateSubjectEmptyFormIsSubmitted() throws Throwable {
        runtimeState.getPageFactory().getCreateSubjectPage().clickCreateBtn();
    }
}

