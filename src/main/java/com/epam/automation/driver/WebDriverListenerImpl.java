package com.epam.automation.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class WebDriverListenerImpl implements WebDriverListener {

    private static final Logger logger = LogManager.getLogger(WebDriverListenerImpl.class);

    @Override
    public void beforeTo(WebDriver.Navigation navigation, String url) {
        logger.info("Navegando a la URL: " + url);
    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, String url) {
        logger.debug("Navegación exitosa a: " + url);
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        logger.info("Cerrando el navegador...");
    }

    @Override
    public void beforeClick(WebElement element) {

        String elementDesc = getElementDescription(element);
        logger.info("Intentando hacer CLIC en el elemento: " + elementDesc);
    }

    @Override
    public void afterClick(WebElement element) {
        logger.debug("CLIC ejecutado correctamente.");
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        String elementDesc = getElementDescription(element);
        String text = (keysToSend == null) ? "null" : Arrays.toString(keysToSend);
        logger.info("Enviando TEXTO '" + text + "' al elemento: " + elementDesc);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.debug("Texto enviado correctamente.");
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        logger.error("!!! ERROR detectado durante la ejecución de: " + method.getName());
        logger.error("Detalles del error: " + e.getTargetException().getMessage());
    }

    private String getElementDescription(WebElement element) {
        String desc = element.toString();
        if (desc.contains("->")) {
            desc = desc.substring(desc.indexOf("->") + 2).trim();
        }
        return desc;
    }
}
