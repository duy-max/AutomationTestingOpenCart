package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
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
import java.util.HashMap;


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

    public static String takeScreenshotAndReturnPath(WebDriver driver, String pathToBCopied) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
        String destScreenshotPath = System.getProperty("user.dir") + pathToBCopied;
        System.out.println("day la path anh "+destScreenshotPath);
        try {
            FileHandler.copy(srcScreenshot, new File(destScreenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destScreenshotPath;
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

    public static Object[][] getTestData(MyXLSReader xls_received, String testName, String sheetName) {
        MyXLSReader xls = xls_received;
        String testCaseName = testName;
        String testDataSheet = sheetName;
        int testStartRowNumber = 1;
        while (!(xls.getCellData(testDataSheet, 1, testStartRowNumber).equals(testCaseName))) {
            testStartRowNumber++;
        }

        int columnStartRowNumber = testStartRowNumber + 1;
        int dataStartRowNumber = testStartRowNumber + 2;

        int rows = 0;
        while (!(xls.getCellData(testDataSheet, 1, dataStartRowNumber + rows).equals(""))) {
            rows++;
        }

        // Total number of columns in the required test
        int columns = 1;

        while (!(xls.getCellData(testDataSheet, columns, columnStartRowNumber).equals(""))) {
            columns++;
        }

        Object[][] obj = new Object[rows][1];
        HashMap<String, String> map = null;

        // Reading the data in the test
        for (int i = 0, row = dataStartRowNumber; row < dataStartRowNumber + rows; row++, i++) {
            map = new HashMap<String, String>();
            for (@SuppressWarnings("unused")
                 int j = 0, column = 1; column < columns; column++, j++) {
                String key = xls.getCellData(testDataSheet, column, columnStartRowNumber);
                String value = xls.getCellData(testDataSheet, column, row);
                map.put(key, value);
            }
            obj[i][0] = map;

        }
        return obj;

    }

    public static ExtentReports getExtentReport() {

        ExtentReports extentReport = new ExtentReports();

        File extentReportFile = new File(System.getProperty("user.dir") + "\\reports\\TNExtentReport.html");

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        ExtentSparkReporterConfig sparkConfig = sparkReporter.config();
        sparkConfig.setReportName("Tutorials Ninja Test Automation Results");
        sparkConfig.setDocumentTitle("TNER Results");

        extentReport.attachReporter(sparkReporter);
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));
        extentReport.setSystemInfo("Selenium WebDriver Version", "4.24.0");

        return extentReport;

    }


}
