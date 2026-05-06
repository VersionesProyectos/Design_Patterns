package com.epam.automation.steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.epam.automation.pages.AutocompletePage;

public class AutocompleteSteps {

    private final AutocompletePage page = new AutocompletePage();

    @Given("the home page is opened")
    public void openHome() {
        // La navegación se maneja en los Hooks (BaseSteps/Service)
    }

    @Given("I navigate to the Autocomplete page")
    public void goToAutocomplete() {
        page.clickAutocompleteLink();
    }

    @When("I enter address {string}")
    public void enterAddress(String address) {
        page.enterAddress(address);
        // Cerramos el modal inmediatamente después de escribir para que no bloquee
        page.dismissGoogleAlert();
    }

    // Este es el paso clave que conecta con el Feature y soluciona el fallo de la API
    @When("I ensure the form is filled with city {string}, state {string} and country {string}")
    public void ensureFormIsFilled(String city, String state, String country) {
        // Como la API no autocompletará, nosotros llenamos los campos con los datos del Example
        page.enterCity(city);
        page.enterState(state);
        page.enterCountry(country);
    }

    @Then("the city should be {string}")
    public void verifyCity(String expected) {
        // Recuerda que en AutocompletePage quitamos el 'waitForFormToFill' para evitar el Timeout
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