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
public class LoginTest extends TestBase {

    private String email;
    private final String password = "password123";
    private final String name = "Login User";

    @BeforeMethod
    public void setUpData() {
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        // 1. ვქმნით იუზერს, რომლითაც ვიმუშავებთ
        email = "login_test_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";

        loginPage.navigateToLogin();
        registerPage.startSignup(name, email);
        registerPage.fillAllDetails(password, "Test", "User");
        registerPage.clickContinue();
        loginPage.clickLogout();
    }

    @Test(description = "Test Case 2: Login User with correct email and password")
    @Severity(SeverityLevel.CRITICAL)
    public void loginUserTest() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        // --- შესწორება: გადავდივართ მთავარ გვერდზე ---
        DriverFactory.getDriver().get("https://automationexercise.com");

        // 3. Verify home page
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        // 4. Navigate to Login
        loginPage.navigateToLogin();

        // 5. Verify Header
        Assert.assertEquals(loginPage.getLoginHeaderText(), "Login to your account");

        // 6-7. Login
        loginPage.login(email, password);

        // 8. Verify Logged in as...
        Assert.assertTrue(loginPage.isLoggedInAsUserVisible(), "'Logged in as...' text is not visible");

        // 9. Delete Account
        registerPage.deleteAccount();

        // 10. Verify Deleted
        Assert.assertEquals(registerPage.getAccountDeletedMessage(), "ACCOUNT DELETED!");
    }
}