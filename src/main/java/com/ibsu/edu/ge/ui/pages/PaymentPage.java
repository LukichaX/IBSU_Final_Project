package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(name = "name_on_card")
    private WebElement nameOnCardInput;

    @FindBy(name = "card_number")
    private WebElement cardNumberInput;

    @FindBy(name = "cvc")
    private WebElement cvcInput;

    @FindBy(name = "expiry_month")
    private WebElement expiryMonthInput;

    @FindBy(name = "expiry_year")
    private WebElement expiryYearInput;

    @FindBy(id = "submit")
    private WebElement payAndConfirmBtn;

    // გადახდის მერე რაც ჩანს
    @FindBy(css = "div.alert-success") // ან h2[data-qa='order-placed']
    private WebElement successMessage; // "Your order has been placed successfully!"


    @Step("ბარათის მონაცემების შეყვანა და გადახდა")
    public void fillPaymentDetailsAndPay(String name, String number, String cvc, String month, String year) {
        sendKeys(nameOnCardInput, name);
        sendKeys(cardNumberInput, number);
        sendKeys(cvcInput, cvc);
        sendKeys(expiryMonthInput, month);
        sendKeys(expiryYearInput, year);

        click(payAndConfirmBtn);

        // რეკლამის დაზღვევა
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                click(payAndConfirmBtn);
            }
        } catch (Exception e) {}
    }

    @Step("შემოწმება: გადახდა წარმატებულია თუ არა")
    public boolean isOrderPlacedSuccessVisible() {
        // ზოგჯერ ტექსტია, ზოგჯერ ელემენტი. ვამოწმებთ URL-საც
        return driver.getCurrentUrl().contains("payment_done") || isDisplayed(successMessage);
    }
}