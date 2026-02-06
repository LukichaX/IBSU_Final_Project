package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.HomePage;
import com.ibsu.edu.ge.ui.pages.ProductsPage;
import com.ibsu.edu.ge.ui.utils.DriverFactory;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Product Management")
@Feature("Browsing & Searching")
public class ProductsTest extends TestBase {

    @Test(description = "Test Case 8: Verify All Products and product detail page")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyAllProductsTest() {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = new ProductsPage();

        // 1. მთავარი გვერდი
        DriverFactory.getDriver().get("https://automationexercise.com");
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page not visible");

        // 2. პროდუქტებზე გადასვლა
        productsPage.clickProductsLink();
        Assert.assertTrue(productsPage.isAllProductsVisible(), "'ALL PRODUCTS' header not visible");

        // 3. სიის შემოწმება (უნდა იყოს 0-ზე მეტი პროდუქტი)
        Assert.assertTrue(productsPage.getProductsCount() > 0, "Product list is empty!");

        // 4. პირველი პროდუქტის გახსნა
        productsPage.clickFirstProductView();

        // 5. დეტალების შემოწმება
        Assert.assertTrue(productsPage.isProductNameVisible(), "Product detail (Name) is not visible");
    }

    @Test(description = "Test Case 9: Search Product")
    @Severity(SeverityLevel.NORMAL)
    public void searchProductTest() {
        HomePage homePage = new HomePage();
        ProductsPage productsPage = new ProductsPage();

        DriverFactory.getDriver().get("https://automationexercise.com");

        // 1. პროდუქტებზე გადასვლა
        productsPage.clickProductsLink();
        Assert.assertTrue(productsPage.isAllProductsVisible());

        // 2. ძებნა (მაგალითად "Dress")
        productsPage.searchProduct("Dress");

        // 3. შედეგის შემოწმება
        Assert.assertTrue(productsPage.isSearchedProductsVisible(), "'SEARCHED PRODUCTS' header not visible");
        Assert.assertEquals(productsPage.getSearchedHeaderText(), "SEARCHED PRODUCTS");

        // 4. სია ისევ არ უნდა იყოს ცარიელი
        Assert.assertTrue(productsPage.getProductsCount() > 0, "No products found after search!");
    }
}