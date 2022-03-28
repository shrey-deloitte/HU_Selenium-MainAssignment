import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Home {


    @Test(priority = 0)
    public static void addCustomer(){
        Main.driver.findElement(By.xpath("//input[@ng-model='fName']")).sendKeys("qwerty");
        Main.driver.findElement(By.xpath("//input[@ng-model='lName']")).sendKeys("qwerty");
        Main.driver.findElement(By.xpath("//input[@ng-model='postCd']")).sendKeys("490009");
        Main.driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        Main.driver.switchTo().alert().accept();
    }

    @Test(priority = 1)
    public static void openAccount(){
        Main.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Main.driver.findElement((By.xpath("//button[@ng-click='openAccount()']"))).click();
        Select customer=new Select(Main.driver.findElement(By.xpath("//select [@name='userSelect']")));
        customer.selectByVisibleText("qwerty qwerty");

        Main.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Select currency=new Select(Main.driver.findElement(By.xpath("//select [@name='currency']")));
        currency.selectByVisibleText("Rupee");

        Main.driver.findElement(By.xpath("//button[@type='submit']")).click();
        Main.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Main.driver.switchTo().alert().accept();

        Main.driver.findElement(By.xpath("//button[@ng-click='home()']")).click();
    }












}
