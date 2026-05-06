package com.epam.automation.hooks;

import io.cucumber.java.*;
import com.epam.automation.driver.DriverManager;
import com.epam.automation.utils.TestUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        // 1. Obtener parámetros con valores por defecto seguros
        String browser = System.getProperty("browser", "chrome");
        String url = System.getProperty("url", "https://formy-project.herokuapp.com/");
        int timeout = Integer.parseInt(System.getProperty("timeout", "10"));

        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.manage().window().maximize();

        // 2. Aplicar esperas
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.get(url);

        // 3. Esperar a que la página esté lista
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        // Si el paso falla, adjuntamos evidencia al reporte de Cucumber/Allure
        if (scenario.isFailed()) {
            WebDriver driver = DriverManager.getInstance().getDriver();
            // Asegúrate de que este método use scenario.attach(bytes, "image/png", name)
            TestUtils.saveScreenshotToAllure(driver);

            // Opcional: Adjuntar directamente al objeto scenario de Cucumber
            final byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Evidencia de fallo en el paso");
        }
    }

    @After
    public void afterScenario() {
        // Siempre cerrar el driver al final para liberar memoria
        DriverManager.getInstance().quitDriver();
    }
}