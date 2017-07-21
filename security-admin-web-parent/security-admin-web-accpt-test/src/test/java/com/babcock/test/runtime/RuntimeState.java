package com.babcock.test.runtime;

import com.babcock.test.helper.selenium.PageFactory;
import com.babcock.test.helper.selenium.WebDriverFactory;
import com.babcock.test.helper.selenium.page.permission.CreatePermissionPage;
import com.babcock.test.helper.selenium.page.role.CreateRolePage;
import com.babcock.test.helper.selenium.page.subject.CreateSubjectPage;
import com.babcock.test.helper.selenium.page.user.CreateUserPage;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;
import cucumber.api.Scenario;
import org.springframework.stereotype.Component;

@Component
public class RuntimeState {

    private Scenario scenario;
    private String host;
    private CucumberWebDriver webDriver;
    private PageFactory pageFactory;

    private CreatePermissionPage.FormData permissionFormData;
    private CreateRolePage.FormData roleFormData;
    private CreateUserPage.FormData userFormData;
    private CreateSubjectPage.FormData subjectFormData;
    private String baseUrl;

    private String uniqueKey;

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void initialise() {
        webDriver = WebDriverFactory.getWebDriver();
        pageFactory = new PageFactory(webDriver);
    }

    public PageFactory getPageFactory() {
        return pageFactory;
    }

    public void takeScreenShot() {
        if (webDriver != null) {
            webDriver.takeScreenShot(scenario);
        }
    }

    public void closeBrowser() {
        if (webDriver != null) {
            webDriver.quit();
     //       webDriver.closeBrowser();
        }
    }

    public void setPermissionFormData(CreatePermissionPage.FormData permissionFormData) {
        this.permissionFormData = permissionFormData;
    }

    public CreatePermissionPage.FormData getPermissionFormData() {
        return permissionFormData;
    }


    public void setRoleFormData(CreateRolePage.FormData roleFormData) {
        this.roleFormData = roleFormData;
    }

    public CreateRolePage.FormData getRoleFormData() {
        return roleFormData;
    }

    public void setUserFormData(CreateUserPage.FormData userFormData) {
        this.userFormData = userFormData;
    }

    public CreateUserPage.FormData getUserFormData() {
        return userFormData;
    }

    public void setSubjectFormData(CreateSubjectPage.FormData subjectFormData) {
        this.subjectFormData = subjectFormData;
    }

    public CreateSubjectPage.FormData getSubjectFormData() {
        return subjectFormData;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
