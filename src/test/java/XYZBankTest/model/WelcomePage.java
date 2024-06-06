package XYZBankTest.model;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WelcomePage extends BasePage{

    @FindBy(xpath = "//span[@class='fontBig ng-binding']")
    private WebElement nameOfClient;

    @FindBy(xpath = "//span[text()='Deposit Successful']")
    private WebElement depositSuccessText;

    @FindBy(xpath = "//span[text()='Transaction successful']")
    private WebElement withdrawnSuccessText;

    @FindBy(xpath = "//div/strong[2]")
    private WebElement balanceCount;

    @FindBy(xpath = "//button[@ng-click='deposit()']")
    private WebElement depositMainButton;

    @FindBy(xpath = "//input[@placeholder='amount']")
    private WebElement amountFieldDeposit;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement depositSubmitButton;

    @FindBy(xpath = "//button[@ng-click='withdrawl()']")
    private WebElement withdrawnMainButton;

    @FindBy(xpath = "//input[@class='form-control ng-dirty ng-touched ng-invalid ng-invalid-required']")
    private WebElement amountFieldWithdrawn;

    @FindBy(xpath = "//button[text()='Withdraw']")
    private WebElement withdrawnAddSumSubmitButton;

    @FindBy(xpath = "//button[@ng-click='transactions()']")
    private WebElement transactionsMainButton;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped']")
    private WebElement tableOfTransactions;

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить имя клиента на странице")
    public String getNameOfClient(){
        return getVisibleElement(10,nameOfClient).getText();
    }

    @Step("Получить текст об успешном зачислении депозита")
    public String getSuccessDepositText(){
        return getVisibleElement(10,depositSuccessText).getText();
    }

    @Step("Получить текст об успешной отмене депозита")
    public String getSuccessWithdrawnText(){
        return getVisibleElement(10,withdrawnSuccessText).getText();
    }

    @Step("Получить данные о балансе")
    public String getBalance(){
        return getVisibleElement(10,balanceCount).getText();
    }

    @Step("Ввести сумму депозита {sum} и нажать кнопку Deposit")
    public WelcomePage addDepositSumAndSubmit(String sum){
        getClickableElement(10,depositMainButton).click();
        getClickableElement(10, amountFieldDeposit).sendKeys(sum);
        getClickableElement(10,depositSubmitButton).click();
        return new WelcomePage(getDriver());
    }

    @Step("Ввести сумму отзыва депозита {sum} и нажать кнопку Withdrawn")
    public WelcomePage addWithdrawnSumAndSubmit(String sum) {
        getClickableElement(10,withdrawnMainButton).click();

        Actions actions = new Actions(getDriver());
        actions.moveToElement(getClickableElement(10,amountFieldWithdrawn)).click().perform();
        int permSum = Integer.parseInt(String.valueOf(sum));
            for (int i = 0; i < permSum; i++) {
                actions.sendKeys(Keys.ARROW_UP).perform();
            }

        getClickableElement(10,withdrawnAddSumSubmitButton).click();
        return new WelcomePage(getDriver());
    }

    @Step("Получить список транзакций")
    public List<String> getTransactionsList(){
        getClickableElement(10,transactionsMainButton).click();

        final List<WebElement> transactionsTableList = Collections.singletonList(getVisibleElement(10,tableOfTransactions));

        return transactionsTableList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

    }

}
