package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//h2[contains(text(), 'Address Details')]")
    private WebElement addressDetailsHeader;

    @FindBy(xpath = "//h2[contains(text(), 'Review Your Order')]")
    private WebElement reviewOrderHeader;

    @FindBy(css = "textarea[name='message']")
    private WebElement commentArea;

    @FindBy(css = "a[href='/payment']")
    private WebElement placeOrderBtn;

    // --- Methods ---

    @Step("შემოწმება: ჩანს თუ არა მისამართი და ორდერი")
    public boolean isCheckoutPageVisible() {
        return isDisplayed(addressDetailsHeader) && isDisplayed(reviewOrderHeader);
    }

    @Step("კომენტარის დაწერა და ორდერის განთავსება")
    public void placeOrder(String comment) {
        sendKeys(commentArea, comment);
        click(placeOrderBtn);
    }
}