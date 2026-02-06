package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.CartPage;
import com.ibsu.edu.ge.ui.pages.HomePage;
import com.ibsu.edu.ge.ui.pages.ProductsPage;
import com.ibsu.edu.ge.ui.utils.DriverFactory;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Cart Management")
@Feature("Adding Products")
public class AddCartTest extends TestBase {

    @Test(description = "Test Case 12: Add Products in Cart")
    @Severity(SeverityLevel.CRITICAL)
    public void addProductsToCartTest() {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();

        // 1. მთავარი გვერდი
        DriverFactory.getDriver().get("https://automationexercise.com");
        Assert.assertTrue(homePage.isHomePageVisible());

        // 2. Products გვერდზე გადასვლა
        productsPage.clickProductsLink();

        // 3. პირველი პროდუქტის დამატება (Hover & Click)
        productsPage.addFirstProductToCart();

        // 4. Continue Shopping
        productsPage.clickContinueShopping();

        // 5. მეორე პროდუქტის დამატება
        productsPage.addSecondProductToCart();

        // 6. View Cart
        productsPage.clickViewCart();

        // 7. შემოწმება: კალათაში 2 ნივთი უნდა იყოს
        Assert.assertEquals(cartPage.getCartItemsCount(), 2, "Cart does not contain 2 items!");

        // 8. ფასების და სახელების შემოწმება
        // პირველი პროდუქტი: Blue Top, Rs. 500
        Assert.assertTrue(cartPage.verifyProductDetails("Blue Top", "500", 0), "First product details incorrect");

        // მეორე პროდუქტი: Men Tshirt, Rs. 400
        Assert.assertTrue(cartPage.verifyProductDetails("Men Tshirt", "400", 1), "Second product details incorrect");
    }
}