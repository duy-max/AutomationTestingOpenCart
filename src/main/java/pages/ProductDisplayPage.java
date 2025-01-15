
package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;
import utils.ElementUtils;
import utils.CommonUtils;

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

    @FindBy(id = "button-cart")
    private WebElement addToCartButton;

    @FindBy(id = "input-quantity")
    private WebElement productQuantity;

    @FindBy(xpath = " //label[contains(.,'Small')]/input")
    private WebElement radioType;

    @FindBy(xpath = "//label[contains(.,'Checkbox 3')]/input")
    private WebElement typeCheckbox3;

    @FindBy(xpath = "//label[contains(.,'Checkbox 4')]/input")
    private WebElement typeCheckbox4;

    @FindBy(xpath = "//label[contains(.,'Select')]/following-sibling::select")
    private WebElement selectOptions;

    @FindBy(xpath = "//option[contains(.,'Red')]")
    private WebElement selectRedOption;

    @FindBy(xpath = "//label[text()='Textarea']/following-sibling::textarea")
    private WebElement textArea;

    @FindBy(xpath = "//button[contains(.,'Upload File')]")
    private WebElement uploadFile;

    @FindBy(xpath = "(//label[contains(.,'Date')])[1]/following-sibling::div//button")
    private WebElement datePicker;

    @FindBy(xpath = "(//label[contains(.,'Time')])[1]/following-sibling::div//button")
    private WebElement timePicker;

    @FindBy(xpath = "(//label[contains(.,'Time')])[2]/following-sibling::div//button")
    private WebElement dateAndTimePicker;


    @FindBy(xpath = "(//label[contains(.,'Date')])[1]/following-sibling::div/input")
    private WebElement dateTextField;

    @FindBy(xpath = "//div[@class='datepicker-days']//th[@class='picker-switch']")
    private WebElement datePickerDays;

    @FindBy(xpath = "//div[@class='datepicker-months']//th[@class='picker-switch']")
    private WebElement datePickerMonths;

    @FindBy(xpath = "//div[@class='datepicker-years']//th[@class='picker-switch']")
    private WebElement datePickerYears;


    @FindBy(xpath = "//div[@class='datepicker-days']//tbody//tr[3]//td[text()='10']")
    private WebElement selectDayValue;

    @FindBy(xpath = " //div[@class='datepicker-months']//span[text()='Apr']")
    private WebElement selectMonthValue;

    @FindBy(xpath = "//div[@class='datepicker-years']//span[text()='2019']")
    private WebElement selectYearValue;


    //div[@id='content']//h2
    @FindBy(xpath = "//h2[normalize-space()='$122.00']")
    private WebElement unitPrice;

    @FindBy(xpath = "//li[contains(.,'Product Code:')]")
    private WebElement productModel;

    @FindBy(xpath = "(//a[@class='thumbnail'])[1]/img")
    private WebElement productImage;

    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement productName;

    public void selectDateFromDatePicker() throws InterruptedException {
        elementUtils.clickOnElement(datePicker);
        elementUtils.clickOnElement(datePickerDays);
        elementUtils.clickOnElement(datePickerMonths);
        elementUtils.clickOnElement(datePickerYears);
        elementUtils.clickOnElement(selectYearValue);
        elementUtils.clickOnElement(selectMonthValue);
        elementUtils.clickOnElement(selectDayValue);


    }

    public String getDateTextField(){
        return elementUtils.getDomAttributeOfElement(dateTextField, "value");
    }

    public String getProductName(){
        return elementUtils.getTextOfElement(productName);
    }



    public boolean didWeNavigateToProductDisplayPage() {
        return elementUtils.isElementDisplayed(thumbnailsSection);
    }

    public void clickOnAddToCartButton() {
        elementUtils.clickOnElement(addToCartButton);
    }

    public String getProductQuantity(){
        System.out.println(elementUtils.getDomAttributeOfElement(productQuantity,"value"));
       return elementUtils.getDomAttributeOfElement(productQuantity,"value");
    }

    public void clickOnAddToCartButtonMultipleTimes(int times) {
        for (int i = 0; i < times; i++) {
            elementUtils.clickOnElement(addToCartButton);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void enterProductQuantity(int quantity) {
        elementUtils.setValueAttributeByJS(productQuantity, quantity);

    }

    public String getUnitPrice(){
        return elementUtils.getTextOfElement(unitPrice);
    }

    public String getProductModel(){
        String fullText = elementUtils.getTextOfElement(productModel);
        int colonIndex = fullText.indexOf(":");
        String result = "";
        result = fullText.substring(colonIndex+1).trim();
        return result;
    }

    public void getProductImage() throws Exception {
        String imgURL = elementUtils.getDomAttributeOfElement(productImage, "src");
        CommonUtils.downloadImage(imgURL,System.getProperty("user.dir")+"\\downloads\\imgProductDisplayPage.png");
    }

    public String calculateProductTotal( String unitPriceProduct, int quantityProduct) {
        return CommonUtils.calculateTotal(unitPriceProduct, quantityProduct);
    }


}
