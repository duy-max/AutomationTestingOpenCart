package tutorialsninja.tests.Register;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.Test;

public class TC_RF_002 {
    @Test
    public void verifyConfirmationEmail(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.amazon.in/");

        driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Need help')]")).click();
        driver.findElement(By.id("auth-fpp-link-bottom")).click();

        String email = "truongduy1717@gmail.com";
        String appPassCode = "hrzq lkqg afkn cfxd";
        String host = "imap.gmail.com";


        String expectedSubject = "amazon.in: Password recovery";
        String expectedFromEmail = "account-update@amazon.in";
        String expectedBodyContent = "Someone is attempting to reset the password of your account.";
        String link = null;


        driver.findElement(By.id("ap_email")).sendKeys(email);
        driver.findElement(By.id("continue")).click();

        System.out.println("Halting the program intentionally for 10 seconds.");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Code truy cập vào email do chatGPT viet
        // Cấu hình các thuộc tính
        Properties properties = new Properties();
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.ssl.enable", "true");
        properties.put("mail.imap.auth", "true");

        // Tạo phiên làm việc với email
        Session session = Session.getDefaultInstance(properties, null);

        try {
            // Kết nối tới email server
            Store store = session.getStore("imap");
            store.connect(host, email, appPassCode);

            // Mở hộp thư "INBOX"
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Tạo điều kiện lọc email chưa đọc và có subject là "amazon.in: Password recovery"
            FlagTerm unreadFlag = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            SubjectTerm subjectTerm = new SubjectTerm(expectedSubject);
            SearchTerm searchCondition = new AndTerm(unreadFlag, subjectTerm);

            // Tìm email thỏa mãn điều kiện
            Message[] messages = inbox.search(searchCondition);


            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];

                // Lấy thông tin tiêu đề (Subject)
                String subject = message.getSubject();

                // Lấy địa chỉ email của người gửi
                Address[] fromAddresses = message.getFrom();
                String fromEmail = fromAddresses != null && fromAddresses.length > 0 ?
                        ((InternetAddress) fromAddresses[0]).getAddress() : "Không xác định";

                //Lấy nội dung email
                String content = getTextFromMessage(message);

                //kiểm tra lại
                Assert.assertEquals(subject, expectedSubject);
                Assert.assertEquals(fromEmail, expectedFromEmail);
                Assert.assertTrue(content.contains(expectedBodyContent));

                //Lấy link "click here to deny"
                Document doc = Jsoup.parse(content);
                Elements linkElement = doc.select("a:contains(click here to deny)"); // Lọc thẻ <a> có chứa đoạn text này
                link = linkElement.attr("href");

            }

            // Đóng hộp thư và ngắt kết nối
            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //  click vào link trong email để tới trang Deny
        driver.navigate().to(link);
        Assert.assertTrue(driver.findElement(By.name("customerResponseDenyButton")).isDisplayed());

        driver.quit();
    }

    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("text/html")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) message.getContent();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    result.append(bodyPart.getContent());
                } else if (bodyPart.isMimeType("text/html")) {
                    result.append(bodyPart.getContent()); // Bạn có thể xử lý thêm nếu cần
                }
            }
            return result.toString();
        }
        return "";
    }

}
