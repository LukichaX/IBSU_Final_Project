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
@Feature("Registration")
public class RegisterTest extends TestBase {

    @Test(description = "Test Case 1: Register User")
    @Severity(SeverityLevel.CRITICAL)
    public void registerUserTest() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        Assert.assertTrue(homePage.isHomePageVisible(), "Home page not visible");
        loginPage.navigateToLogin();
        Assert.assertTrue(registerPage.isSignupHeaderVisible(), "Signup header not visible");

        String randomEmail = "ibsu_auto_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";
        registerPage.startSignup("IBSU Student", randomEmail);

        Assert.assertTrue(registerPage.isAccountInfoHeaderVisible());
        registerPage.fillAllDetails("password123", "Luka", "Khirdaev");

        Assert.assertEquals(registerPage.getAccountCreatedMessage(), "ACCOUNT CREATED!");
        registerPage.clickContinue();

        Assert.assertTrue(loginPage.isLoggedInAsUserVisible(), "'Logged in as...' text not visible");

        registerPage.deleteAccount();
        Assert.assertEquals(registerPage.getAccountDeletedMessage(), "ACCOUNT DELETED!");
        registerPage.clickContinue();
    }
}