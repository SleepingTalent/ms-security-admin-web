package com.babcock.test.helper.selenium.page.subject;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;


public class SubjectsPage extends Page {
    private static final String SUBJECTS_URL = "navigate-to-subjects";

    public SubjectsPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToPage(String host) {
        navigateToUrl(host+ SUBJECTS_URL);
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("subjectsContent");
    }

    public void filterTableBy(String role) {
        filterTable(role);
    }

    public void findRoleInTable(String role) {
        getPageHelper().findTableDataWithText(role);
    }

    public void clickOnEditLink() {
        getPageHelper().clickOnElement("editSubjectLink");
    }
}
