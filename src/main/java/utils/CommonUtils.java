package utils;

import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class CommonUtils {
    public static String generateBrandNewEmail() {
        return new Date().toString().replaceAll("[ :+]", "") + "@gmail.com";
    }

    public static boolean compareImages(String actualImagePath, String expectedImagePath) throws IOException {
        // Đọc hai ảnh từ file
        BufferedImage actualImage = ImageIO.read(new File(actualImagePath));
        BufferedImage expectedImage = ImageIO.read(new File(expectedImagePath));

        // Tạo đối tượng ImageDiffer để so sánh
        ImageDiffer imageDiffer = new ImageDiffer();

        // Trả về kết quả so sánh, true khi 2 ảnh giống nhau, false thì ngược lại
        return !imageDiffer.makeDiff(actualImage, expectedImage).hasDiff();
    }
}
