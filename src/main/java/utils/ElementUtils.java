package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementUtils {
    WebDriver driver;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false; // Element không tồn tại
        } catch (StaleElementReferenceException e) {
            return false; // Element không còn hợp lệ trong DOM
        } catch (WebDriverException e) {
            return false; // Lỗi liên quan đến WebDriver
        }
    }

    public boolean isElementEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (NoSuchElementException e) {
            return false; // Element không tồn tại
        } catch (StaleElementReferenceException e) {
            return false; // Element không còn hợp lệ trong DOM
        } catch (WebDriverException e) {
            return false; // Lỗi liên quan đến WebDriver
        }
    }

    public boolean isElementSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (NoSuchElementException e) {
            return false; // Element không tồn tại
        } catch (StaleElementReferenceException e) {
            return false; // Element không còn hợp lệ trong DOM
        } catch (WebDriverException e) {
            return false; // Lỗi liên quan đến WebDriver
        }
    }

    public void clickOnElement(WebElement element) {
        try {
            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
            }

        } catch (Exception e) {
            //Dùng js để click
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }


    }

    public void enterTextIntoElement(WebElement element, String text) {
        if (isElementDisplayed(element) && isElementEnabled(element)) {
            element.clear();
            element.sendKeys(text);
        }
    }

    public String getTextOfElement(WebElement element) {
        try {
            return element.getText(); // Trả về trực tiếp kết quả
        } catch (NoSuchElementException e) {
            return ""; // Trả về chuỗi rỗng nếu element không tồn tại
        } catch (StaleElementReferenceException e) {
            return ""; // Trả về chuỗi rỗng nếu element không còn hợp lệ trong DOM
        } catch (WebDriverException e) {
            return ""; // Trả về chuỗi rỗng nếu có lỗi liên quan đến WebDriver
        }
    }

    public String getTextOfElementWithSomeDelay(WebElement element, int delay) {
        try {
            Thread.sleep(delay);
           return element.getText();
        } catch (NoSuchElementException e) {
            return "";
        } catch (Exception e) {
            return"";
        }
    }

    public String getDomAttributeOfElement(WebElement element, String attribute) {
        try {
            return element.getDomAttribute(attribute); // Trả về trực tiếp kết quả
        } catch (NoSuchElementException e) {
            return ""; // Trả về chuỗi rỗng nếu element không tồn tại
        } catch (StaleElementReferenceException e) {
            return ""; // Trả về chuỗi rỗng nếu element không còn hợp lệ trong DOM
        } catch (WebDriverException e) {
            return ""; // Trả về chuỗi rỗng nếu có lỗi liên quan đến WebDriver
        }
    }



    public void clearTextFromElement(WebElement element) {
        if (isElementDisplayed(element) && isElementEnabled(element)) {
            element.clear();
        }
    }

    public String getDomPropertyOfElement(WebElement element, String attribute) {
        String text = "";
        try {
            text = element.getDomProperty(attribute);
        } catch (NoSuchElementException e) {
            text = "";
        } catch (Exception e) {
            text = "";
        }
        return text;
    }

    public void mouseLeftClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.click(element).perform();
    }

    public void pressAndHoldTheKeyboardKey(Keys key) {
        Actions actions = new Actions(driver);
        actions.keyDown(key).perform();
    }

    public void pressKeyboardKey(Keys keyText) {
        Actions actions = new Actions(driver);
        actions.sendKeys(keyText).perform();
    }

    public void pressKeyMultipleTimes(Keys keyText, int times) {
        Actions actions = new Actions(driver);
        for (int i = 0; i < times; i++){
            actions.sendKeys(keyText).perform();
        }

    }


    public void enterTextIntoFieldUsingKeyboardKeys(String text) {

        Actions actions = new Actions(driver);
        actions.sendKeys(text).perform();

    }

    public void releaseKeyboardKey(Keys key) {
        Actions actions = new Actions(driver);
        actions.keyUp(key).perform();
    }

    public void pastingTextIntoField(WebElement element) {
        if (isElementDisplayed(element) && isElementEnabled(element)) {
            Actions actions = new Actions(driver);
            actions.click(element).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
        }
    }

    public void copyTextFromElement(WebElement element) {
        if (isElementDisplayed(element) && isElementEnabled(element)) {
            Actions actions = new Actions(driver);
            actions.doubleClick(element).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
        }
    }

    public boolean isElementDisplayedAfterWaiting(By by, int seconds) {
        boolean b = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            b = element.isDisplayed();
        } catch (NoSuchElementException e) {
            b = false;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public String getCSSPropertyOfPuseudoElement(WebElement element, String property) {
        String text = "";
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            text = (String) jse.executeScript(
                    "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('" + property + "');",
                    element);
        } catch (NoSuchElementException e) {
            text = "";
        } catch (Exception e) {
            text = "";
        }
        return text;
    }

    public String getCSSPropertyOfElement(WebElement element, String property) {
        String text = "";
        try {
            text = element.getCssValue(property);
        } catch (NoSuchElementException e) {
            text = "";
        } catch (Exception e) {
            text = "";
        }
        return text;
    }

    public void selectOptionInDropDownFieldUsingVisibleText(WebElement element, String value) {
        if (isElementDisplayed(element) && isElementEnabled(element)) {
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            for (WebElement option : options) {
                String visibleText = option.getText().replaceAll("&nbsp;", "").trim();
                if (visibleText.equals(value)) {
                    select.selectByIndex(options.indexOf(option));
                    break;
                }
            }
        }
    }

    public int getElementCount(List<WebElement> elements) {
        int n;
        try {
            n = elements.size();
        } catch (NoSuchElementException e) {
            n = 0;
        }
        catch (Exception e) {
            n = 0;
        }
        return n;
    }
}
