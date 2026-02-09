package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.HomePage;
import com.ibsu.edu.ge.ui.pages.ProductsPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Products Management")
@Feature("Search & Browse")
public class ProductsTest extends TestBase {

    @Test(description = "Test Case 8 & 9: Verify All Products and Search Product")
    @Severity(SeverityLevel.CRITICAL)
    public void searchProductTest() {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = new ProductsPage();

        // 1. მთავარი გვერდიდან Products-ზე გადასვლა
        Assert.assertTrue(homePage.isHomePageVisible());
        productsPage.clickProductsLink();

        // 2. შემოწმება რომ Products გვერდზე ვართ
        Assert.assertTrue(productsPage.isAllProductsVisible(), "ALL PRODUCTS header not visible");

        // 3. ძებნა: "Tshirt"
        productsPage.searchProduct("Tshirt");

        // 4. შემოწმება რომ "SEARCHED PRODUCTS" ჩანს
        Assert.assertTrue(productsPage.isSearchedProductsVisible(), "SEARCHED PRODUCTS header not visible");

        // აქ შეიძლებოდა დაგვემატებინა ლისტის შემოწმებაც, მაგრამ ჯერ Header-იც საკმარისია
    }
}