package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.HomePage;
import com.ibsu.edu.ge.ui.pages.LoginPage;
import com.ibsu.edu.ge.ui.pages.RegisterPage;
import com.ibsu.edu.ge.ui.utils.DriverFactory;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.UUID;

@Epic("User Management")
@Feature("Authentication")
public class LogoutTest extends TestBase {

    private String email;
    private final String password = "password123";
    private final String name = "Logout User";

    @BeforeMethod
    public void setUpData() {
        // წინაპირობა: ვქმნით იუზერს, რომ მერე დავლოგინდეთ და გამოვიდეთ
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        email = "logout_test_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";

        loginPage.navigateToLogin();
        registerPage.startSignup(name, email);
        registerPage.fillAllDetails(password, "Test", "User");
        registerPage.clickContinue();
        loginPage.clickLogout(); // ვტოვებთ სუფთა სისტემას
    }

    @Test(description = "Test Case 4: Logout User")
    @Severity(SeverityLevel.NORMAL)
    public void logoutUserTest() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        // 1. გადავდივართ მთავარ გვერდზე
        DriverFactory.getDriver().get("https://automationexercise.com");
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // 2. შევდივართ სისტემაში (Login)
        loginPage.navigateToLogin();
        loginPage.login(email, password);
        Assert.assertTrue(loginPage.isLoggedInAsUserVisible(), "'Logged in as...' not visible");

        // 3. გამოვდივართ (Logout)
        loginPage.clickLogout();

        // 4. ვამოწმებთ, რომ ისევ Login გვერდზე ვართ
        // (პირობის მიხედვით: "Verify that user is navigated to login page")
        Assert.assertEquals(loginPage.getLoginHeaderText(), "Login to your account", "Not returned to login page!");

        // (Optional: შექმნილი იუზერის წაშლა არ არის სავალდებულო ამ ტესტის პირობაში, მაგრამ კარგი ტონია.
        // თუმცა, რადგან უკვე გამოვედით, წაშლა რთული იქნება (თავიდან უნდა შეხვიდე), ამიტომ შეგვიძლია დავტოვოთ)
    }
}