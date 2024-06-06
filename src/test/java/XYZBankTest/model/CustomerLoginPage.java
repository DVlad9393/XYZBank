package XYZBankTest.model;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage extends HomePage{

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//select[@name='userSelect']")
    private WebElement DropDownListOfCustomer;

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement loginButton;

    @Step("Выбрать клиента HarryPotter и нажать кнопнку Login")
    public WelcomePage selectCustomerHarryPotterAndClickLogin(){
        Select selectHarry = new Select(getClickableElement(10,DropDownListOfCustomer));
        selectHarry.selectByVisibleText("Harry Potter");
        getClickableElement(10,loginButton).click();
        return new WelcomePage(getDriver());
    }

}