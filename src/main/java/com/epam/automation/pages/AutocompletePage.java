package com.epam.automation.pages;

import com.epam.automation.base.BasePage;
import com.epam.automation.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutocompletePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(AutocompletePage.class);

    @FindBy(css = ".btn.btn-lg")
    private WebElement autocompleteLink;

    @FindBy(className = "dismissButton")
    private WebElement okButton;

    @FindBy(id = "autocomplete")
    private WebElement addressField;

    @FindBy(id = "street_number")
    private WebElement streetAddressField;

    @FindBy(id = "route")
    private WebElement streetAddressDosField;

    @FindBy(id = "locality")
    private WebElement cityField;

    @FindBy(id = "administrative_area_level_1")
    private WebElement stateField;

    @FindBy(id = "postal_code")
    private WebElement postalCodeField;

    @FindBy(id = "country")
    private WebElement countryField;

    public AutocompletePage() {
        super();
    }

    public void clickAutocompleteLink() {
        click(autocompleteLink);
        waitForElementToBeVisible(addressField);
    }


    public void fillAddressDetails(Address address) {
        refreshPageElements();
        enterAddress(address.getFullAddress());
        dismissGoogleAlert();

        enterStreetAddress(address.getStreet());
        enterStreetAddressDos(address.getStreet2());
        enterCity(address.getCity());
        enterState(address.getState());
        enterPostalCode(address.getZipCode());
        enterCountry(address.getCountry());

    }

    public void dismissGoogleAlert() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement button = shortWait.until(ExpectedConditions.elementToBeClickable(okButton));
            button.click();
            logger.info("Modal de Google Maps cerrado.");
        } catch (Exception e) {
            logger.warn("El modal de Google no apareció, continuando con el flujo.");
        }
    }

    public void enterAddress(String address) {
        scrollToElement(addressField);
        sendText(addressField, address);
    }
    public void closeGoogleErrorIfPresent() {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dismissButton")));
            okButton.click();
            System.out.println("Modal de Google Maps cerrado.");
        } catch (Exception e) {
        }
    }
    public void enterStreetAddress(String streetAddress) {
        sendText(streetAddressField, streetAddress);
    }

    public void enterStreetAddressDos(String streetAddressDos) {
        sendText(streetAddressDosField, streetAddressDos);
    }

    public void enterCity(String city) {
        sendText(cityField, city);
    }


    public void enterState(String state) {
        sendText(stateField, state);
    }

    public void enterPostalCode(String postalCode) {
        sendText(postalCodeField, postalCode);
    }

    public void enterCountry(String country) {
        sendText(countryField, country);
    }

    public String getCityValue() {
        return cityField.getAttribute("value");
    }

    public String getStateValue() {
        return stateField.getAttribute("value");
    }

    public String getCountryValue() {
        return countryField.getAttribute("value");
    }
}