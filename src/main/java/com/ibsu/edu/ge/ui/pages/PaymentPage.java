package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(name = "name_on_card") private WebElement nameOnCardInput;
    @FindBy(name = "card_number") private WebElement cardNumberInput;
    @FindBy(name = "cvc") private WebElement cvcInput;
    @FindBy(name = "expiry_month") private WebElement expiryMonthInput;
    @FindBy(name = "expiry_year") private WebElement expiryYearInput;

    @FindBy(id = "submit") private WebElement payBtn;

    // Success Message
    @FindBy(css = "h2[data-qa='order-placed']")
    private WebElement orderPlacedMsg;

    @FindBy(css = "a[href='/delete_account']") private WebElement continueBtn;

    // --- Methods ---

    @Step("საბანკო მონაცემების შეყვანა და გადახდა")
    public void fillPaymentDetailsAndPay() {
        sendKeys(nameOnCardInput, "Luka Khirdaev");
        sendKeys(cardNumberInput, "4111111111111111");
        sendKeys(cvcInput, "123");
        sendKeys(expiryMonthInput, "12");
        sendKeys(expiryYearInput, "2030");
        click(payBtn);
    }

    @Step("ორდერის დადასტურების შემოწმება")
    public String getOrderPlacedMessage() {
        return getText(orderPlacedMsg);
    }
}