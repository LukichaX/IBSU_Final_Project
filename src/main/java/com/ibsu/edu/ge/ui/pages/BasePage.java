package com.ibsu.edu.ge.ui.pages;

import com.ibsu.edu.ge.ui.utils.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // --- უნივერსალური Click (Smart Logic) ---
    protected void click(WebElement element) {
        try {
            // 1. მივიტანოთ ელემენტთან (Scroll)
            actions.scrollToElement(element).perform();
        } catch (Exception e) {
            // Fallback Scroll
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        }

        try {
            // 2. ცდა: ჩვეულებრივი Selenium Click
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            // 3. მარცხი: რეკლამა ეფარება? -> JS Click!
            System.out.println("⚠️ Click Intercepted. Using JS Click force...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    // --- უნივერსალური SendKeys ---
    protected void sendKeys(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).clear();
            element.sendKeys(text);
        } catch (Exception e) {
            // Fallback: JS Focus & Send
            ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);
            element.sendKeys(text);
        }
    }

    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            // აქ wait გვინდა, რომ დავრწმუნდეთ ელემენტის არსებობაში
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Select (Dropdown) დამხმარე მეთოდი
    protected void selectByVisibleText(WebElement element, String text) {
        // Select-ს ხანდახან სჭირდება სქროლი
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            new Select(element).selectByVisibleText(text);
        } catch (Exception e) {
            new Select(element).selectByVisibleText(text);
        }
    }
}