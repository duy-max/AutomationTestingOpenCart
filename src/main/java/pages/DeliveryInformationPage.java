package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class DeliveryInformationPage extends RootPage {
    ElementUtils elementUtils;
WebDriver driver;
    public DeliveryInformationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Delivery Information']")
    private WebElement deliveryInformationPageBreadcrumb;

    public boolean didWeNavigateToDeliveryInformationPage() {
        return elementUtils.isElementDisplayed(deliveryInformationPageBreadcrumb);
    }
}
