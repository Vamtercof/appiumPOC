package utils;

import base.BaseMobileTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementFetch {
    // Usar AppiumDriver con WebElement (en Appium v8+)

    public By getLocator(String identifierType, String identifierValue){
        switch (identifierType.toUpperCase()) {
            case "XPATH":
                return By.xpath(identifierValue);
            case "ID":
                return By.id(identifierValue);
            case "NAME":
                return By.name(identifierValue);
            default:
                throw new IllegalArgumentException("Tipo de identificador no válido: " + identifierType);
        }
    }

    public WebElement getWebElement(String identifierType, String identifierValue) {
        switch (identifierType.toUpperCase()) {
            case "XPATH":
                return BaseMobileTest.driverApp.findElement(By.xpath(identifierValue));

            case "ID":
                return BaseMobileTest.driverApp.findElement(By.id(identifierValue));

            case "NAME":
                return BaseMobileTest.driverApp.findElement(By.name(identifierValue));

            default:
                throw new IllegalArgumentException("Tipo de identificador no válido: " + identifierType);
        }
    }

    public List<WebElement> getWebElements(String identifierType, String identifierValue) {
        switch (identifierType.toUpperCase()) {
            case "XPATH":
                return BaseMobileTest.driverApp.findElements(By.xpath(identifierValue));

            case "ID":
                return BaseMobileTest.driverApp.findElements(By.id(identifierValue));

            case "NAME":
                return BaseMobileTest.driverApp.findElements(By.name(identifierValue));


            default:
                throw new IllegalArgumentException("Tipo de identificador no válido: " + identifierType);
        }
    }
}