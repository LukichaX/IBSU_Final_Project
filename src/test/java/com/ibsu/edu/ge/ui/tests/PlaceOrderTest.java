package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.UUID;

@Epic("E-Commerce Flow")
@Feature("Checkout")
public class PlaceOrderTest extends TestBase {

    @Test(description = "Test Case 15: Place Order: Register before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    public void placeOrderRegisterWhileCheckoutTest() {
        // გვერდების ინიციალიზაცია
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();
        PaymentPage paymentPage = new PaymentPage();

        // 1. რეგისტრაცია (Pre-condition)
        String name = "Buyer Luka";
        String email = "ibsu_buy_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";

        loginPage.navigateToLogin();
        registerPage.startSignup(name, email);
        registerPage.fillAllDetails("password123", name, "Khirdaev");
        registerPage.clickContinue();
        Assert.assertTrue(loginPage.isLoggedInAsUserVisible(), "Login failed after registration");

        // 2. პროდუქტის დამატება
        productsPage.clickProductsLink();
        productsPage.addFirstProductToCart();
        productsPage.clickViewCart();

        // 3. კალათის შემოწმება
        Assert.assertTrue(cartPage.isCartPageVisible(), "Cart page not visible");
        cartPage.clickProceedToCheckout();

        // 4. ჩექაუთი (მისამართის შემოწმება)
        Assert.assertTrue(checkoutPage.isCheckoutPageVisible(), "Checkout details not visible");
        checkoutPage.placeOrder("Test Order via Selenium");

        // 5. გადახდა
        paymentPage.fillPaymentDetailsAndPay();

        // 6. წარმატების შემოწმება
        Assert.assertEquals(paymentPage.getOrderPlacedMessage(), "ORDER PLACED!", "Order not placed!");

        // 7. ექაუნთის წაშლა (Cleanup)
        registerPage.deleteAccount();
        Assert.assertEquals(registerPage.getAccountDeletedMessage(), "ACCOUNT DELETED!");
        registerPage.clickContinue();
    }
}