package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    @FindBy(css = "li.active") private WebElement activeBreadcrumb;
    @FindBy(css = "a.check_out") private WebElement proceedToCheckoutBtn;
    @FindBy(id = "cart_info") private WebElement cartInfoTable;

    // წაშლის ღილაკი
    @FindBy(css = "a.cart_quantity_delete") private WebElement deleteProductBtn;

    // რაოდენობის ღილაკი (სადაც წერია რამდენი ცალია)
    @FindBy(css = "td.cart_quantity button") private WebElement productQuantityBtn;

    // --- Methods ---

    @Step("შემოწმება: ვართ თუ არა კალათაში")
    public boolean isCartPageVisible() {
        return getText(activeBreadcrumb).contains("Shopping Cart");
    }

    @Step("Checkout-ზე გადასვლა")
    public void clickProceedToCheckout() {
        click(proceedToCheckoutBtn);
    }

    @Step("პროდუქტის წაშლა კალათიდან")
    public void deleteProduct() {
        click(deleteProductBtn);
        wait.until(ExpectedConditions.invisibilityOf(deleteProductBtn));
    }

    @Step("შემოწმება: კალათა ცარიელია")
    public boolean isCartEmpty() {
        return driver.findElements(By.cssSelector("a.cart_quantity_delete")).isEmpty();
    }

    @Step("რაოდენობის წაკითხვა კალათიდან")
    public String getProductQuantity() {
        return getText(productQuantityBtn);
    }
}