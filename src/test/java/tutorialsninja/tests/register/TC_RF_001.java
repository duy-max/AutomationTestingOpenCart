package tutorialsninja.tests.register;

import org.openqa.selenium.WebDriver;
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

public class TC_RF_001 extends Base {

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

    @Test
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

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
