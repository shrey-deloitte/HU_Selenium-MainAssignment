import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Main {
     static WebDriver driver;
    @BeforeTest
    public static void launch(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\shredeshpande\\Downloads\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }



    @Test(priority = 0)
    public static void BankManagerLogin() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.findElement(By.xpath("//button[@ng-click='manager()']")).click();

    }

    @Test(priority  =1)
    public static void AddCustomer(){

        Main.driver.findElement(By.xpath("//button[@ng-click='addCust()']")).click();
    }
   // @AfterTest
    public static void quit(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.quit();
    }

}
