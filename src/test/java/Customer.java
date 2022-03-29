import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Customer {
    public  static ArrayList<CustomerDetails> custumerList ;
    @BeforeTest
    public void readXlsx() throws IOException, InterruptedException {
        XlsxReader reader=new XlsxReader("src/resources/customerDetails.xlsx");
        custumerList=reader.readFile();
        Thread.sleep(3000);
    }

    @Test(priority = 0)
    public static void customerLogin(){
        CustomerDetails customerDetails = custumerList.get(1);

        Main.driver.findElement(By.xpath("//button[@ng-click='customer()']")).click();
        Select name=new Select(Main.driver.findElement(By.xpath("//select[@id='userSelect']")));
        name.selectByVisibleText(customerDetails.getFirstName()+" " +customerDetails.getLastName());
        log4j.logger.info("Customer name selected");
        Main.driver.findElement(By.xpath("//button[@type='submit']")).click();
        log4j.logger.info("Logged In");
    }

    @Test(priority = 1)
    public static void transactions(){
        Main.driver.findElement((By.xpath("//button[@ng-click='transactions()']"))).click();
        Main.driver.findElement(By.xpath("//button[@ng-click='back()']")).click();
        log4j.logger.info("transactions checked");
    }

    @Test(priority = 2)
    public static void deposit(){
        String successMsg="Deposit Successful";
        String deposiedAmount= Main.driver.findElement(By.xpath("//STRONG[@class='ng-binding'][text()='0']")).getText();
        Main.driver.findElement(By.xpath("//button[@ng-click='deposit()']")).click();
        Main.driver.findElement(By.xpath("//input[@ng-model='amount']")).sendKeys("1000");
        Main.driver.findElement(By.xpath("//button[@type='submit']")).click();
        String msg= Main.driver.findElement(By.xpath("//span[@ng-show='message']")).getText();
        if (successMsg.equals(msg)){
            log4j.logger.info("Success Message Printed");
        }
        else {
            log4j.logger.error("Not printed success printed");
        }
        log4j.logger.info("Amount Deposited");
        String latestDepositedAmount= Main.driver.findElement(By.xpath("//STRONG[@class='ng-binding'][text()='1000']")).getText();
        if(deposiedAmount.equals(latestDepositedAmount)){
            System.out.println("There is an error");
            log4j.logger.error("Balance Amount Error");
        }
        log4j.logger.info("balance amount cross-checked");
    }


    @Test(priority = 3)
    public static void withdrawl() throws InterruptedException {
        String latestDepositedAmount= Main.driver.findElement(By.xpath("//STRONG[@class='ng-binding'][text()='1000']")).getText();
        Main.driver.findElement(By.xpath("//BUTTON[@ng-class='btnClass3']")).click();


        sleep(4000);

        Main.driver.findElement(By.xpath("//INPUT[@ng-model='amount']")).sendKeys("500");
        Main.driver.manage().timeouts().getScriptTimeout();
        Thread.sleep(2000);
        sleep(2000);
        System.out.println("input added");
        Main.driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(3000);
        String withdraw= Main.driver.findElement(By.xpath("//STRONG[@class='ng-binding'][text()='500']/self::STRONG")).getText();
        if (Integer.parseInt(latestDepositedAmount)==Integer.parseInt(latestDepositedAmount)-Integer.parseInt(withdraw)){
            System.out.println("success");
        }

        String msg= Main.driver.findElement(By.xpath("//span[@ng-show='message']")).getText();
        String SuccessMsg="Transaction successful";
        if (SuccessMsg.equals(msg)){
            log4j.logger.info("Message Successfully printed");
        }
        else {
            log4j.logger.error("Message Not Printed");
        }
    }

    @Test(priority = 4)
    public static void transactions1(){
        Main.driver.findElement((By.xpath("//button[@ng-click='transactions()']"))).click();
        log4j.logger.info("transactions checked");
    }

    //@AfterTest
    public static void quit(){
        Main.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Main.driver.quit();
    }


}
