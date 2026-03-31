package com.epam.automation.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        WebDriver originalDriver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");

                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                originalDriver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                originalDriver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        WebDriverListener listener = new WebDriverListenerImpl();

        WebDriver decoratedDriver = new EventFiringDecorator<>(listener).decorate(originalDriver);

        return decoratedDriver;
    }
}

