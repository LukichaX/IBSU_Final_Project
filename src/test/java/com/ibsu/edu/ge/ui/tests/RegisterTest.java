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
public class RegisterTest extends TestBase { // აუცილებელია extends TestBase

    @Test(description = "Test Case 1: Register User")
    @Severity(SeverityLevel.CRITICAL)
    public void registerUserTest() {
        // ინიციალიზაცია
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        // 1. მთავარი გვერდის შემოწმება (საიტი უკვე გახსნილია TestBase-ის მიერ)
        Assert.assertTrue(homePage.isHomePageVisible(), "Home page not visible");

        // 2. Signup-ზე გადასვლა
        loginPage.navigateToLogin();
        Assert.assertTrue(loginPage.isSignupHeaderVisible(), "Signup header not visible");

        // 3. მონაცემების შეყვანა
        String randomEmail = "ibsu_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";
        registerPage.startSignup("Luka", randomEmail);

        // 4. დეტალების შევსება
        Assert.assertTrue(registerPage.isAccountInfoHeaderVisible(), "Account info header not visible");
        registerPage.fillAllDetails("pass123", "Luka", "Khirdaev");

        // 5. შექმნის დადასტურება
        Assert.assertEquals(registerPage.getAccountCreatedMessage(), "ACCOUNT CREATED!");
        registerPage.clickContinue(); // აქ უკვე გვაქვს ანტი-რეკლამა ლოგიკა

        // 6. ლოგინის დადასტურება
        Assert.assertTrue(loginPage.isLoggedInAsUserVisible(), "Logged in text not visible");

        // 7. წაშლა
        registerPage.deleteAccount();
        Assert.assertEquals(registerPage.getAccountDeletedMessage(), "ACCOUNT DELETED!");
        registerPage.clickContinue();

        // ბრაუზერის დახურვა არ გვჭირდება, TestBase @AfterMethod იზამს ამას
    }
}