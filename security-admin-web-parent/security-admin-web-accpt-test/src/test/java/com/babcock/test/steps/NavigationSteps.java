package com.babcock.test.steps;

import com.babcock.test.helper.rest.RestHelper;
import com.babcock.test.runtime.RuntimeState;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class NavigationSteps extends AbstractStep {

    @Autowired
    RuntimeState runtimeState;

    @Autowired
    RestHelper restHelper;

    @Given("^a user is on the \"([^\"]*)\" page$")
    public void aUserIsOnThePage(String pageName) throws Throwable {
        runtimeState.getPageFactory().getPage(pageName).navigateToPage(runtimeState.getBaseUrl());

        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getPage(pageName).assertPageIsDisplayed();
    }

    @When("^the \"([^\"]*)\" navigation link is clicked$")
    public void theNavigationLinkIsClicked(String linkName) throws Throwable {
        runtimeState.getPageFactory().getNavigationPanel().clickLink(linkName);
    }

    @Then("^the \"([^\"]*)\" page is displayed$")
    public void thePageIsDisplayed(String pageName) throws Throwable {
        runtimeState.takeScreenShot();
        runtimeState.getPageFactory().getPage(pageName).assertPageIsDisplayed();
    }

    @When("^the \"([^\"]*)\" dashboard link is clicked$")
    public void theDashboardLinkIsClicked(String dashBoardLink) throws Throwable {
       runtimeState.getPageFactory().getDashBoardPage().clickLink(dashBoardLink);
    }
}

