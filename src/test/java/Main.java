import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class Main {
     static WebDriver driver;

    public static void SuccessScreenshot() throws IOException, AWTException {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        ImageIO.write(image, "png", new File("C:\\Users\\shredeshpande\\IdeaProjects\\HU_Selenium_MainAssignment\\src\\resources\\SuccessCases\\"+"img"+".png"));
    }
    public void FailedScreenshot() throws IOException, AWTException {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

        ImageIO.write(image, "png", new File("C:\\Users\\shredeshpande\\IdeaProjects\\HU_Selenium_MainAssignment\\src\\resources\\SuccessCases\\FailedCases\\"+"img"+".png"));
    }


    @BeforeTest
    public static void launch(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\shredeshpande\\Downloads\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        log4j.logger.info("Test Started");
    }



    @Test(priority = 0)
    public static void BankManagerLogin() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.findElement(By.xpath("//button[@ng-click='manager()']")).click();

    }


    @AfterTest
    public static void quit(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.quit();
    }

}
