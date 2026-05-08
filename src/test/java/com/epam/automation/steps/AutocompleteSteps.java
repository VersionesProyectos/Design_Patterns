package com.epam.automation.steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.epam.automation.pages.AutocompletePage;

public class AutocompleteSteps {

    private final AutocompletePage page = new AutocompletePage();

    @Given("the home page is opened")
    public void openHome() {}

    @Given("I navigate to the Autocomplete page")
    public void goToAutocomplete() {
        page.clickAutocompleteLink();
    }

    @When("I enter address {string}")
    public void enterAddress(String address) {
        page.enterAddress(address);
        page.dismissGoogleAlert();
    }

    @When("I ensure the form is filled with city {string}, state {string} and country {string}")
    public void ensureFormIsFilled(String city, String state, String country) {
        page.enterCity(city);
        page.enterState(state);
        page.enterCountry(country);
    }

    @Then("the city should be {string}")
    public void verifyCity(String expected) {
        Assert.assertEquals(page.getCityValue(), expected);
    }

    @Then("the state should be {string}")
    public void verifyState(String expected) {
        Assert.assertEquals(page.getStateValue(), expected);
    }

    @Then("the country should be {string}")
    public void verifyCountry(String expected) {
        Assert.assertEquals(page.getCountryValue(), expected);
    }
}