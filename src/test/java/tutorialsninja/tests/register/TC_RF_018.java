package tutorialsninja.tests.register;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_018 {
    WebDriver driver;
    @Test
    public void verifyRegisteringAccountFieldsHeightWidthAligment(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://tutorialsninja.com/demo");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        String expectedHeight = "34px";
        String expectedWidth = "701.25px";

        //First Name text field from 1 to 32 characters
        By firstNameField = By.id("input-firstname");
        String fnError = "First Name must be between 1 and 32 characters!";
        validateHeightAndWidth(firstNameField, expectedHeight, expectedWidth);
        validateInputData(firstNameField, 1, 32, fnError );

        //Last Name text field from 1 to 32 characters
        By lastNameField = By.id("input-lastname");
        String lnError = "Last Name must be between 1 and 32 characters!";
        validateHeightAndWidth(lastNameField, expectedHeight, expectedWidth);
        validateInputData(lastNameField, 1, 32, lnError );

        //Email text field should not have any limit
        By emailField = By.id("input-email");
        validateHeightAndWidth(lastNameField, expectedHeight, expectedWidth);
        driver.findElement(emailField).sendKeys("abcdefghijklmnopqrstuvwxyz@gmail.com");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        try {
            Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).isDisplayed());
        }catch(NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        //Telephone text field from 3 to 32 characters
        By telephoneField = By.id("input-telephone");
        String teleError = "Telephone must be between 3 and 32 characters!";
        validateHeightAndWidth(telephoneField, expectedHeight, expectedWidth);
        validateInputData(telephoneField, 3, 32, teleError );

        //Password text field from 4 to 20 characters
        //Error when enter 21 characters but do not show the message
        By pwField = By.id("input-password");
        String pwError = "Password must be between 4 and 20 characters!";
        validateHeightAndWidth(pwField, expectedHeight, expectedWidth);
        validateInputData(pwField, 4, 20, pwError );

        //Password confirm field
        By pwConfirmField = By.id("input-confirm");
        validateHeightAndWidth(pwField, expectedHeight, expectedWidth);



    }

    public void validateHeightAndWidth(By fieldLocator, String expectedHeight, String expectedWidth){
        String actualFieldHeight = driver.findElement(fieldLocator).getCssValue("height");
        String actualFieldWidth = driver.findElement(fieldLocator).getCssValue("width");
        Assert.assertEquals(actualFieldHeight, expectedHeight);
        Assert.assertEquals(actualFieldWidth, expectedWidth);
    }

    private void validateInputData(By fieldLocator, int minLength, int maxLength, String expectedError) {
        // Các giá trị biên để kiểm tra

        String[] testCases = {
                generateString(minLength - 1),  // Dưới biên dưới (invalid)
                generateString(minLength),      // Biên dưới (valid)
                generateString(minLength + 1),  // Gần biên dưới (valid)
                generateString(maxLength - 1),  // Gần biên trên (valid)
                generateString(maxLength),      // Biên trên (valid)
                generateString(maxLength + 1)   // Trên biên trên (invalid)
        };

        String nameField = driver.findElement(fieldLocator).getAttribute("placeholder").toLowerCase().replaceAll(" ","");
        // Thực hiện kiểm tra với từng giá trị
        for (int i = 0; i < testCases.length; i++) {
            driver.findElement(fieldLocator).clear();
            driver.findElement(fieldLocator).sendKeys(testCases[i]);
            driver.findElement(By.xpath("//input[@value='Continue']")).click(); // Hoặc click nút submit tùy giao diện
           // System.out.println("day la lan chay thu "+ i + "cua field "+nameField);
            if (i == 0 || i == 5) {

               Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-"+nameField+"']/following-sibling::div")).getText(), expectedError);

            } else {

                try {

                    Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-"+nameField+"']/following-sibling::div")).isDisplayed());
                } catch (NoSuchElementException e) {
                    Assert.assertTrue(true);


                }

            }
        }


    }
public String generateString(int length){
        return "a".repeat(length);
}


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
