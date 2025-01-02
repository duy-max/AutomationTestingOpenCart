package pages.root;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    public boolean isTextMatching(String expectedText, String actualText) {
        boolean b = false; // Mặc định là false
        if (actualText != null && expectedText != null) { // Kiểm tra null trước
            b = actualText.equals(expectedText); // Gán kết quả so sánh
        }
        return b; // Trả về giá trị cuối cùng
    }

    public String getSuccessMessage() {
        String fullText = elementUtils.getTextOfElementWithSomeDelay(successMessage, 3000);
        return fullText.replaceAll("\n","").substring(0, fullText.length() -2);
    }

}
