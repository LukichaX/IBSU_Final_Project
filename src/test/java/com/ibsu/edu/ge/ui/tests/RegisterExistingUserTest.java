package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.HomePage;
import com.ibsu.edu.ge.ui.pages.LoginPage;
import com.ibsu.edu.ge.ui.pages.RegisterPage;
import com.ibsu.edu.ge.ui.utils.DriverFactory; // ეს დაგჭირდება
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.UUID;

@Epic("User Management")
@Feature("Registration")
public class RegisterExistingUserTest extends TestBase {

    private String existingEmail;
    private final String name = "Existing User";

    @BeforeMethod
    public void setUpData() {
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        // 1. ვქმნით პირველ იუზერს (რომ მეილი დაკავებული იყოს)
        existingEmail = "exist_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";

        loginPage.navigateToLogin();
        registerPage.startSignup(name, existingEmail);
        registerPage.fillAllDetails("pass123", "Test", "User");
        registerPage.clickContinue();
        loginPage.clickLogout();
    }

    @Test(description = "Test Case 5: Register User with existing email")
    @Severity(SeverityLevel.NORMAL)
    public void registerWithExistingEmailTest() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        // --- შესწორება: გადავდივართ მთავარ გვერდზე ტესტის დაწყებამდე ---
        DriverFactory.getDriver().get("https://automationexercise.com");

        // 1. ვამოწმებთ მთავარ გვერდს
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // 2. მივდივართ რეგისტრაციაზე
        loginPage.navigateToLogin();
        Assert.assertTrue(registerPage.isSignupHeaderVisible());

        // 3. ვცდილობთ დარეგისტრირებას იგივე მეილით
        registerPage.startSignup("New Name", existingEmail);

        // 4. ვამოწმებთ ერორს
        Assert.assertTrue(registerPage.isEmailExistErrorVisible(), "Error message 'Email already exist!' is not visible");
    }
}