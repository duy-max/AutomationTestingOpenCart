package pages.root;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ProductComparisonPage;
import pages.ShoppingCartPage;
import utils.ElementUtils;

public class RootPage {
    private WebDriver driver;
    ElementUtils elementUtils;
    public RootPage(WebDriver driver) {
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
    private WebElement successMessage;

    @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
    private WebElement failMessage;



    @FindBy(linkText="product comparison")
    private WebElement productCompareLink;

    @FindBy(xpath = "//a[normalize-space()='shopping cart']")
    private WebElement shoppingCartLink;

    @FindBy(xpath="//button[@*='Compare this Product']")
    private WebElement compareThisProduct;

    @FindBy(id="list-view")
    private WebElement listViewOption;

    @FindBy(id="grid-view")
    private WebElement gridViewOption;

    public ProductComparisonPage clickOnProductComparisonLink(){
        elementUtils.clickOnElement(productCompareLink);
        return new ProductComparisonPage(driver);
    }

    public ShoppingCartPage clickOnShoppingCartLink(){
        elementUtils.clickOnElement(shoppingCartLink);
        return new ShoppingCartPage(driver);
    }

    public String getToolTipForThisProductOption(){
        return elementUtils.getToolTip(compareThisProduct);
    }

    public void selectCompareThisProductOption() {
        elementUtils.clickOnElement(compareThisProduct);
    }

    public void selectListViewOption() {
        elementUtils.clickOnElement(listViewOption);
    }

    public void selectGridViewOption() {
        elementUtils.clickOnElement(gridViewOption);
    }

    public boolean isTextMatching(String expectedText, String actualText) {
        boolean b = false; // Mặc định là false
        if (actualText != null && expectedText != null) { // Kiểm tra null trước
            b = actualText.equals(expectedText); // Gán kết quả so sánh
        }
        return b; // Trả về giá trị cuối cùng
    }

    public String getSuccessMessage() {
        String fullText = elementUtils.getTextOfElement(successMessage);
        return fullText.replaceAll("\n","").substring(0, fullText.length() -2);
    }

    public String getFailMessage() {
        String fullText = elementUtils.getTextOfElement(failMessage);
        return fullText.replaceAll("\n","").substring(0, fullText.length() -2);
    }

}
