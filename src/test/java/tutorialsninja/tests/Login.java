package tutorialsninja.tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tutorialsninja.base.Base;
import utils.CommonUtils;

import java.util.Properties;

public class Login extends Base {
    WebDriver driver;
    Properties prop;
    LandingPage landingPage;
    LoginPage loginPage;
    AccountPage accountPage;
    ForgottenPasswordPage forgottenPasswordPage;
    LogoutPage logoutPage;

    @BeforeMethod
    public void setup() {
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);
        landingPage.clickMyAccountDropMenu();
        loginPage = landingPage.selectLoginOption();
    }
    @Test(priority = 1)
    public void verifyLoginWithValidCredentials() {
        Assert.assertTrue(loginPage.isLoginBreadcrumbDisplayed());
        loginPage.enterEmail(prop.getProperty("existingEmail"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        accountPage = loginPage.clickLoginButton();
        Assert.assertTrue(accountPage.isUserLoggedIn());
        Assert.assertTrue(accountPage.didWeNavigateToAccountPage());

    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials() {
        Assert.assertTrue(loginPage.isLoginBreadcrumbDisplayed());
        loginPage.enterEmail(CommonUtils.generateBrandNewEmail());
        loginPage.enterPassword(prop.getProperty("invalidPassword"));
        loginPage.clickLoginButton();
        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {
        Assert.assertTrue(loginPage.isLoginBreadcrumbDisplayed());
        loginPage.enterEmail(CommonUtils.generateBrandNewEmail());
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickLoginButton();
        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {
        Assert.assertTrue(loginPage.isLoginBreadcrumbDisplayed());
        loginPage.enterEmail(CommonUtils.validEmailRandomizeGenerator());
        loginPage.enterPassword(prop.getProperty("invalidPassword"));
        loginPage.clickLoginButton();
        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

    }

    @Test(priority = 5)
    public void verifyLoginWithoutCredentials() {
        Assert.assertTrue(loginPage.isLoginBreadcrumbDisplayed());
        loginPage.clickLoginButton();
        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

    }

    @Test(priority = 6)
    public void verifyForgottenPasswordLinkOnLoginPage() {
        Assert.assertTrue(loginPage.isLoginBreadcrumbDisplayed());
        Assert.assertTrue(loginPage.isForgottenPasswordLinkDisplayed());
        forgottenPasswordPage = loginPage.clickForgottenPasswordLink();
        Assert.assertTrue(forgottenPasswordPage.isForgottenPasswordPageDisplayed());

    }
    @Test(priority = 7)
    public void verifyLoggingIntoTheApplicationUsingKeyboardKeys() {
        Assert.assertTrue(loginPage.isLoginBreadcrumbDisplayed());
        pressKeyMultipleTimes(driver, Keys.TAB, 23);
        enterDetailsIntoLoginPageFields(driver);
        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.isUserLoggedIn());
        Assert.assertTrue(accountPage.didWeNavigateToAccountPage());


    }


    @Test(priority = 8)
    public void verifyLoginFieldsPlaceholders() {

        String expectedEmailPlaceholder = "E-Mail Address";
        String expectedPasswordPlaceholder = "Password";
        Assert.assertEquals(loginPage.getEmailPlaceholder(), expectedEmailPlaceholder);
        Assert.assertEquals(loginPage.getPasswordPlaceholder(), expectedPasswordPlaceholder);


    }

    @Test(priority = 9)
    public void verifyBrowserBackAfterLogin() {

        loginPage.enterEmail(prop.getProperty("existingEmail"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickLoginButton();
        navigateBack(driver);
        loginPage = new LoginPage(driver);
        accountPage = loginPage.clickOnMyAccountRightColumnOption();
        Assert.assertTrue(accountPage.isUserLoggedIn());

    }

    @Test(priority = 10)
    public void verifyBrowserBackAfterLoggingOut() {
        loginPage.enterEmail(prop.getProperty("existingEmail"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        accountPage = loginPage.clickLoginButton();
        Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
        logoutPage = accountPage.clickLogoutOption();
        Assert.assertTrue(logoutPage.isLogoutPageDisplayed());
        navigateBack(driver);
        Assert.assertTrue(accountPage.isUserLoggedIn());

    }

    @Test(priority = 11)
    public void verifyLoginWithInactiveCredentials() {

        loginPage.enterEmail(prop.getProperty("inactiveEmail"));
        loginPage.enterPassword(prop.getProperty("validPassword"));
        loginPage.clickLoginButton();
        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

    }

    @Test(priority = 12)
    public void verifyNumberOfUnsuccessfulLoginAttempts() throws InterruptedException {

        loginPage.enterEmail(CommonUtils.generateBrandNewEmail());
        loginPage.enterPassword(prop.getProperty("validPassword"));
        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
        loginPage.clickLoginButton();
        expectedWarning = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);

    }
    @Test(priority = 13)
    public void verifyTextEnteredIntoPasswordFieldIsToggledToHideItsVisibility() {

        String expectedType = "password";
        Assert.assertEquals(loginPage.getPasswordFieldType(), expectedType);

    }

    @Test(priority = 14)
    public void verifyCopyingOfTextEnteredIntoPasswordField() {

        String passwordText = prop.getProperty("samplePassword");
        loginPage.enterPassword(passwordText);
        loginPage.selectPasswordFieldTextAndCopy();
        loginPage.pasteCopiedTextIntoEmailField();
        Assert.assertNotEquals(loginPage.getTextFromEmailField(), passwordText);

    }

    //Test Failed
    @Test(priority = 15)
    public void verifyPasswordIsStoredInHTMLCodeOfThePage() {

        String passwordText = prop.getProperty("samplePassword");
        loginPage.enterPassword(passwordText);
        Assert.assertFalse(getHTMLCodeOfThePage(driver).contains(passwordText));
        loginPage.clickLoginButton();
        Assert.assertFalse(getHTMLCodeOfThePage(driver).contains(passwordText));

    }


    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
