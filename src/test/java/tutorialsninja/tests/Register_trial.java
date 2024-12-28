package tutorialsninja.tests;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LandingPage;
import tutorialsninja.base.Base;
import utils.CommonUtils;
import utils.MyXLSReader;

import java.util.HashMap;
import java.util.Properties;


public class Register_trial extends Base {
    public WebDriver driver;
    Properties prop;

    @BeforeMethod
    public void setup() {

        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);
        landingPage.clickOnMyAccount();
        registerPage = landingPage.selectRegisterOption();

    }

    @Test(priority = 1, dataProvider = "passwordSupplier")
    public void Test_trial(HashMap<String, String> hMap) {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephoneNumber(prop.getProperty("telephoneNumber"));
        registerPage.selectYesNewsletterOption();
        registerPage.selectPrivacyPolicy();
        registerPage.enterPassword(hMap.get("Passwords"));
        registerPage.enterConfirmPassword(hMap.get("Passwords"));
        registerPage.clickOnContinueButton();

        String warningMessage = "Password entered is not matching the Complexity standards";

        boolean state;

        try {
            state = registerPage.getPasswordWarning().equals(warningMessage);
        } catch (NoSuchElementException e) {
            state = false;
        }

        Assert.assertTrue(state);
        Assert.assertFalse(registerPage.didWeNavigateToRegisterAccountPage());

        driver.quit();

    }

    @DataProvider(name = "passwordSupplier")
    public Object[][] supplyPasswords() {
        myXLSReader = new MyXLSReader(System.getProperty("user.dir") + "\\src\\test\\resources\\TutorialsNinja.xlsx");
        Object[][] data = CommonUtils.getTestData(myXLSReader, "RegsiterTestSupplyPasswords", "data");
        return data;
    }
}
