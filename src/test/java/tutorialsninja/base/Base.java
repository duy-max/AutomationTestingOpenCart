package tutorialsninja.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import pages.*;
import utils.CommonUtils;

import java.time.Duration;
import java.util.Properties;
import utils.MyXLSReader;

public class Base {
    WebDriver driver;
    Properties prop;
    public MyXLSReader myXLSReader;
    public LandingPage landingPage;
    public RegisterPage registerPage;
    public AccountSuccessPage accountSuccessPage;
    public AccountPage accountPage;
    public NewsletterPage newsletterPage;
    public LoginPage loginPage;
    public EditAccountInformationPage editAccountInformationPage;
    public ContactUsPage contactUsPage;
    public ShoppingCartPage shoppingCartPage;
    public SearchPage searchPage;
    public ForgottenPasswordPage forgottenPasswordPage;
    public AboutUsPage aboutUsPage;
    public DeliveryInformationPage deliveryInformationPage;
    public PrivacyPolicyPage privacyPolicyPage;
    public TermsAndConditionsPage termsAndConditionsPage;
    public ProductReturnsPage productReturnsPage;
    public SiteMapPage siteMapPage;
    public BrandPage brandPage;
    public GiftCertificatePage giftCertificatePage;
    public AffiliateLoginPage affiliateLoginPage;
    public SpecialOffersPage specialOffersPage;
    public HeaderOptions headerOptions;
    public RightColumnOptions rightColumnOptions;
    public FooterOptions footerOptions;
    public ChangePasswordPage changePasswordPage;
    public LogoutPage logoutPage;

    public WebDriver openBrowserAndApplication() {

        prop = CommonUtils.loadProperties();

        String browserName = prop.getProperty("browserName");
        switch (browserName) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(prop.getProperty("appURL"));
        return driver;
    }

    public void closeBrowser(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    public void pressKeyMultipleTimes(WebDriver driver, Keys keyName, int times){
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++) {
            actions.sendKeys(keyName).perform();
        }
    }

    public void enterDetailsIntoLoginPageFields(WebDriver driver){
        Actions actions = new Actions(driver);
        Properties prop = CommonUtils.loadProperties();
        actions.sendKeys(prop.getProperty("existingEmail")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("validPassword"))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER).build().perform();
    }

    public WebDriver navigateBack(WebDriver driver){
        driver.navigate().back();
        return driver;
    }

    public String getHTMLCodeOfThePage(WebDriver driver){
        return driver.getPageSource();
    }

    public String getPageURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }
    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public WebDriver  refreshPage(WebDriver driver) {
        System.out.println("truoc refresh");
        driver.navigate().refresh();
        System.out.println("sau refresh");
        return driver;
    }

    public WebDriver navigateToRegisterPage(WebDriver driver,String URL) {
        driver.navigate().to(URL);
        return driver;
    }



    public void enterDetailsIntoRegisterAccountPageFields(WebDriver driver) {

        prop = CommonUtils.loadProperties();
        Actions actions = new Actions(driver);
        actions.sendKeys(prop.getProperty("firstName")).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
                .pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("lastName")).sendKeys(Keys.TAB)
                .pause(Duration.ofSeconds(1)).sendKeys(CommonUtils.generateBrandNewEmail()).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("telephoneNumber"))
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(prop.getProperty("validPassword")).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
                .pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("validPassword")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.LEFT).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.SPACE).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER).pause(Duration.ofSeconds(3)).build().perform();


    }

//    public static void waitForPageLoad(WebDriver driver) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        while (!js.executeScript("return document.readyState").toString().equals("complete")) {
//            // Chờ trang tải hoàn toàn
//        }
//    }

}
