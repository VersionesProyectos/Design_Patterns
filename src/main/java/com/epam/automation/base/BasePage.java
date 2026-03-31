package com.epam.automation.base;

import com.epam.automation.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage() {

        this.driver = DriverManager.getInstance().getDriver(System.getProperty("browser", "chrome"));

        int timeout = Integer.parseInt(System.getProperty("timeout", "10"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        PageFactory.initElements(driver, this);
    }

    protected void highlightElement(WebElement element) {
        try {
            String originalStyle = element.getAttribute("style");
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);

            Thread.sleep(200);

            js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
        } catch (Exception e) {
            logger.warn("No se pudo resaltar el elemento: " + e.getMessage());
        }
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));

        highlightElement(element);
        element.click();
    }

    protected void sendText(WebElement element, String text) {
        waitForElementToBeVisible(element);
        highlightElement(element);

        element.clear();
        element.sendKeys(text);
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        highlightElement(element);
    }

    protected void refreshPageElements() {
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}