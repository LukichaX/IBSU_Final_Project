package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.*;
import com.ibsu.edu.ge.ui.utils.DriverFactory;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.UUID;

@Epic("E-Commerce Flow")
@Feature("Checkout Process")
public class PlaceOrderTest extends TestBase {

    @Test(description = "Test Case 14: Place Order: Register while Checkout")
    @Severity(SeverityLevel.CRITICAL)
    public void placeOrderRegisterWhileCheckoutTest() {
        // ყველა საჭირო გვერდის ინიციალიზაცია
        HomePage homePage = new HomePage();
        ProductsPage productsPage = new ProductsPage();
        CartPage cartPage = new CartPage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        CheckoutPage checkoutPage = new CheckoutPage();
        PaymentPage paymentPage = new PaymentPage();

        // 1. Launch browser
        DriverFactory.getDriver().get("https://automationexercise.com");
        Assert.assertTrue(homePage.isHomePageVisible());

        // 2. Add products to cart
        productsPage.clickProductsLink();
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();

        // 3. Cart page -> Proceed to Checkout
        productsPage.clickViewCart();
        Assert.assertTrue(cartPage.isCartPageVisible());

        cartPage.clickProceedToCheckout();

        // 4. Click 'Register / Login' button (რადგან არ ვართ შესული)
        cartPage.clickRegisterLoginLink();

        // 5. Register User
        String email = "order_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";
        registerPage.startSignup("Buyer User", email);
        registerPage.fillAllDetails("pass123", "Buyer", "Testov");
        Assert.assertEquals(registerPage.getAccountCreatedMessage(), "ACCOUNT CREATED!");
        registerPage.clickContinue();

        // 6. Verify 'Logged in as username'
        Assert.assertTrue(loginPage.isLoggedInAsUserVisible());

        // 7. Click 'Cart' button (რადგან რეგისტრაციამ მთავარზე გადაგვაგდო)
        productsPage.clickViewCart(); // ზედა მენიუდან გადავდივართ ისევ კალათაში

        // 8. Click 'Proceed to Checkout'
        cartPage.clickProceedToCheckout();

        // 9. Verify Address Details and Review Your Order
        Assert.assertTrue(checkoutPage.isAddressDetailsVisible(), "Address details not found!");

        // 10. Enter description -> Place Order
        checkoutPage.enterCommentAndPlaceOrder("Please deliver quickly!");

        // 11. Enter Payment details
        paymentPage.fillPaymentDetailsAndPay("Buyer Testov", "411111111111", "311", "12", "2028");

        // 12. Verify Success message
        Assert.assertTrue(paymentPage.isOrderPlacedSuccessVisible(), "Order success message not visible!");

        // 13. Delete Account
        registerPage.deleteAccount();
        Assert.assertEquals(registerPage.getAccountDeletedMessage(), "ACCOUNT DELETED!");
    }
}