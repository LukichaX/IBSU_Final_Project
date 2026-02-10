package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.HomePage;
import com.ibsu.edu.ge.ui.pages.LoginPage;
import com.ibsu.edu.ge.ui.pages.RegisterPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.UUID;

@Epic("User Management")
@Feature("Authentication")
public class LoginTest extends TestBase {

    @Test(description = "Test Case 2: Login User with correct email and password")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUserTest() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        // 1. მონაცემების მომზადება (Pre-condition: ვქმნით იუზერს, რომ შევძლოთ შესვლა)
        String name = "Luka";
        String email = "ibsu_login_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";
        String password = "password123";

        // --- რეგისტრაციის პროცესი (სწრაფად) ---
        loginPage.navigateToLogin();
        registerPage.startSignup(name, email);
        registerPage.fillAllDetails(password, name, "Khirdaev");
        registerPage.clickContinue();
        loginPage.clickLogout();

        // --- აქ იწყება ნამდვილი LOGIN ტესტი ---

        // 2. ვამოწმებთ რომ მთავარ გვერდზე ვართ (ლოგინზე გადასვლამდე)
        Assert.assertTrue(loginPage.isLoginHeaderVisible(), "User is not navigated to login page after logout");

        // 3. შეგვყავს სწორი მეილი და პაროლი
        loginPage.performLogin(email, password);

        // 4. ვამოწმებთ, რომ შევიდა ("Logged in as...")
        Assert.assertTrue(loginPage.isLoggedInAsUserVisible(), "Logged in text is NOT visible!");

        // 5. ექაუნთის წაშლა (Cleanup)
        registerPage.deleteAccount();
        Assert.assertEquals(registerPage.getAccountDeletedMessage(), "ACCOUNT DELETED!");
        registerPage.clickContinue();
    }
}