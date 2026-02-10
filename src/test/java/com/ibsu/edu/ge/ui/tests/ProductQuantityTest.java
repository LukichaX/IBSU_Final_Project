package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.CartPage;
import com.ibsu.edu.ge.ui.pages.HomePage;
import com.ibsu.edu.ge.ui.pages.ProductDetailsPage;
import com.ibsu.edu.ge.ui.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Cart Management")
@Feature("Quantity Verification")
public class ProductQuantityTest extends TestBase {

    @Test(description = "Test Case 13: Verify Product Quantity in Cart")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductQuantityTest() {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = new ProductsPage();
        ProductDetailsPage detailsPage = new ProductDetailsPage();
        CartPage cartPage = new CartPage();

        // 1. მთავარი გვერდი
        Assert.assertTrue(homePage.isHomePageVisible());

        // 2. გადავდივართ Products -> View First Product
        productsPage.clickProductsLink();
        productsPage.clickFirstViewProduct();

        // 3. რაოდენობის შეცვლა 4-ზე
        Assert.assertTrue(detailsPage.isProductDetailsVisible(), "Details page not visible");
        detailsPage.setQuantity("4");

        // 4. კალათაში დამატება
        detailsPage.addToCart();
        detailsPage.clickViewCartFromModal();

        // 5. შემოწმება, რომ კალათაში ზუსტად 4 წერია
        Assert.assertTrue(cartPage.isCartPageVisible());
        Assert.assertEquals(cartPage.getProductQuantity(), "4", "Product quantity mismatch in cart!");
    }
}