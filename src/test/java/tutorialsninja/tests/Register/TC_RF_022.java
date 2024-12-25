package tutorialsninja.tests.Register;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_022 {
    @Test
    public void verifyVisibiltyTogglineOfPasswordsFieldsOnRegisterAccount() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",driver.findElement(By.xpath("//input[@id='input-password']")) );
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']")).getAttribute("type"), "password", "Password field is not masked!");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-confirm']")).getAttribute("type"), "password", "Password Confirm field is not masked!");

        driver.quit();
    }
}
