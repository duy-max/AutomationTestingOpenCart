package tutorialsninja.tests.register;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonUtils;

import java.sql.*;
import java.time.Duration;

public class TC_RF_015 {

    @Test
    public void verifyDataTestingOfRegisteringAccount() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("http://localhost/opencart/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        String firstNameInputData = "An";
        driver.findElement(By.id("input-firstname")).sendKeys(firstNameInputData);

        String lastNameInputData = "Nguyen";
        driver.findElement(By.id("input-lastname")).sendKeys(lastNameInputData);

        String emailInputData = CommonUtils.generateBrandNewEmail();
        driver.findElement(By.id("input-email")).sendKeys(emailInputData);

        String passwordInputData = "12345";
        driver.findElement(By.id("input-password")).sendKeys(passwordInputData);

        // driver.findElement(By.xpath("//input[@id='input-newsletter']")).click();
        WebElement newsletter = driver.findElement(By.xpath("//input[@id='input-newsletter']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsletter);
        WebElement policyAgree = driver.findElement(By.xpath("//input[@name='agree']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", policyAgree);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='Continue']")));

        String firstNameStoredInDB = null;
        String lastNameStoredInDB = null;
        String emailStoredInDB = null;
        try {
            // Thông tin kết nối
            String url = "jdbc:mysql://localhost:3306/opencart_db";
            String username = "root";
            String password = "";

            // Đăng ký driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Tạo kết nối
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully!");

            // Tạo statement
            Statement stmt = conn.createStatement();

            // Thực thi truy vấn
            String sql = "SELECT * FROM oc_customer";
            ResultSet rs = stmt.executeQuery(sql);


            // Đọc dữ liệu
            while (rs.next()) {
                firstNameStoredInDB = rs.getString("firstname");
                lastNameStoredInDB = rs.getString("lastname");
                emailStoredInDB = rs.getString("email");

            }

            // Đóng kết nối
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        Assert.assertEquals(firstNameStoredInDB, firstNameInputData);
        Assert.assertEquals(lastNameStoredInDB, lastNameInputData);
        Assert.assertEquals(emailStoredInDB, emailInputData);

    }


}
