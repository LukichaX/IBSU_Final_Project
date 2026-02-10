package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // --- Navigation ---
    @FindBy(css = "a[href='/login']")
    private WebElement loginLink;

    // --- Headers ---
    @FindBy(css = "div.signup-form h2")
    private WebElement signupHeader; // "New User Signup!"

    @FindBy(css = "div.login-form h2")
    private WebElement loginHeader; // "Login to your account"

    // --- Login Form Elements ---
    @FindBy(css = "input[data-qa='login-email']")
    private WebElement loginEmailInput;

    @FindBy(css = "input[data-qa='login-password']")
    private WebElement loginPasswordInput;

    @FindBy(css = "button[data-qa='login-button']")
    private WebElement loginBtn;

    // --- Post Login ---
    @FindBy(xpath = "//li/a[contains(text(), 'Logout')]")
    private WebElement logoutBtn;

    @FindBy(xpath = "//li/a[contains(text(), 'Logged in as')]")
    private WebElement loggedInAsText;

    // --- Methods ---

    @Step("Login გვერდზე გადასვლა")
    public void navigateToLogin() {
        click(loginLink);
    }

    @Step("შემოწმება: ჩანს თუ არა Signup ფორმა")
    public boolean isSignupHeaderVisible() {
        return isDisplayed(signupHeader);
    }

    @Step("შემოწმება: ჩანს თუ არა Login ფორმა")
    public boolean isLoginHeaderVisible() {
        return isDisplayed(loginHeader);
    }

    @Step("სისტემაში შესვლა")
    public void performLogin(String email, String password) {
        sendKeys(loginEmailInput, email);
        sendKeys(loginPasswordInput, password);
        click(loginBtn);
    }

    @Step("შემოწმება: შევიდა თუ არა მომხმარებელი")
    public boolean isLoggedInAsUserVisible() {
        return isDisplayed(loggedInAsText);
    }

    @Step("სისტემიდან გამოსვლა")
    public void clickLogout() {
        click(logoutBtn);

        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                System.out.println("⚠️ Google Vignette detected on Logout! Refreshing...");
                driver.navigate().refresh();

                if (isDisplayed(logoutBtn)) {
                    click(logoutBtn);
                }
            }
        } catch (Exception e) {
        }
    }
}