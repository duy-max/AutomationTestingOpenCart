package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

public class CategoryPage extends RootPage {
    ElementUtils elementUtils;
    WebDriver driver;

    public CategoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//li[2]")
    private WebElement categoryBreadcrumb;

    public boolean didWeNavigateToCategoryBreadcrumb() {
        return elementUtils.isElementDisplayed(categoryBreadcrumb);
    }

}
