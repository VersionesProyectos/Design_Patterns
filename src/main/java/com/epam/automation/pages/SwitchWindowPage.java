package com.epam.automation.pages;

import com.epam.automation.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SwitchWindowPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SwitchWindowPage.class);
    private String mainWindowHandle;

    @FindBy(id = "logo")
    private WebElement logoHome;
    @FindBy(css = "a.btn-lg[href='/switch-window']")
    private WebElement switchWindowLink;
    @FindBy(id = "new-tab-button")
    private WebElement openNewTabButton;
    @FindBy(id = "alert-button")
    private WebElement openAlertButton;
    @FindBy(css = ".navbar-brand")
    private WebElement logoFormy;
    @FindBy(css = ".display-3")
    private WebElement mainTitle;

    public SwitchWindowPage() {
        super();
    }

    public void goToSwitchWindowSection() {
        driver.get(System.getProperty("url"));
        waitForElementToBeVisible(switchWindowLink);
        scrollToElement(switchWindowLink);
        click(switchWindowLink);
    }

    public void openNewTabAndSwitch() {
        mainWindowHandle = driver.getWindowHandle();
        click(openNewTabButton);

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    public void closeAndReturnToMain() {
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    public void handleAlert() {
        click(openAlertButton);

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public String getPageTitle() {
        return mainTitle.getText();
    }

    public void clickLogoFormy() {
        click(logoFormy);
    }
}
