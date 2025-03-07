package utils;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static base.BaseMobileTest.driverApp;
import static base.BaseMobileTest.logger;

public class commonMethods {

    public static WebElement waitForElement(By locator){
        WebDriverWait wait = new WebDriverWait(driverApp, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean elementIsVisible(String locator){
        try{
            return driverApp.findElement(By.id(locator)).isDisplayed();
        }catch (Exception e){
            logger.warning("Element not found: " + locator);
            return false;
        }
    }

    public static void waitingSeconds(int time) throws InterruptedException {
        time = time * 1000;
        Thread.sleep(time);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public static void scrollDown(double scrollPercentage) {
        // Validar que el porcentaje esté entre 0 y 1
        if (scrollPercentage < 0) scrollPercentage = 0;
        if (scrollPercentage > 1) scrollPercentage = 1;

        // Obtener el tamaño de la pantalla
        Dimension size = driverApp.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int minY = (int) (size.height * 0.2); // punto mínimo para el scroll
        int totalScrollDistance = startY - minY;

        // Calcular el punto final según el porcentaje indicado
        int endY = startY - (int) (totalScrollDistance * scrollPercentage);

        // Realizar el gesto de scroll (swipe hacia arriba, lo que equivale a desplazar la pantalla hacia abajo)
        new TouchAction<>((AndroidDriver) driverApp)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }
}
