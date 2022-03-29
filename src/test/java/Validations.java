import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Validations {
    public static ArrayList<CustomerDetails> custumerList;

    @BeforeTest
    public void readXlsx() throws IOException, InterruptedException {
        XlsxReader reader = new XlsxReader("src/resources/customerDetails.xlsx");
        custumerList = reader.readFile();
        Thread.sleep(2000);
    }

    @Test(priority = 0)
    public static void checkTransactions() throws InterruptedException {
        String credit = Main.driver.findElement(By.xpath("//TD[@class='ng-binding'][text()='Credit'][1]")).getText();
        String debit = Main.driver.findElement(By.xpath("//TD[@class='ng-binding'][text()='Debit'][1]")).getText();

        if (credit.equals("Credit")) {
            log4j.logger.info("Credit Transactions Successfully added");
        } else {
            log4j.logger.error("Credit Transactions Not added Successfully");
        }

        if (debit.equals("Debit")) {
            log4j.logger.info("Debit Transactions Successfully added");
        } else {
            log4j.logger.error("Debit Transactions Not added Successfully");
        }

        sleep(1000);

        Main.driver.findElement(By.xpath("//button[@ng-click='back()']")).click();

    }

    @Test(priority = 1)
    public static void withdrawValidate() throws InterruptedException {

        Main.driver.findElement(By.xpath("//BUTTON[@ng-class='btnClass3']")).click();
        sleep(4000);

        Main.driver.findElement(By.xpath("//INPUT[@ng-model='amount']")).sendKeys("600");
        Main.driver.findElement(By.xpath("//button[@type='submit']")).click();
        String warning = Main.driver.findElement(By.xpath("//span[@ng-show='message']")).getText();
        String msg = "Transaction Failed. You can not withdraw amount more than the balance.";

        if (msg.equals(warning)) {
            log4j.logger.info("Withdraw error message printed");
        } else {
            log4j.logger.error("Withdraw error message  not printed");
        }

        Main.driver.findElement(By.xpath("//button[@ng-click='home()']")).click();

    }

    @Test(priority = 2)
    public static void NameValidation() throws InterruptedException {

        CustomerDetails customerDetails = custumerList.get(1);
        Main.driver.findElement(By.xpath("//button[@ng-click='manager()']")).click();

        Main.driver.findElement(By.xpath("//button[@ng-click='addCust()']")).click();
        Thread.sleep(2000);

        Main.driver.findElement(By.xpath("//input[@ng-model='fName']")).sendKeys(customerDetails.getFirstName());
        Thread.sleep(1000);
        Main.driver.findElement(By.xpath("//input[@ng-model='lName']")).sendKeys(customerDetails.getLastName());
        Thread.sleep(1000);
        Main.driver.findElement(By.xpath("//input[@ng-model='postCd']")).sendKeys(customerDetails.getPinCode());
        Main.driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        String alert="Please check the details. Customer may be duplicate.";

        String alertmsg=Main.driver.switchTo().alert().getText();

        if(alertmsg.equals(alert)){
            log4j.logger.info("Alert message for same userID printed");
        }
        else {
            log4j.logger.error("Alert message for same  not userID printed");
        }

        }

        @AfterTest
    public static void quit(){
            Main.driver.quit();
        }

    }





