package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
    WebDriver driver;

    public AccountSuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    private WebElement logOutOption;

    @FindBy(xpath = "//div[@id='common-success']//h1")
    private WebElement pageHeading;

    @FindBy(id = "content")
    private WebElement pageContent;

    @FindBy(xpath = "//a[text()='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Success']")
    private WebElement accountSuccessPageBreadcrumb;

    public boolean isUserLoggedIn() {
        return logOutOption.isDisplayed();
    }

    public String getPageHeadingText() {
        return pageHeading.getText();
    }

    public String getPageContentText() {
        return pageContent.getText();
    }

    public AccountPage clickContinueButton() {
        continueButton.click();
        return new AccountPage(driver);
    }

    public boolean didWeNavigateToAccountSuccessPage() {
        return accountSuccessPageBreadcrumb.isDisplayed();
    }
}
