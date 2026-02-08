package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    // --- Signup ---
    @FindBy(css = "input[data-qa='signup-name']") private WebElement nameInput;
    @FindBy(css = "input[data-qa='signup-email']") private WebElement emailInput;
    @FindBy(css = "button[data-qa='signup-button']") private WebElement signupBtn;

    // --- Account Info ---
    @FindBy(xpath = "//b[contains(text(), 'Enter Account Information')]") private WebElement accountInfoHeader;
    @FindBy(id = "id_gender1") private WebElement titleMr;
    @FindBy(id = "password") private WebElement passwordInput;
    @FindBy(id = "days") private WebElement daySelect;
    @FindBy(id = "months") private WebElement monthSelect;
    @FindBy(id = "years") private WebElement yearSelect;

    @FindBy(id = "newsletter") private WebElement newsletterCheckbox;
    @FindBy(id = "optin") private WebElement specialOffersCheckbox;

    // --- Address Info ---
    @FindBy(id = "first_name") private WebElement firstNameInput;
    @FindBy(id = "last_name") private WebElement lastNameInput;
    @FindBy(id = "company") private WebElement companyInput;
    @FindBy(id = "address1") private WebElement address1Input;
    @FindBy(id = "country") private WebElement countrySelect;
    @FindBy(id = "state") private WebElement stateInput;
    @FindBy(id = "city") private WebElement cityInput;
    @FindBy(id = "zipcode") private WebElement zipInput;
    @FindBy(id = "mobile_number") private WebElement mobileInput;
    @FindBy(css = "button[data-qa='create-account']") private WebElement createAccountBtn;

    // --- Messages ---
    @FindBy(css = "h2[data-qa='account-created']") private WebElement accountCreatedMsg;
    @FindBy(css = "a[data-qa='continue-button']") private WebElement continueBtn;
    @FindBy(xpath = "//li/a[contains(text(), 'Delete Account')]") private WebElement deleteAccountBtn;
    @FindBy(css = "h2[data-qa='account-deleted']") private WebElement accountDeletedMsg;

    // Google Vignette Dismiss Button (თუ რეკლამა ამოვარდა)
    @FindBy(id = "dismiss-button") private WebElement adDismissBtn;

    // --- Methods ---

    @Step("რეგისტრაციის დაწყება")
    public void startSignup(String name, String email) {
        sendKeys(nameInput, name);
        sendKeys(emailInput, email);
        click(signupBtn);
    }

    @Step("Account Info Header-ის შემოწმება")
    public boolean isAccountInfoHeaderVisible() {
        return isDisplayed(accountInfoHeader);
    }

    @Step("ფორმის შევსება")
    public void fillAllDetails(String password, String fName, String lName) {
        click(titleMr);
        sendKeys(passwordInput, password);

        selectByVisibleText(daySelect, "10");
        selectByVisibleText(monthSelect, "May");
        selectByVisibleText(yearSelect, "2000");

        click(newsletterCheckbox);
        click(specialOffersCheckbox);

        sendKeys(firstNameInput, fName);
        sendKeys(lastNameInput, lName);
        sendKeys(companyInput, "IBSU");
        sendKeys(address1Input, "Tbilisi, Chavchavadze Ave");
        selectByVisibleText(countrySelect, "United States");
        sendKeys(stateInput, "Georgia");
        sendKeys(cityInput, "Tbilisi");
        sendKeys(zipInput, "0100");
        sendKeys(mobileInput, "599123456");

        click(createAccountBtn);
    }

    public String getAccountCreatedMessage() {
        return getText(accountCreatedMsg);
    }

    // --- აი აქ არის ცვლილება! ---
    public void clickContinue() {
        click(continueBtn);

        // დაჭერის შემდეგ, ვამოწმებთ ამოვარდა თუ არა Google Vignette რეკლამა
        try {
            // თუ URL შეიცვალა და წერია google_vignette, ესეიგი რეკლამაა
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                System.out.println("⚠️ Google Vignette Ad detected! Refreshing to bypass...");
                driver.navigate().refresh();

                // Refresh-ის შემდეგ "Continue" ღილაკი ისევ უნდა გამოჩნდეს,
                // ან პირდაპირ გადაგვიყვანს, ამიტომ კიდევ ერთხელ ვცადოთ დაჭერა
                click(continueBtn);
            }
        } catch (Exception e) {
            System.out.println("ℹ️ No ad detected or refresh failed.");
        }
    }

    public void deleteAccount() {
        click(deleteAccountBtn);
    }

    public String getAccountDeletedMessage() {
        return getText(accountDeletedMsg);
    }
}