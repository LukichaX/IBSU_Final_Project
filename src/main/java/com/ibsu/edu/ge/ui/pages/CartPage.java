package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> cartRows;

    @FindBy(xpath = "//li[contains(text(), 'Shopping Cart')]")
    private WebElement breadcrumb;

    // --- ახალი ღილაკები ---
    @FindBy(css = "a.check_out")
    private WebElement proceedToCheckoutBtn;

    // ეს ის ლინკია, რომელიც მოდალში გამოდის: "Register / Login"
    @FindBy(xpath = "//u[contains(text(), 'Register / Login')]")
    private WebElement registerLoginModalLink;


    @Step("შემოწმება: ჩანს თუ არა კალათის გვერდი")
    public boolean isCartPageVisible() {
        return isDisplayed(breadcrumb);
    }

    @Step("Proceed to Checkout-ზე დაჭერა")
    public void clickProceedToCheckout() {
        click(proceedToCheckoutBtn);
    }

    @Step("მოდალში 'Register / Login'-ზე დაჭერა")
    public void clickRegisterLoginLink() {
        click(registerLoginModalLink);
    }

    @Step("კალათაში პროდუქტების რაოდენობის გაგება")
    public int getCartItemsCount() {
        return cartRows.size();
    }

    @Step("კალათაში კონკრეტული პროდუქტის შემოწმება")
    public boolean verifyProductDetails(String productName, String price, int rowIndex) {
        if (cartRows.size() <= rowIndex) return false;
        WebElement row = cartRows.get(rowIndex);
        String rowText = row.getText();
        return rowText.contains(productName) && rowText.contains(price);
    }
}