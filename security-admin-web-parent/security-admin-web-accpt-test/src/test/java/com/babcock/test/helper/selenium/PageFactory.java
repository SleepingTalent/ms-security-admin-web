package com.babcock.test.helper.selenium;

import com.babcock.test.helper.selenium.page.*;
import com.babcock.test.helper.selenium.page.permission.CreatePermissionPage;
import com.babcock.test.helper.selenium.page.permission.PermissionsPage;
import com.babcock.test.helper.selenium.page.role.AssignRolePermissionsPage;
import com.babcock.test.helper.selenium.page.role.CreateRolePage;
import com.babcock.test.helper.selenium.page.role.EditRolePage;
import com.babcock.test.helper.selenium.page.role.RolesPage;
import com.babcock.test.helper.selenium.page.subject.AssignSubjectRolesPage;
import com.babcock.test.helper.selenium.page.subject.CreateSubjectPage;
import com.babcock.test.helper.selenium.page.subject.EditSubjectPage;
import com.babcock.test.helper.selenium.page.subject.SubjectsPage;
import com.babcock.test.helper.selenium.page.user.CreateUserPage;
import com.babcock.test.helper.selenium.page.user.UsersPage;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;

public class PageFactory {

    private CucumberWebDriver webDriver;

    public PageFactory(CucumberWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public DashboardPage getDashBoardPage() {
        return new DashboardPage(webDriver);
    }

    public UsersPage getUsersPage() {
        return new UsersPage(webDriver);
    }

    public CreateUserPage getCreateUserPage() {
        return new CreateUserPage(webDriver);
    }

    public CreateSubjectPage getCreateSubjectPage() {
        return new CreateSubjectPage(webDriver);
    }

    public SubjectsPage getSubjectsPage() {
        return new SubjectsPage(webDriver);
    }

    public RolesPage getRolesPage() {
        return new RolesPage(webDriver);
    }

    public CreateRolePage getCreateRolePage() {
        return new CreateRolePage(webDriver);
    }

    public AssignRolePermissionsPage getAssignRolePermissionsPage() {
        return new AssignRolePermissionsPage(webDriver);
    }

    public EditRolePage getEditRolePage() {
        return new EditRolePage(webDriver);
    }

    public PermissionsPage getPermissionsPage() {
        return new PermissionsPage(webDriver);
    }

    public CreatePermissionPage getCreatePermissionPage() {
        return new CreatePermissionPage(webDriver);
    }

    public NavigationPanel getNavigationPanel() {
        return new NavigationPanel(webDriver);
    }

    public AssignSubjectRolesPage getAssignSubjectRolesPage() {
        return new AssignSubjectRolesPage(webDriver);
    }

    public EditSubjectPage getEditSubjectPage() {
        return new EditSubjectPage(webDriver);
    }

    public Page getPage(String pageName) throws Exception {
        switch (pageName) {
            case "dashboard":
                return getDashBoardPage();
            case "users":
                return getUsersPage();
            case "createUser":
                return getCreateUserPage();
            case "subjects":
                 return getSubjectsPage();
            case "createSubject":
                return getCreateSubjectPage();
            case "roles":
               return getRolesPage();
            case "createRole":
                return getCreateRolePage();
            case "permissions":
                return getPermissionsPage();
            case "createPermission":
                return getCreatePermissionPage();
            default:
                throw new Exception("Unsupported Page : " + pageName);
        }
    }
}
