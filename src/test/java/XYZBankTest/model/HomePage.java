package XYZBankTest.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//button[@ng-click='customer()']")
    private WebElement customerLoginButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CustomerLoginPage clickCustomerLoginButton() {

        getClickableElement(10,customerLoginButton).click();
        return new CustomerLoginPage(getDriver());
    }

}