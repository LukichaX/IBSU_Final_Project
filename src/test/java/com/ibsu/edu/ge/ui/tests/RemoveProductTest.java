package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.CartPage;
import com.ibsu.edu.ge.ui.pages.HomePage;
import com.ibsu.edu.ge.ui.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Cart Management")
@Feature("Delete Item")
public class RemoveProductTest extends TestBase {

    @Test(description = "Test Case 17: Remove Products From Cart")
    @Severity(SeverityLevel.NORMAL)
    public void removeProductFromCartTest() {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();

        // 1. პროდუქტის დამატება (Pre-condition)
        Assert.assertTrue(homePage.isHomePageVisible());
        productsPage.clickProductsLink();
        productsPage.addFirstProductToCart();
        productsPage.clickViewCart();

        // 2. კალათის შემოწმება
        Assert.assertTrue(cartPage.isCartPageVisible());

        // 3. წაშლა
        cartPage.deleteProduct();

        // 4. შემოწმება რომ წაიშალა
        Assert.assertTrue(cartPage.isCartEmpty(), "Product was not removed from cart!");
    }
}