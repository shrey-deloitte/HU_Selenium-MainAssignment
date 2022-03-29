import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class BankManager {

    public  static ArrayList<CustomerDetails> custumerList ;

    @BeforeTest
    public void readXlsx() throws IOException, InterruptedException {
        XlsxReader reader=new XlsxReader("src/resources/customerDetails.xlsx");
        custumerList=reader.readFile();
        Thread.sleep(3000);
    }




    @Test(priority = 0)
    public static void addCustomer() throws IOException, AWTException, InterruptedException {
        CustomerDetails customerDetails = custumerList.get(1);

        Main.driver.findElement(By.xpath("//button[@ng-click='addCust()']")).click();
        Thread.sleep(2000);
        Main.driver.findElement(By.xpath("//input[@ng-model='fName']")).sendKeys(customerDetails.getFirstName());
        Thread.sleep(1000);
        Main.driver.findElement(By.xpath("//input[@ng-model='lName']")).sendKeys(customerDetails.getLastName());
        Thread.sleep(1000);
        Main.driver.findElement(By.xpath("//input[@ng-model='postCd']")).sendKeys(customerDetails.getPinCode());
        Main.driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        Main.driver.switchTo().alert().accept();
        log4j.logger.info("Customer Added");
        Main.SuccessScreenshot();

    }


    @Test(priority = 1)
    public static void openAccount() throws IOException, AWTException {
        CustomerDetails customerDetails = custumerList.get(1);

        Main.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Main.driver.findElement((By.xpath("//button[@ng-click='openAccount()']"))).click();
        Select customer=new Select(Main.driver.findElement(By.xpath("//select [@name='userSelect']")));

        customer.selectByVisibleText(customerDetails.getFirstName()+" " +customerDetails.getLastName());

        log4j.logger.info("Account Created");

        Main.SuccessScreenshot();

        Main.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Select currency=new Select(Main.driver.findElement(By.xpath("//select [@name='currency']")));
        currency.selectByVisibleText("Rupee");

        Main.driver.findElement(By.xpath("//button[@type='submit']")).click();
        Main.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Main.driver.switchTo().alert().accept();

        Main.driver.findElement(By.xpath("//button[@ng-click='home()']")).click();
    }


}
