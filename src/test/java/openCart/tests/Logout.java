package openCart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import openCart.base.Base;
import utils.CommonUtils;

import java.util.Properties;

public class Logout extends Base {
    public WebDriver driver;
    Properties prop;


    @BeforeMethod
    public void setup() {

        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);

    }

    @Test(priority = 1)
    public void verifyLoggingOutUsingMyAccountDropMenu() {
            loginPage = landingPage.navigateToLoginPage();
            accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"), prop.getProperty("validPassword") );
           //chuyển sang trang khác thì cần phải truyền driver vào
            headerOptions = new HeaderOptions(accountPage.getDriver());
            headerOptions.clickOnMyAccountDropMenu();
            logoutPage = headerOptions.selectLogoutOption();
            Assert.assertTrue(logoutPage.didWeNavigateToAccountLogoutPage());

            headerOptions = new HeaderOptions(logoutPage.getDriver());
            Assert.assertTrue(headerOptions.isLoginOptionAvailable());
            headerOptions.clickOnMyAccountDropMenu();
            logoutPage = new LogoutPage(headerOptions.getDriver());
            landingPage = logoutPage.clickOnContinueButton();
            Assert.assertEquals(getPageURL(landingPage.getDriver()), prop.getProperty("landingPageURL"));

    }

    @Test(priority = 2)
    public void verifyLoggingOutUsingLogoutRightColumnOption() {
        loginPage = landingPage.navigateToLoginPage();
        accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"), prop.getProperty("validPassword"));
        rightColumnOptions = new RightColumnOptions(accountPage.getDriver());
        logoutPage  = rightColumnOptions.clickOnRightSideLogoutOption();
        Assert.assertTrue(logoutPage.didWeNavigateToAccountLogoutPage());

        headerOptions = new HeaderOptions(logoutPage.getDriver());
        Assert.assertTrue(headerOptions.isLoginOptionAvailable());
        headerOptions.clickOnMyAccountDropMenu();
        logoutPage = new LogoutPage(headerOptions.getDriver());
        landingPage = logoutPage.clickOnContinueButton();
        Assert.assertEquals(getPageURL(landingPage.getDriver()), prop.getProperty("landingPageURL"));

    }

    @Test(priority = 3)
    public void verifyLoggingOutAndBrowsingBack() {
        loginPage = landingPage.navigateToLoginPage();
        accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"), prop.getProperty("validPassword"));
        rightColumnOptions = new RightColumnOptions(accountPage.getDriver());
        logoutPage  = rightColumnOptions.clickOnRightSideLogoutOption();
        Assert.assertTrue(logoutPage.didWeNavigateToAccountLogoutPage());
        driver = navigateBack(logoutPage.getDriver());
        driver = refreshPage(driver);
        loginPage  = new LoginPage(driver);
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

    }
    @Test(priority = 4)
    public void verifyThereIsNoLogoutOptionBeforeLogin() {
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.clickOnMyAccountDropMenu();
        Assert.assertFalse(headerOptions.isLogoutOptionAvailable());
    }

    @Test(priority = 5)
    public void verifyThereIsNoRightColumnLogoutOptionBeforeLogin() {
        registerPage = landingPage.navigateToRegisterPage();
        rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
        Assert.assertFalse(rightColumnOptions.isLogoutRightColumnOptionAvailable());
    }
    @Test(priority = 6)
    public void verifyLoginAfterLogout() {
        loginPage = landingPage.navigateToLoginPage();
        accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
        headerOptions = new HeaderOptions(accountPage.getDriver());
        headerOptions.clickOnMyAccountDropMenu();
        logoutPage = headerOptions.selectLogoutOption();
        headerOptions = new HeaderOptions(logoutPage.getDriver());
        headerOptions.clickOnMyAccountDropMenu();
        loginPage = headerOptions.selectLoginOption();
        accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
        Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
    }

    @Test(priority = 7)
    public void verifyBreadcrumbTitleHeadingAndURLOfAccountLogoutPage() {

        loginPage = landingPage.navigateToLoginPage();
        accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
        headerOptions = new HeaderOptions(accountPage.getDriver());
        headerOptions.clickOnMyAccountDropMenu();
        logoutPage = headerOptions.selectLogoutOption();
        Assert.assertEquals(getPageTitle(logoutPage.getDriver()), prop.getProperty("logoutPageTitle"));
        Assert.assertEquals(getPageURL(logoutPage.getDriver()),prop.getProperty("logoutPageURL"));
        Assert.assertEquals(logoutPage.getPageHeading(),prop.getProperty("logoutPageHeading"));
        Assert.assertTrue(logoutPage.didWeNavigateToAccountLogoutPage());

    }

    @Test(priority = 8)
    public void verifyUIOfLogoutOptionAndAccountLogoutPage() {

        loginPage = landingPage.navigateToLoginPage();
        accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
        headerOptions = new HeaderOptions(accountPage.getDriver());
        headerOptions.clickOnMyAccountDropMenu();
        if(prop.getProperty("browserName").equals("firefox")) {
            CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualFirefoxLogoutOption.png");
            Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualFirefoxLogoutOption.png",System.getProperty("user.dir")+"\\Screenshots\\expectedFirefoxLogoutOption.png"));
        }else if(prop.getProperty("browserName").equals("chrome")) {
            CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualChromeLogoutOption.png");
            Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualChromeLogoutOption.png",System.getProperty("user.dir")+"\\Screenshots\\expectedChromeLogoutOption.png"));
        }else if(prop.getProperty("browserName").equals("edge")) {
            CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualEdgeLogoutOption.png");
            Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualEdgeLogoutOption.png",System.getProperty("user.dir")+"\\Screenshots\\expectedEdgeLogoutOption.png"));
        }
        logoutPage = headerOptions.selectLogoutOption();
        if(prop.getProperty("browserName").equals("firefox")) {
            CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualFirefoxAccountLogoutPage.png");
            Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualFirefoxAccountLogoutPage.png",System.getProperty("user.dir")+"\\Screenshots\\expectedFirefoxAccountLogoutPage.png"));
        }else if(prop.getProperty("browserName").equals("chrome")) {
            CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualChromeAccountLogoutPage.png");
            Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualChromeAccountLogoutPage.png",System.getProperty("user.dir")+"\\Screenshots\\expectedChromeAccountLogoutPage.png"));
        }else if(prop.getProperty("browserName").equals("edge")) {
            CommonUtils.takeScreenshot(headerOptions.getDriver(),"\\Screenshots\\actualEdgeAccountLogoutPage.png");
            Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualEdgeAccountLogoutPage.png",System.getProperty("user.dir")+"\\Screenshots\\expectedEdgeAccountLogoutPage.png"));
        }

    }

    @Test(priority = 9)
    public void verifyAccountLogoutFunctionality() {

        loginPage = landingPage.navigateToLoginPage();
        accountPage = loginPage.loginToApplication(prop.getProperty("existingEmail"),prop.getProperty("validPassword"));
        headerOptions = new HeaderOptions(accountPage.getDriver());
        headerOptions.clickOnMyAccountDropMenu();
        logoutPage = headerOptions.selectLogoutOption();
        Assert.assertTrue(logoutPage.didWeNavigateToAccountLogoutPage());
        landingPage = logoutPage.clickOnContinueButton();
        Assert.assertEquals(getPageURL(landingPage.getDriver()),prop.getProperty("landingPageURL"));
        headerOptions = new HeaderOptions(landingPage.getDriver());
        headerOptions.clickOnMyAccountDropMenu();
        Assert.assertTrue(headerOptions.isLoginOptionAvailable());

    }
    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }

}
