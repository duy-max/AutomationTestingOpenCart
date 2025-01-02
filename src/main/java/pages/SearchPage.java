package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;
import utils.ElementUtils;

import java.util.List;

public class SearchPage extends RootPage {
    ElementUtils elementUtils;
    WebDriver driver;
    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
    private WebElement searchBreadcrumb;

    @FindBy(linkText = "HP LP3065")
    private WebElement existingProduct;

    @FindBy(xpath = "//a[contains(text(), 'LP3065')]")
    private WebElement exisPro;

    @FindBy(linkText = "iMac")
    private WebElement iMacProduct;

    @FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
    private WebElement noProductMessage;

    @FindBy(xpath = "//div[@class='product-thumb']")
    private List<WebElement> numberOfProducts;

    @FindBy(id = "input-search")
    private WebElement searchCriteriaField;

    @FindBy(id = "button-search")
    private WebElement searchButton;

    @FindBy(id = "description")
    private WebElement searchInProductDescriptionCheckboxBoxField;

    @FindBy(xpath = "//label[normalize-space()='Search in subcategories']")
    private WebElement searchInSubCategoriesCheckboxField;

    @FindBy(name="category_id")
    private WebElement categoryDropdownField;

    @FindBy(id="list-view")
    private WebElement listViewOption;

    @FindBy(id="grid-view")
    private WebElement gridViewOption;

    @FindBy(xpath="//span[text()='Add to Cart']")
    private WebElement addToCartOption;

    @FindBy(xpath = "//button[@*='Add to Wish List']")
    private WebElement addToWishListOption;

    @FindBy(xpath="//button[@*='Compare this Product']")
    private WebElement compareThisProduct;

    @FindBy(xpath = "//div[@class='product-thumb']//img")
    private WebElement productImage;

    @FindBy(id = "compare-total")
    private WebElement productCompareLink;

    @FindBy(id = "input-sort")
    private WebElement sortByDropdownField;

    @FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[1]")
    private WebElement firstProductInSearchResults;

    @FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[2]")
    private WebElement secondProductInSearchResults;

    @FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[3]")
    private WebElement thirdProductInSearchResults;

    @FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[4]")
    private WebElement fourthProductInSearchResults;

    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement searchPageHeading;

    @FindBy(linkText="product comparison")
    private WebElement productComparisonLink;

    public void selectOptionFromCategoryDropdownField(String value) {
        elementUtils.selectOptionInDropDownFieldUsingVisibleText(categoryDropdownField, value);
    }

    public void selectOptionInSortByDropdownField(String sortOption) {
        elementUtils.selectOptionInDropDownFieldUsingVisibleText(sortByDropdownField, sortOption);
    }

    public void selectSearchInProductDescriptionCheckboxBoxField() {
        elementUtils.clickOnElement(searchInProductDescriptionCheckboxBoxField);
    }

    public void selectSearchInSubCategoriesCheckboxField() {
        elementUtils.clickOnElement(searchInSubCategoriesCheckboxField);
    }

    public void clickOnSearchButton() {
        elementUtils.clickOnElement(searchButton);
    }

    public void selectListViewOption() {
        elementUtils.clickOnElement(listViewOption);
    }

    public void selectAddToCartOption() {
        elementUtils.clickOnElement(addToCartOption);
    }

    public void selectAddToWishListOption() {
        elementUtils.clickOnElement(addToWishListOption);
    }

    public void selectCompareThisProductOption() {
        elementUtils.clickOnElement(compareThisProduct);
    }

    public ProductDisplayPage selectProductImage() {
        elementUtils.clickOnElement(productImage);
        return new ProductDisplayPage(driver);
    }

    public ProductDisplayPage selectProductUsingName() {
        elementUtils.clickOnElement(iMacProduct);
        return new ProductDisplayPage(driver);
    }

    public String getToolTip(){
        return elementUtils.getToolTip(compareThisProduct);
    }

    public void selectGridViewOption() {
        elementUtils.clickOnElement(gridViewOption);
    }

    public void enterIntoSearchCriteriaField(String text) {
        elementUtils.enterTextIntoElement(searchCriteriaField, text);
    }

    public String getPlaceHolderTextOfSearchCriteriaField() {
        return elementUtils.getDomAttributeOfElement(searchCriteriaField, "placeholder");
    }

    public int getNumberOfProductsDisplayedInSearchResults() {
        return elementUtils.getElementCount(numberOfProducts);
    }

    public String getNoProductMessage() {
        return elementUtils.getTextOfElement(noProductMessage);
    }

