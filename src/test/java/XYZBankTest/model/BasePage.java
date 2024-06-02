package XYZBankTest.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage extends BaseModel{
    public BasePage(WebDriver driver) {
        super(driver);
    }

    By mainButton = By.xpath("//button[@class='btn home']");

    public BasePage clickmainButton(){
        getDriver().findElement(mainButton).click();
        return new HomePage(getDriver());
    }
}
