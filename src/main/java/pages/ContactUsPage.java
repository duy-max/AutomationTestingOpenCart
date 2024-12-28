package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class ContactUsPage {
    WebDriver driver;
    ElementUtils elementUtils;
    public ContactUsPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Contact Us']")
    private WebElement contactUsBreadcrumb;

    public boolean didWeNavigateToContactUsPage() {
        return elementUtils.isElementDisplayed(contactUsBreadcrumb);
    }
}
