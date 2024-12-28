package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class CommonUtils {
    public static String generateBrandNewEmail() {
        return new Date().toString().replaceAll("[ :+]", "") + "@gmail.com";
    }


    public static Properties loadProperties() {
        Properties prop = new Properties();
        try {
            FileReader fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties");
            prop.load(fr);
        } catch(IOException e){
            e.printStackTrace();
        } ;
        return prop;
    }

    public static void updateProperty(String key, String value) {
        Properties properties = loadProperties();
        try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties")) {
            properties.setProperty(key, value);
            properties.store(fos, "Updated property: " + key);
        } catch (IOException e) {
            System.out.println("Không thể ghi vào file properties: " + e.getMessage());
        }
    }

    public static String getTextFromMessage(Message message) throws Exception {
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


    public static String validEmailRandomizeGenerator() {
        String[] validEmails = {"duyabc21@gmail.com", "duyabc22@gmail.com", "duyabc23@gmail.com",
                "duyabc24@gmail.com", "duyabc25@gmail.com"};
        Random random = new Random();
        int index = random.nextInt(validEmails.length);
        return validEmails[index];
    }

    public static WebDriver takeScreenshot(WebDriver driver, String pathToBCopied) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(srcScreenshot, new File(System.getProperty("user.dir") + pathToBCopied));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) {
        BufferedImage acutualBImg = null;
        BufferedImage expectedBImg = null;
        try {
            acutualBImg = ImageIO.read(new File(actualImagePath));
            expectedBImg = ImageIO.read(new File(expectedImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageDiffer imgDiffer = new ImageDiffer();
        ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);

        return imgDifference.hasDiff();

    }

    public String generateString(int length){
        return "a".repeat(length);
    }



}
