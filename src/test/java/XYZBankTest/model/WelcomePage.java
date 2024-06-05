package XYZBankTest.model;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WelcomePage extends BasePage{

    By SubmitButton = By.xpath("//button[@type='submit']");
    
    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public String getNameOfClient(){
        final WebElement nameOfClient = getWait(10).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//span[@class='fontBig ng-binding']")));
        return nameOfClient.getText();


    }

    public String getSuccessDepositText(){
        final WebElement depositSuccessText = getWait(10).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//span[text()='Deposit Successful']")));
        return depositSuccessText.getText();
    }

    public String getSuccessWithdrawnText(){
        final WebElement withdrawnSuccessText = getWait(10).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//span[text()='Transaction successful']")));
        return withdrawnSuccessText.getText();
    }

    public String getBalance(){
        final WebElement withdrawnSuccessText = getWait(10).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//div/strong[2]")));
        return withdrawnSuccessText.getText();
    }

    public WelcomePage addDepositSumAndSubmit(String sum){
        final WebElement depositMainButton = getWait(10).until(ExpectedConditions.elementToBeClickable
                (By.xpath("//button[@ng-click='deposit()']")));
        depositMainButton.click();

        final WebElement amountField = getWait(10).until(ExpectedConditions.elementToBeClickable
                (By.xpath("//input[@placeholder='amount']")));
        amountField.sendKeys(sum);

        final WebElement depositAddSumSubmitButton = getWait(10).until(ExpectedConditions.elementToBeClickable(SubmitButton));
        depositAddSumSubmitButton.click();
        return new WelcomePage(getDriver());

    }

    public WelcomePage addWithdrawnSumAndSubmit(String sum) {


        final WebElement withdrawnMainButton = getWait(10).until(ExpectedConditions.elementToBeClickable
                (By.xpath("//button[@ng-click='withdrawl()']")));
        withdrawnMainButton.click();

        WebElement amountField = getWait(10).until(ExpectedConditions.elementToBeClickable
                (By.xpath("//input[@class='form-control ng-dirty ng-touched ng-invalid ng-invalid-required']")));

        Actions actions = new Actions(getDriver());
        actions.moveToElement(amountField).click().perform();
        int permSum = Integer.parseInt(String.valueOf(sum));
            for (int i = 0; i < permSum; i++) {
                actions.sendKeys(Keys.ARROW_UP).perform();
            }

        final WebElement withdrawnAddSumSubmitButton = getWait(10).until(ExpectedConditions.elementToBeClickable
                (By.xpath("//button[text()='Withdraw']")));
        withdrawnAddSumSubmitButton.click();
        return new WelcomePage(getDriver());
    }

    public List<String> getTransactionsList(){
        final WebElement transactionsMainButton = getWait(10).until(ExpectedConditions.elementToBeClickable
                (By.xpath("//button[@ng-click='transactions()']")));
        transactionsMainButton.click();

        final List<WebElement> tableOfTransactions = Collections.singletonList(getWait(10).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//table[@class='table table-bordered table-striped']"))));

        return tableOfTransactions.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

    }

}
