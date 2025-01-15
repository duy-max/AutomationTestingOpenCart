package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class OrderSuccess extends RootPage {

    ElementUtils elementUtils;
    WebDriver driver;
    public OrderSuccess(WebDriver driver)  {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Success']")
    private WebElement orderSuccessPageBreadcrumb;

    @FindBy(xpath = "//h1[normalize-space()='Your order has been placed!']")
    private WebElement orderSuccessHeading;

    public String getOrderSuccessHeading(){
        return elementUtils.getTextOfElement(orderSuccessHeading);
    }

    public boolean didWeNavigateToOrderSuccessPage() {
        return elementUtils.isElementDisplayed(orderSuccessPageBreadcrumb);
    }
}
