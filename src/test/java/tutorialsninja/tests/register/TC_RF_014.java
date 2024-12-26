package tutorialsninja.tests.register;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_014 {
    WebDriver driver;

    @Test
    public void verifyMandatoryFieldsSymbolAndColorInRegisterAccountPage() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
        WebElement firstName = driver.findElement(By.xpath("//label[normalize-space()='First Name']"));
        WebElement lastName = driver.findElement(By.xpath("//label[normalize-space()='Last Name']"));
        WebElement email = driver.findElement(By.xpath("//label[normalize-space()='E-Mail']"));
        WebElement telephone = driver.findElement(By.xpath("//label[normalize-space()='Telephone']"));
        WebElement password = driver.findElement(By.xpath("//label[normalize-space()='Password']"));
        WebElement passwordConfirm = driver.findElement(By.xpath("//label[normalize-space()='Password Confirm']"));

        String contentExpected = "*";
        String colorExpected = "rgb(255, 0, 0)";


        Assert.assertEquals(getContentBeforeElement(firstName), contentExpected);
        Assert.assertEquals(getColorBeforeElement(firstName), colorExpected);

        Assert.assertEquals(getContentBeforeElement(lastName), contentExpected);
        Assert.assertEquals(getColorBeforeElement(lastName), colorExpected);

        Assert.assertEquals(getContentBeforeElement(email), contentExpected);
        Assert.assertEquals(getColorBeforeElement(email), colorExpected);

        Assert.assertEquals(getContentBeforeElement(telephone), contentExpected);
        Assert.assertEquals(getColorBeforeElement(telephone), colorExpected);

        Assert.assertEquals(getContentBeforeElement(password), contentExpected);
        Assert.assertEquals(getColorBeforeElement(password), colorExpected);

        Assert.assertEquals(getContentBeforeElement(passwordConfirm), contentExpected);
        Assert.assertEquals(getColorBeforeElement(passwordConfirm), colorExpected);


        driver.quit();


    }

    //Hàm lấy dấu *
    public String getContentBeforeElement(WebElement beforeElement) {
        String beforeContent = (String) ((JavascriptExecutor) driver).executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
                beforeElement);
        //Xử lý chuỗi từ "* " thành *
        beforeContent = beforeContent.replaceAll("^\"|\"$", "").trim();
        return beforeContent;
    }

    //Hàm lấy màu dấu *
    public String getColorBeforeElement(WebElement beforeElement) {
        String beforeColor = (String) ((JavascriptExecutor) driver).executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",
                beforeElement);
        return beforeColor;
    }
}
