package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseMobileTest {
    // Instancias estáticas de ExtentReports y SparkReporter
    public static ExtentReports extent;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentTest logger;  // Para uso en cada prueba
    // Driver appium
    public static AppiumDriver driverApp;

    @BeforeSuite
    public void beforeSuite() {
        // Ruta donde se creará el reporte
        String reportPath = System.getProperty("user.dir") + "/reports/AutomationReport.html";

        // Configurar SparkReporter (interfaz HTML)
        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Resultados de Pruebas - Mobile/Web");

        // Crear la instancia de ExtentReports y vincular el SparkReporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Información del sistema y proyecto
        extent.setSystemInfo("Company", "TELUS");
        extent.setSystemInfo("Project", "Tubi_App");
        extent.setSystemInfo("Tester", "Arturo Castillo");
    }

    public static void setUpAppium() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:automationName","UiAutomator2");
        caps.setCapability("appium:deviceName", "emulator-5554"); // Ajusta el nombre de tu dispositivo/emulador
        caps.setCapability("appium:app", "C:/Users/Vamte/AndroidApp_POC/android_app/tubitv.apk"); // Ruta local a la app que quieres probar

        // Conéctate al servidor de Appium (asegúrate de que esté corriendo en http://127.0.0.1:4723)
        driverApp = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
    }

    @BeforeMethod
    public void beforeMethod(Method testMethod) {
        // Crear un “test” dentro del reporte con el nombre del metodo
        String testName = testMethod.getName(); // Puedes usar @Test(description = "…") si prefieres
        logger = extent.createTest(testName);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        // Registrar el resultado en el reporte
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = takeScreenshot(result.getName());
            logger.fail("Test Falló: " + result.getThrowable().getMessage()).addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.skip("Test Saltado");
        } else {
            logger.pass("Test Exitoso");
        }
    }

    @AfterSuite
    public void afterSuite() {
        // Close appium driver
        if (driverApp != null) {
            driverApp.quit();
        }

        // Generar el reporte final
        if (extent != null) {
            extent.flush();
        }
    }

    public String takeScreenshot(String testName) {
        // Definir la ruta de la captura
        String screenshotDir = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "screenshots";
        // Crear el directorio si no existe
        File dir = new File(screenshotDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String screenshotPath = screenshotDir + File.separator + "screenshot_" + testName + ".png";
        File screenshotFile = ((TakesScreenshot) driverApp).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    public static void tearDownAppium() {
        if (driverApp != null) {
            driverApp.quit();
        }
    }
}
