package XYZBankTest.runner;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

public abstract class BaseTest {

    protected WebDriver driver;

    public BaseTest() {
    }


    @BeforeMethod
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = Files.readAllBytes(scrFile.toPath());
            saveScreenshotPNG(fileContent);
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(byte[] screenshot) {
        return screenshot;
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
