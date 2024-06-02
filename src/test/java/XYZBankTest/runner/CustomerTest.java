package XYZBankTest.runner;

import XYZBankTest.model.HomePage;
import XYZBankTest.model.TestUtils;
import XYZBankTest.model.WelcomePage;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CustomerTest extends BaseTest{

        private static final String CUSTOMER_NAME = "Harry Potter";

        private static final String SUCESS_ADD_DEPOSIT_TEXT = "Deposit Successful";

        private static final String SUCESS_ADD_WITHDRAWN_TEXT = "Transaction successful";

        private static final String ADDED_SUM = new TestUtils().getFibonacciSum();

        private static final String RESULT_BALANCE = "0";

        private static final String TRANSACTION_TYPE_CREDIT = ADDED_SUM+" Credit";

        private static final String TRANSACTION_TYPE_DEBIT = ADDED_SUM+" Debit";

    protected CustomerTest() {
        super();
    }

    @Test
    public void testMainSmokeClientHarryPotter() {


        String balance = new HomePage(driver)
                .clickCustomerLoginButton()
                .selectCustomerHarryPotterAndClickLogin()
                .addDepositSumAndSubmit(ADDED_SUM)
                .addWithdrawnSumAndSubmit(ADDED_SUM)
                .getBalance();

        Assert.assertEquals(balance, RESULT_BALANCE);

        List<String> transactionsList = new WelcomePage(driver).getTransactionsList();

        System.out.println(transactionsList.get(0));
        System.out.println(transactionsList.size());
        System.out.println(transactionsList);

        Assert.assertTrue(transactionsList.get(0).contains(TRANSACTION_TYPE_CREDIT));
        Assert.assertTrue(transactionsList.get(0).contains(TRANSACTION_TYPE_DEBIT));

        try {
            FileWriter csvWriter = new FileWriter("transactions.csv");
            csvWriter.append("Date-Time,Amount,Transaction Type\n");

            for (String transaction : transactionsList) {
                csvWriter.append(transaction + "\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Allure.addAttachment("Transactions", "text/csv", new File("transactions.csv").toURI().toString());
    }




        @Test
        public void testCheckAuthorization() {

        String nameOfClient = new HomePage(driver)
                .clickCustomerLoginButton()
                .selectCustomerHarryPotterAndClickLogin()
                .getNameOfClient();

        Assert.assertEquals(nameOfClient, CUSTOMER_NAME);
    }

        @Test
        public void testCheckAddDeposit() {

                String depositSuccessText = new HomePage(driver)
                        .clickCustomerLoginButton()
                        .selectCustomerHarryPotterAndClickLogin()
                        .addDepositSumAndSubmit(ADDED_SUM)
                        .getSuccessDepositText();

                Assert.assertEquals(depositSuccessText, SUCESS_ADD_DEPOSIT_TEXT);
        }

        @Test
        public void testCheckWithdrawn() {

                String successText = new HomePage(driver)
                        .clickCustomerLoginButton()
                        .selectCustomerHarryPotterAndClickLogin()
                        .addDepositSumAndSubmit(ADDED_SUM)
                        .addWithdrawnSumAndSubmit(ADDED_SUM)
                        .getSuccessWithdrawnText();

                Assert.assertEquals(successText, SUCESS_ADD_WITHDRAWN_TEXT);
        }

}
