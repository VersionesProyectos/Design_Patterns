package com.epam.automation.driver;


import org.openqa.selenium.WebDriver;
import java.lang.ThreadLocal;

public class DriverManager {

    private static volatile DriverManager instance;
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        return instance;
    }
    public WebDriver getDriver(String browser) {
        if (threadLocalDriver.get() == null) {
            WebDriver driver = DriverFactory.createDriver(browser);
            threadLocalDriver.set(driver);
        }
        return threadLocalDriver.get();
    }

    public void quitDriver() {
        if (threadLocalDriver.get() != null) {
            threadLocalDriver.get().quit();
            threadLocalDriver.remove();
        }
    }
}
