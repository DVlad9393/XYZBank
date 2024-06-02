package XYZBankTest.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage extends HomePage{

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    public WelcomePage selectCustomerHarryPotterAndClickLogin(){
        final WebElement selectDropDownListofCustomer = getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='userSelect']")));
        Select selectHarry = new Select(selectDropDownListofCustomer);
        selectHarry.selectByVisibleText("Harry Potter");
        final WebElement loginButton = getWait10().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-default']")));
        loginButton.click();
        return new WelcomePage(getDriver());
    }

}
