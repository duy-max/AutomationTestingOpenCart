package tutorialsninja.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.AccountSuccessPage;
import pages.LandingPage;
import pages.RegisterPage;
import tutorialsninja.base.Base;
import utils.CommonUtils;

import java.time.Duration;

public class Register extends Base {
    WebDriver driver;
    LandingPage landingPage;
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;
    AccountPage accountPage;

    @BeforeMethod
    public void setup() {
        driver = openBrowserAndApplication();
        landingPage = new LandingPage(driver);
        landingPage.clickMyAccountDropMenu();
        registerPage = landingPage.selectRegisterOption();
    }

    @Test(priority = 1)
    public void verifyRegisteringWithMandatoryFields() {

        registerPage.enterFirstName("Duy");
        registerPage.enterLastName("Truong");
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone("12345678");
        registerPage.enterPassword("123456");
        registerPage.enterPasswordConfirm("123456");
        registerPage.clickYesNewsletterOption();
        registerPage.clickPrivacyPolicyFiled();
        accountSuccessPage = registerPage.clickContinueButton();


        Assert.assertTrue(accountSuccessPage.isUserLoggedIn());

        String expectedHeading = "Your Account Has Been Created!";

        Assert.assertEquals(accountSuccessPage.getPageHeadingText(), expectedHeading);

        String actualProperDetailsOne = "Congratulations! Your new account has been successfully created!";
        String actualProperDetailsTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String actualProperDetailsThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String actualProperDetailsFour = "contact us";


        Assert.assertTrue(accountSuccessPage.getPageContentText().contains(actualProperDetailsOne));
        Assert.assertTrue(accountSuccessPage.getPageContentText().contains(actualProperDetailsTwo));
        Assert.assertTrue(accountSuccessPage.getPageContentText().contains(actualProperDetailsThree));
        Assert.assertTrue(accountSuccessPage.getPageContentText().contains(actualProperDetailsFour));

        accountPage = accountSuccessPage.clickContinueButton();
        Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
    }

    @Test(priority = 3)
    public void verifyRegisterAccountWithAllFields() {

        registerPage.enterFirstName("duy");
        registerPage.enterLastName("truong");
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone("1234567890");
        registerPage.enterPassword("12345");
        registerPage.enterPasswordConfirm("12345");
        registerPage.clickYesNewsletterOption();
        registerPage.clickPrivacyPolicyFiled();

        accountSuccessPage = registerPage.clickContinueButton();
        Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
        Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());

        String expectedProperDetailsOne = "Your Account Has Been Created!";
        String expectedProperDetailsTwo = "Congratulations! Your new account has been successfully created!";
        String expectedProperDetailsThree = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String expectedProperDetailsFour = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String expectedProperDetailsFive = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please ";
        String expectedProperDetailsSix = "contact us";

        String actualProperDetails = accountSuccessPage.getPageContentText();

        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsOne));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsTwo));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsThree));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFour));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFive));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsSix));

        accountPage = accountSuccessPage.clickContinueButton();

        Assert.assertTrue(accountPage.didWeNavigateToAccountPage());


    }
@Test(priority = 4)
    public void verifyRegistringAccountWithoutFillFields() {
        registerPage.clickContinueButton();

        String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
        String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
        String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
        String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
        String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
        String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";

        Assert.assertEquals(registerPage.getFirstNameWarning(),expectedFirstNameWarning);
        Assert.assertEquals(registerPage.getLastNameWarning(), expectedLastNameWarning);
        Assert.assertEquals(registerPage.getEmailWarning(),expectedEmailWarning);
        Assert.assertEquals(registerPage.getTelephoneWarning(),expectedTelephoneWarning);
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedPasswordWarning);
        Assert.assertEquals(registerPage.getPrivacyPolicyWarning(), expectedPrivacyPolicyWarning);

        driver.quit();
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
