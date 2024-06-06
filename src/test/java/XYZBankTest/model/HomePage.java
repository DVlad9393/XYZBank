package XYZBankTest.model;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//button[@ng-click='customer()']")
    private WebElement customerLoginButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку CustomerLogin")
    public CustomerLoginPage clickCustomerLoginButton() {

        getClickableElement(10,customerLoginButton).click();
        return new CustomerLoginPage(getDriver());
    }

}