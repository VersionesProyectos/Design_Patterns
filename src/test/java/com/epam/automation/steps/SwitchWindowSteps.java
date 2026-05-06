package com.epam.automation.steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.epam.automation.pages.SwitchWindowPage;

public class SwitchWindowSteps {

    private final SwitchWindowPage page = new SwitchWindowPage();

    @Given("I navigate to the Switch Window page")
    public void goToSwitch() {
        page.goToSwitchWindowSection();
    }

    @When("I open a new tab and switch")
    public void openNewTab() {
        page.openNewTabAndSwitch();
    }

    @When("I close the new tab and return")
    public void closeAndReturn() {
        page.closeAndReturnToMain();
    }

    @When("I handle the alert")
    public void handleAlert() {
        page.handleAlert();
    }

    @Then("I should see the main page title equals {string}")
    public void verifyTitle(String expected) {
        Assert.assertEquals(page.getPageTitle(), expected);
    }
}

