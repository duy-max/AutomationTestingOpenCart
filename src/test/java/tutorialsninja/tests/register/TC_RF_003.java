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
import utils.CommonUtils;
import tutorialsninja.base.Base;

public class TC_RF_003 extends Base {
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

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
