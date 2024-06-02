package XYZBankTest.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CustomerLoginPage clickCustomerLoginButton(){

        WebElement customerLoginButton = getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@ng-click='customer()']")));;
        System.out.println(customerLoginButton.toString());
        customerLoginButton.click();
        return new CustomerLoginPage(getDriver());
    }

}
