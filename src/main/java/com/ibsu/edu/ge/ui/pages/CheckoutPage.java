package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//ul[@id='address_delivery']")
    private WebElement deliveryAddressBlock;

    @FindBy(xpath = "//ul[@id='address_invoice']")
    private WebElement billingAddressBlock;

    @FindBy(css = "textarea[name='message']")
    private WebElement commentInput;

    @FindBy(css = "a[href='/payment']")
    private WebElement placeOrderBtn;


    @Step("შემოწმება: ჩანს თუ არა მისამართის ბლოკი")
    public boolean isAddressDetailsVisible() {
        return isDisplayed(deliveryAddressBlock) && isDisplayed(billingAddressBlock);
    }

    @Step("კომენტარის დაწერა და შეკვეთის განთავსება")
    public void enterCommentAndPlaceOrder(String comment) {
        sendKeys(commentInput, comment);
        click(placeOrderBtn);

        // რეკლამის დაზღვევა
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                click(placeOrderBtn);
            }
        } catch (Exception e) {}
    }
}