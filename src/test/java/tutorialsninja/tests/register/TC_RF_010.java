package tutorialsninja.tests.register;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class TC_RF_010 {
    WebDriver driver = null;
    @Test
    public void verifyRegisteringAccountUsingInvalidEmail() throws IOException, InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://tutorialsninja.com/demo");

        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Duy");
        driver.findElement(By.id("input-lastname")).sendKeys("Truong");
        driver.findElement(By.id("input-email")).sendKeys("duy");
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();


        String messageExpected1 = "Please include an '@' in the email address. 'duy' is missing an '@'.";
        Thread.sleep(2000);
        validateMessage(messageExpected1);

        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("duy@");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String messageExpected2 = "Please enter a part following '@'. 'duy@' is incomplete.";
        Thread.sleep(2000);
        validateMessage(messageExpected2);

        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("duy@gmail");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String expectedWarningMessage = "E-Mail Address does not appear to be valid!";
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), expectedWarningMessage);

        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("duy@gmail.");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String messageExpected3 = "'.' is used at a wrong position in 'gmail.'.";
        Thread.sleep(2000);
        validateMessage(messageExpected3);

        driver.quit();
    }

    //Dùng js để lấy thuộc tính validationMessage trong HTML5
    public void validateMessage(String messageExpected){
        String messageActual = (String)((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;",driver.findElement(By.id("input-email")));
        Assert.assertEquals(messageActual, messageExpected);

    }
}