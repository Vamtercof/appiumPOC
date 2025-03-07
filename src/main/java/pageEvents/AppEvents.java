package pageEvents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.AppElements;

import static base.BaseMobileTest.logger;

import utils.ElementFetch;
import utils.commonMethods;

public class AppEvents {
    ElementFetch element = new ElementFetch();

    public void verifySkipButton() throws InterruptedException {
        logger.info("Verify if the Skip button is loaded");

        try {
            // Esperar hasta que el botón esté visible y clickeable
            By skipButtonLocator = element.getLocator("ID", AppElements.skipButtonId);

            WebElement skipButton = commonMethods.waitForElement(skipButtonLocator);
            skipButton.click();

            commonMethods.waitingSeconds(2);

            Assert.assertTrue(commonMethods.elementIsVisible(AppElements.moviesButtonId),"Movies button not displayed");

        } catch (Exception e) {
            logger.info("Error: Skip button not found.");
            e.printStackTrace();
        }
    }

    public void verifyFamilyMovies() throws InterruptedException {
        logger.info("Verify if the Family Movies option is present");

        try {
            By moviesButtonLocator = element.getLocator("ID", AppElements.moviesButtonId);
            WebElement moviesButton = commonMethods.waitForElement(moviesButtonLocator);
            moviesButton.click();

            commonMethods.waitingSeconds(2);

            commonMethods.scrollDown(0.5);

            By familyMoviesLocator = element.getLocator("ID", AppElements.familyMoviesXpath);
            WebElement familyMovies = commonMethods.waitForElement(familyMoviesLocator);
            familyMovies.click();

            commonMethods.waitingSeconds(2);

            Assert.assertTrue(commonMethods.elementIsVisible(AppElements.firstAssetXpath),"Family Movies section not displayed");



        }catch (Exception e) {
            logger.info("Error: Family Movies section not found");
            e.printStackTrace();
        }


    }
}
