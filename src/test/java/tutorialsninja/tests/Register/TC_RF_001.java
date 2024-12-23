package tutorialsninja.tests.Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class TC_RF_001 {
@Test
        public void verifyRegisteringWithMandatoryFields(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Duy");
        driver.findElement(By.id("input-lastname")).sendKeys("Truong");
        driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("123456");
        driver.findElement(By.id("input-confirm")).sendKeys("123456");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());

        String expectedHeading = "Your Account Has Been Created!";

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText(), expectedHeading);

        String actualProperDetailsOne = "Congratulations! Your new account has been successfully created!";
        String actualProperDetailsTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String actualProperDetailsThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String actualProperDetailsFour = "contact us";

        String expectedProperDetails = driver.findElement(By.id("content")).getText();

        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsOne));
        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsTwo));
        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsThree));
        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsFour));

        driver.findElement(By.xpath("//a[text()='Continue']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

        driver.quit();

    }

        public String generateNewEmail() {
//                Date date = new Date();
//                String dateString = date.toString();
//                //Loại bỏ khoảng trắng, : và + trong chuỗi
//                String formattedDateString = dateString.replaceAll("[ :+]", "");
//                String emailWithTimeStamp = formattedDateString + "@gmail.com";
//                return emailWithTimeStamp;

                //chỉ 1 dong code
                return new Date().toString().replaceAll("[ :+]", "") + "@gmail.com" ;
        }
}
