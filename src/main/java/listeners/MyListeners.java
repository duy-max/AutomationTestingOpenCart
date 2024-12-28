package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.CommonUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class MyListeners implements ITestListener {
    ExtentReports extentReport;
    ExtentTest extentTest;
    @Override
    public void onTestSuccess(ITestResult result) {
       extentTest.log(Status.PASS, result.getName()+"test got passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL,result.getName()+" test got failed");
        WebDriver driver = null;
        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String screentshotPath = CommonUtils.takeScreenshotAndReturnPath(driver,"\\Screenshots\\"+result.getName()+".png");
        extentTest.addScreenCaptureFromPath(screentshotPath);
        extentTest.log(Status.INFO,result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP,result.getName()+" test got skipped");
    }

    @Override
    public void onStart(ITestContext context) {
       extentReport =  CommonUtils.getExtentReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        File extentReportFile = new File(System.getProperty("user.dir") + "\\reports\\TNExtentReport.html");
        try {
            Desktop.getDesktop().browse(extentReportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
       extentTest =  extentReport.createTest(result.getName());
        extentTest.log(Status.INFO, result.getName() + "test execution start");
    }

}
