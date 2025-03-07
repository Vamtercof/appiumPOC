package qaMobile.tests;

import base.BaseMobileTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageEvents.AppEvents;

public class LoginTest extends BaseMobileTest {
    AppEvents events = new AppEvents();

    @BeforeClass
    public void setUpTest() throws Exception {
        setUpAppium();  //Llamamos al metodo setUpAppium() de BaseTest
    }

    @Test(description = "Test skip button")
    public void testSkipButton() throws InterruptedException{
        logger.info("Open app in the emulated device");
        events.verifySkipButton();

    }

    @Test(description = "Test family movies section")
    public void testFamilyMovies() throws InterruptedException{
        logger.info("Scroll down and wait for Family Movies section");
        events.verifyFamilyMovies();

    }

    @AfterClass
    public void tearDownTest() {
        tearDownAppium();
    }
}
