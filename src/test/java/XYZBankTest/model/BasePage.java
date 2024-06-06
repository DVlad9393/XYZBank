package XYZBankTest.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private final WebDriver driver;

    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    protected WebDriver getDriver(){
        return driver;
    }

    protected WebDriverWait getWait(long sec) {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(sec));
        }
        return wait;
    }

    protected WebElement getClickableElement (long sec, WebElement element){
        return getWait(sec).until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement getVisibleElement (long sec, WebElement element){
        return getWait(sec).until(ExpectedConditions.visibilityOf(element));
    }

}
