package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.LoginPage;
import com.ibsu.edu.ge.ui.pages.RegisterPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.UUID;

@Epic("User Management")
@Feature("Registration")
public class RegisterExistingUserTest extends TestBase {

    @Test(description = "Test Case 5: Register User with existing email")
    @Severity(SeverityLevel.NORMAL)
    public void registerExistingUserTest() {
        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        // 1. Pre-condition: ვქმნით პირველ იუზერს
        String email = "ibsu_exist_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";
        String name = "Luka";

        loginPage.navigateToLogin();
        registerPage.startSignup(name, email);
        registerPage.fillAllDetails("password", name, "Khirdaev");
        registerPage.clickContinue();
        loginPage.clickLogout(); // გამოვდივართ

        // 2. ვცდილობთ იგივე მეილით დარეგისტრირებას
        Assert.assertTrue(loginPage.isSignupHeaderVisible());
        registerPage.startSignup("Luka Duplicate", email);

        // 3. ვამოწმებთ ერორს: "Email Address already exist!"
        Assert.assertTrue(registerPage.isEmailAlreadyExistsErrorVisible(), "Error message 'Email exists' not visible!");

        // 4. Cleanup: შევდივართ ძველი იუზერით და ვშლით
        loginPage.performLogin(email, "password");
        registerPage.deleteAccount();
        registerPage.clickContinue();
    }
}