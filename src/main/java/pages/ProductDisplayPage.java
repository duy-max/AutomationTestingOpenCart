
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.ElementUtils;

public class ProductDisplayPage extends RootPage {

    WebDriver driver;
    ElementUtils elementUtils;
    public ProductDisplayPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(className="thumbnails")
    private WebElement thumbnailsSection;

    @FindBy(linkText="product comparison")
    private WebElement productComparisonLink;


    @FindBy(xpath="//button[@*='Compare this Product']")
    private WebElement compareThisProduct;


    public String getToolTipForThisProductOption(){
        return elementUtils.getToolTip(compareThisProduct);
    }

    public void selectCompareProductOption(){
        elementUtils.clickOnElement(compareThisProduct);
    }

    public boolean didWeNavigateToProductDisplayPage() {
        return elementUtils.isElementDisplayed(thumbnailsSection);
    }

    public ProductComparisonPage clickOnProductComparisonLink() {

        elementUtils.clickOnElement(productComparisonLink);

        return new ProductComparisonPage(driver);
    }

}
