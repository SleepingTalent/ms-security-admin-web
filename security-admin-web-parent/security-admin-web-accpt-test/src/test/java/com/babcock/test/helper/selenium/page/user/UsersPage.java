package com.babcock.test.helper.selenium.page.user;

import com.babcock.test.helper.selenium.page.Page;
import com.babcock.test.helper.selenium.webdriver.CucumberWebDriver;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UsersPage extends Page {

    private static final String USERS_URL = "navigate-to-users";

    public UsersPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToPage(String host) {
        navigateToUrl(host+ USERS_URL);
    }

    @Override
    public void assertPageIsDisplayed() {
        getPageHelper().findElementById("usersContent");
    }

    public void filterTableBy(String user) {
        filterTable(user);
    }

    public void findUserInTable(String user) {
        getPageHelper().findTableDataWithText(user);
    }

}
