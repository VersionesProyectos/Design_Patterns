package com.epam.automation.steps;

import io.cucumber.java.en.*;
import org.testng.Assert;
import com.epam.automation.pages.CompleteWebFormPage;
import com.epam.automation.model.User;

public class CompleteFormSteps {

    private final CompleteWebFormPage page = new CompleteWebFormPage();

    @Given("I navigate to the Complete Web Form page")
    public void goToForm() {
        page.clickLinkForm();
    }

    @When("I fill the form with firstName {string} lastName {string} title {string} date {string}")
    public void fillForm(String first, String last, String title, String date) {
        User user = new User.UserBuilder()
                .firstName(first)
                .lastName(last)
                .jobTitle(title)
                .date(date)
                .build();
        page.fillForm(user);
    }

    @When("I submit the form")
    public void submit() {
        page.clickSubmit();
    }

    @Then("I should see success message {string}")
    public void verifySuccess(String expected) {
        Assert.assertEquals(page.getAlertText(), expected);
    }
}
