package XYZBankTest.runner;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public abstract class BaseTest extends TestListenerAdapter {

    protected WebDriver driver;

    private Logger log = LoggerFactory.getLogger(BaseTest.class);

    private WebDriverWait wait;

    public BaseTest() {
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    protected WebDriverWait getWait(long sec) {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sec));
        }
        return wait;
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test class started: " + result.getTestClass().getName());
        log.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test SUCCESS: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        makeScreenshot();
        log.error("Test FAILED: " + result.getName());
        if (result.getThrowable()!=null) {
            result.getThrowable().printStackTrace();
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
