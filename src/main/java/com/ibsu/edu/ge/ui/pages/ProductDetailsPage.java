package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage {

    @FindBy(id = "quantity")
    private WebElement quantityInput;

    @FindBy(css = "button.cart") // "Add to cart" button inside details page
    private WebElement addToCartBtn;

    @FindBy(css = "u")
    private WebElement viewCartLink; // Modal window link "View Cart"

    @FindBy(css = "div.product-information h2")
    private WebElement productNameHeader;

    // --- Methods ---

    @Step("შემოწმება: პროდუქტის დეტალების გვერდზე ვართ")
    public boolean isProductDetailsVisible() {
        return isDisplayed(productNameHeader);
    }

    @Step("რაოდენობის შეცვლა: {quantity}")
    public void setQuantity(String quantity) {
        quantityInput.clear();
        sendKeys(quantityInput, quantity);
    }

    @Step("კალათაში დამატება")
    public void addToCart() {
        click(addToCartBtn);
    }

    @Step("გადასვლა კალათაში (მოდალური ფანჯრიდან)")
    public void clickViewCartFromModal() {
        wait.until(ExpectedConditions.visibilityOf(viewCartLink));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink));
        click(viewCartLink);
    }
}