package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.ElementUtils;
public class ProductComparisonPage extends RootPage{
    WebDriver driver;
    ElementUtils elementUtils;

    public ProductComparisonPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Product Comparison']")
    private WebElement productComparisonPageBreadcrumb;


    @FindBy(xpath="//strong[text()='Apple Cinema 30\"' or text()='HP LP3065' or text()='MacBook']")
    private WebElement productDetailOfHP;

    public boolean didWeNavigateToProductComparisonPage() {
        return elementUtils.isElementDisplayed(productComparisonPageBreadcrumb);
    }

    public boolean didDetailOfProductGotAddedForComparison(){
       return elementUtils.isElementDisplayed(productDetailOfHP);
    }
}
