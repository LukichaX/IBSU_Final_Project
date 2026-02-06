package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "a[href='/login']")
    private WebElement loginLink;

    @FindBy(css = "div[class='login-form'] h2")
    private WebElement loginHeader;

    @FindBy(css = "input[data-qa='login-email']")
    private WebElement emailInput;

    @FindBy(css = "input[data-qa='login-password']")
    private WebElement passwordInput;

    @FindBy(css = "button[data-qa='login-button']")
    private WebElement loginBtn;

    @FindBy(xpath = "//li/a[contains(text(), 'Logout')]")
    private WebElement logoutBtn;

    @FindBy(xpath = "//li[contains(., 'Logged in as')]")
    private WebElement loggedInAsText;

    // --- Actions ---

    @Step("ნავიგაცია Login გვერდზე")
    public void navigateToLogin() {
        click(loginLink);
    }

    @Step("Login ტექსტის შემოწმება")
    public String getLoginHeaderText() {
        return getText(loginHeader);
    }

    @Step("ავტორიზაცია მომხმარებლით: {0}")
    public void login(String email, String password) {
        sendKeys(emailInput, email);

        // ვწერთ პაროლს და ეგრევე ENTER-ს
        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.ENTER);

        // ნაცვლად Thread.sleep-ისა, უბრალოდ ვამოწმებთ შეიცვალა თუ არა URL
        // თუ ისევ ლოგინზე ვართ, ესეიგი რეკლამამ შეგვიშალა ხელი
        try {
            if (driver.getCurrentUrl().contains("/login")) {
                driver.navigate().refresh();
                sendKeys(emailInput, email);
                passwordInput.sendKeys(password + Keys.ENTER);
            }
        } catch (Exception e) {}
    }

    @Step("შემოწმება: ჩანს თუ არა 'Logged in as [username]'")
    public boolean isLoggedInAsUserVisible() {
        return isDisplayed(loggedInAsText);
    }

    @Step("სისტემიდან გამოსვლა (Logout)")
    public void clickLogout() {
        click(logoutBtn);
    }
}