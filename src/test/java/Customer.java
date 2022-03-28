import org.apache.poi.ss.formula.functions.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class Customer {

    @Test(priority = 0)
    public static void customerLogin(){
        Main.driver.findElement(By.xpath("//button[@ng-click='customer()']")).click();
        Select name=new Select(Main.driver.findElement(By.xpath("//select[@id='userSelect']")));
        name.selectByVisibleText("qwerty qwerty");
        Main.driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Test(priority = 1)
    public static void transactions(){
        Main.driver.findElement((By.xpath("//button[@ng-click='transactions()']"))).click();
        Main.driver.findElement(By.xpath("//button[@ng-click='back()']")).click();
    }

    @Test(priority = 2)
    public static void deposit(){
        String deposiedAmount= Main.driver.findElement(By.xpath("//STRONG[@class='ng-binding'][text()='0']")).getText();
        Main.driver.findElement(By.xpath("//button[@ng-click='deposit()']")).click();
        Main.driver.findElement(By.xpath("//input[@ng-model='amount']")).sendKeys("1000");
        Main.driver.findElement(By.xpath("//button[@type='submit']")).click();
        String latestDepositedAmount= Main.driver.findElement(By.xpath("//STRONG[@class='ng-binding'][text()='1000']")).getText();
        if(deposiedAmount.equals(latestDepositedAmount)){
            System.out.println("There is an error");
        }
    }


    @Test(priority = 3)
    public static void withdrawl() throws InterruptedException {
        String latestDepositedAmount= Main.driver.findElement(By.xpath("//STRONG[@class='ng-binding'][text()='1000']")).getText();
        Main.driver.findElement(By.xpath("//BUTTON[@ng-class='btnClass3']")).click();
        System.out.println("withdraw");
        Main.driver.findElement(By.xpath("//INPUT[@type='number']/self::INPUT")).sendKeys("500");
        sleep(2000);
        System.out.println("input added");
        sleep(2000);
        Main.driver.findElement(By.xpath("//BUTTON[@type='submit'][text()='Withdraw']/self::BUTTON")).click();
        sleep(3000);
        String withdraw= Main.driver.findElement(By.xpath("//STRONG[@class='ng-binding'][text()='500']/self::STRONG")).getText();
        if (Integer.parseInt(latestDepositedAmount)==Integer.parseInt(latestDepositedAmount)-Integer.parseInt(withdraw)){
            System.out.println("success");
        }

    }
}
