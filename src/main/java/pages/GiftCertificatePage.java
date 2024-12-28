package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class GiftCertificatePage extends RootPage {
    ElementUtils elementUtils;
WebDriver driver;
    public GiftCertificatePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Gift Certificate']")
    private WebElement giftCertificatePageBreadcrumb;

    public boolean didWeNavigateToGiftCertificatePage() {
        return elementUtils.isElementDisplayed(giftCertificatePageBreadcrumb);
    }

}
