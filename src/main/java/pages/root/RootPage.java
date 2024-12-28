package pages.root;

import org.openqa.selenium.WebDriver;

public class RootPage {
    private WebDriver driver;

    public RootPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean isTextMatching(String expectedText, String actualText) {
        boolean b = false; // Mặc định là false
        if (actualText != null && expectedText != null) { // Kiểm tra null trước
            b = actualText.equals(expectedText); // Gán kết quả so sánh
        }
        return b; // Trả về giá trị cuối cùng
    }

}