    public boolean didWeNavigateToSearchPage() {
        return elementUtils.isElementDisplayed(searchBreadcrumb);
    }

    public boolean isExistingProductDisplayedInSearchResults() {
        return elementUtils.isElementDisplayed(existingProduct);
    }

    public boolean isProductHavingDescriptionTextDisplayedInSearchResults() {
        return elementUtils.isElementDisplayed(iMacProduct);
    }

    public boolean isProductInCategoryDisplayedInSearchResults() {
        return elementUtils.isElementDisplayed(iMacProduct);
    }

    public ProductComparisonPage selectProductCompareLink() {
        elementUtils.clickOnElement(productCompareLink);
        return new ProductComparisonPage(driver);
    }

    public String getFirstProductName() {
        return elementUtils.getTextOfElement(firstProductInSearchResults);
    }

    public String getSecondProductName() {
        return elementUtils.getTextOfElement(secondProductInSearchResults);
    }

    public String getThirdProductName() {
        return elementUtils.getTextOfElement(thirdProductInSearchResults);
    }

    public String getFourthProductName() {
        return elementUtils.getTextOfElement(fourthProductInSearchResults);
    }

    public SearchPage selectSearchBreadcrumbOption(){
        elementUtils.clickOnElement(searchBreadcrumb);
        return new SearchPage(driver);
    }
    public String getSearchPageHeading() {
        return elementUtils.getTextOfElement(searchPageHeading);
    }

    public SearchPage searchUsingSearchCriteriaFieldInSearchResultsPageUsingKeyboardKeys(String productName) {
        elementUtils.pressKeyMultipleTimes(Keys.TAB, 21);
        elementUtils.pressKeyboardKey(Keys.BACK_SPACE);
        elementUtils.enterTextIntoFieldUsingKeyboardKeys(productName);
        elementUtils.pressKeyMultipleTimes(Keys.TAB, 3);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public SearchPage verifySearchingByCategoryUsingKeyboardKeys() {
        elementUtils.pressKeyMultipleTimes(Keys.TAB, 22);
        elementUtils.pressKeyboardKey(Keys.ARROW_DOWN);
        elementUtils.pressKeyMultipleTimes(Keys.TAB, 3);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public SearchPage verifySearchingInSubcategoriesUsingKeyboardKeys() {
        elementUtils.pressKeyMultipleTimes(Keys.TAB, 23);
        elementUtils.pressKeyboardKey(Keys.SPACE);
        elementUtils.pressKeyMultipleTimes(Keys.TAB, 2);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public SearchPage verifySearchingUsingDescriptionUsingKeyboardKeys(String productDescription) {
        elementUtils.pressKeyMultipleTimes(Keys.TAB, 21);
        elementUtils.pressKeyboardKey(Keys.BACK_SPACE);
        elementUtils.enterTextIntoFieldUsingKeyboardKeys(productDescription);
        elementUtils.pressKeyMultipleTimes(Keys.TAB, 3);
        elementUtils.pressKeyboardKey(Keys.SPACE);
        elementUtils.pressKeyboardKey(Keys.TAB);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public SearchPage verifySearchingInListViewUsingKeyboardKeys() {
        elementUtils.pressKeyMultipleTimes( Keys.TAB, 26);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public SearchPage verifySearchingInGridViewUsingKeyboardKeys() {
        elementUtils.pressKeyboardKey(Keys.TAB);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }
    public ProductComparisonPage verifyNavigatingToProductComparedPageUsingKeyboardKeys() {
        elementUtils.pressKeyboardKey(Keys.TAB);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new ProductComparisonPage(driver);
    }

    public SearchPage verifySortInSearchPageUsingKeyboardKeys() {
        elementUtils.pressKeyMultipleTimes( Keys.TAB, 1);
        elementUtils.pressKeyboardKey(Keys.ARROW_DOWN);
        return new SearchPage(driver);
    }

    public SearchPage verifyProductsCountInSearchPageUsingKeyboardKeys() {
        elementUtils.pressKeyMultipleTimes(Keys.TAB,30);
        elementUtils.pressKeyboardKey(Keys.ARROW_DOWN);
        return new SearchPage(driver);
    }

    public ProductDisplayPage clickOnProductDisplayInSearchResults(){
        elementUtils.clickOnElement(existingProduct);
        return new ProductDisplayPage(driver);
    }

    public ProductComparisonPage clickOnProductComparisonLink(){
        elementUtils.clickOnElement(productCompareLink);
        return new ProductComparisonPage(driver);
    }
}
