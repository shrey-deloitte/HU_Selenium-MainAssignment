import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class BankManager {


    @Test(priority = 0)
    public static void addCustomer(){
        Main.driver.findElement(By.xpath("//input[@ng-model='fName']")).sendKeys("qwerty");
        Main.driver.findElement(By.xpath("//input[@ng-model='lName']")).sendKeys("qwerty");
        Main.driver.findElement(By.xpath("//input[@ng-model='postCd']")).sendKeys("490009");
        Main.driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        Main.driver.switchTo().alert().accept();
        log4jDemo.logger.info("Customer Added");
    }

    @Test(priority = 1)
    public static void openAccount() throws IOException, AWTException {
        Main.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Main.driver.findElement((By.xpath("//button[@ng-click='openAccount()']"))).click();
        Select customer=new Select(Main.driver.findElement(By.xpath("//select [@name='userSelect']")));
        customer.selectByVisibleText("qwerty qwerty");
        log4jDemo.logger.info("Account Created");

        screenshot();

        Main.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Select currency=new Select(Main.driver.findElement(By.xpath("//select [@name='currency']")));
        currency.selectByVisibleText("Rupee");

        Main.driver.findElement(By.xpath("//button[@type='submit']")).click();
        Main.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Main.driver.switchTo().alert().accept();

        Main.driver.findElement(By.xpath("//button[@ng-click='home()']")).click();
    }

    public static void screenshot() throws IOException, AWTException {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        ImageIO.write(image, "png", new File("C:\\Users\\shredeshpande\\IdeaProjects\\HU_Selenium_MainAssignment\\src\\resources\\"+"img"+".png"));
    }











}
