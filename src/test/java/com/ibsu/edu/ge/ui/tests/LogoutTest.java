package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.LoginPage;
import com.ibsu.edu.ge.ui.pages.RegisterPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.UUID;

@Epic("User Management")
@Feature("Authentication")
public class LogoutTest extends TestBase {

    @Test(description = "Test Case 4: Logout User")
    @Severity(SeverityLevel.NORMAL)
    public void logoutUserTest() {
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        // 1. Pre-condition: იუზერის შექმნა და შესვლა (ავტომატურად შედის რეგისტრაციის მერე)
        String email = "ibsu_logout_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";

        loginPage.navigateToLogin();
        registerPage.startSignup("Luka", email);
        registerPage.fillAllDetails("password", "Luka", "Khirdaev");
        registerPage.clickContinue(); // ანტი-რეკლამა აქ მუშაობს

        // 2. შემოწმება რომ სისტემაში ვართ
        Assert.assertTrue(loginPage.isLoggedInAsUserVisible());

        // 3. გამოსვლა (Logout)
        loginPage.clickLogout();

        // 4. შემოწმება რომ გამოვედით (Login გვერდზე უნდა გადაგვიყვანოს)
        Assert.assertTrue(loginPage.isLoginHeaderVisible(), "Not navigated to login page after logout");

        // 5. Cleanup: ისევ შევდივართ რომ წავშალოთ (სისუფთავისთვის)
        loginPage.performLogin(email, "password");
        registerPage.deleteAccount();
        registerPage.clickContinue();
    }
}