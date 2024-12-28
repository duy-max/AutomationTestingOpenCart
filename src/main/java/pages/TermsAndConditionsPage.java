package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class TermsAndConditionsPage extends RootPage {
    ElementUtils elementUtils;
WebDriver driver;
    public TermsAndConditionsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Terms & Conditions']")
    private WebElement termsAndConditionsBreadcrumb;

    public boolean didWeNavigateToTermsAndConditionsPage() {
        return elementUtils.isElementDisplayed(termsAndConditionsBreadcrumb);
    }
}
