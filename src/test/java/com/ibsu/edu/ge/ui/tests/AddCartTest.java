package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.CartPage;
import com.ibsu.edu.ge.ui.pages.HomePage;
import com.ibsu.edu.ge.ui.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Products Management")
@Feature("Cart")
public class AddCartTest extends TestBase {

    @Test(description = "Test Case 12: Add Products in Cart")
    @Severity(SeverityLevel.CRITICAL)
    public void addProductsToCartTest() {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();

        Assert.assertTrue(homePage.isHomePageVisible());

        productsPage.clickProductsLink();
        productsPage.addFirstProductToCart();

        productsPage.clickViewCart();
        Assert.assertTrue(cartPage.isCartPageVisible());

    }
}