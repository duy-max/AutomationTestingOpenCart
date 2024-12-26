package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class CommonUtils {
    public static String generateBrandNewEmail() {
        return new Date().toString().replaceAll("[ :+]", "") + "@gmail.com";
    }

    //Do claude ai viết
    public static boolean compareImages(String actualImagePath, String expectedImagePath) throws IOException {
        // Đọc hai ảnh từ file
        BufferedImage actualImage = ImageIO.read(new File(actualImagePath));
        BufferedImage expectedImage = ImageIO.read(new File(expectedImagePath));

        // Tạo đối tượng ImageDiffer để so sánh
        ImageDiffer imageDiffer = new ImageDiffer();

        // Trả về kết quả so sánh, true khi 2 ảnh giống nhau, false thì ngược lại
        return !imageDiffer.makeDiff(actualImage, expectedImage).hasDiff();
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


}
