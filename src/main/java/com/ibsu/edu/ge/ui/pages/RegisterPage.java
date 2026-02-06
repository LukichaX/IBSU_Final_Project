package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {

    // --- Signup Elements ---
    @FindBy(css = "div[class='signup-form'] h2")
    private WebElement signupHeader;

    @FindBy(css = "input[data-qa='signup-name']")
    private WebElement nameInput;

    @FindBy(css = "input[data-qa='signup-email']")
    private WebElement emailInput;

    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signupBtn;

    @FindBy(css = "p[style='color: red;']")
    private WebElement emailErrorMsg;

    // --- Account Details Elements ---
    @FindBy(xpath = "//b[contains(text(), 'Enter Account Information')]")
    private WebElement accountInfoHeader;

    @FindBy(id = "id_gender1")
    private WebElement titleMr;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement daySelect;
    @FindBy(id = "months")
    private WebElement monthSelect;
    @FindBy(id = "years")
    private WebElement yearSelect;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement specialOffersCheckbox;

    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "address1")
    private WebElement address1Input;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(id = "country")
    private WebElement countrySelect;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "zipcode")
    private WebElement zipInput;

    @FindBy(id = "mobile_number")
    private WebElement mobileInput;

    @FindBy(css = "button[data-qa='create-account']")
    private WebElement createAccountBtn;

    // --- Confirmation Elements ---
    @FindBy(css = "h2[data-qa='account-created']")
    private WebElement accountCreatedMsg;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueBtn;

    @FindBy(xpath = "//li/a[contains(text(), 'Delete Account')]")
    private WebElement deleteAccountBtn;

    // აი ეს ელემენტი გაკლდა სავარაუდოდ:
    @FindBy(css = "h2[data-qa='account-deleted']")
    private WebElement accountDeletedMsg;


    // --- Methods ---

    @Step("შემოწმება: ჩანს თუ არა 'New User Signup!'")
    public boolean isSignupHeaderVisible() {
        return isDisplayed(signupHeader);
    }

    @Step("შემოწმება: ჩანს თუ არა 'Email Address already exist!'")
    public boolean isEmailExistErrorVisible() {
        return isDisplayed(emailErrorMsg);
    }

    @Step("რეგისტრაციის დაწყება")
    public void startSignup(String name, String email) {
        sendKeys(nameInput, name);
        sendKeys(emailInput, email);
        click(signupBtn);
    }

    @Step("შემოწმება: ჩანს თუ არა Account Info Header")
    public boolean isAccountInfoHeaderVisible() {
        return isDisplayed(accountInfoHeader);
    }

    @Step("სრული რეგისტრაციის ფორმის შევსება")
    public void fillAllDetails(String password, String firstName, String lastName) {
        click(titleMr);
        sendKeys(passwordInput, password);
        new Select(daySelect).selectByVisibleText("10");
        new Select(monthSelect).selectByVisibleText("May");
        new Select(yearSelect).selectByVisibleText("2000");

        click(newsletterCheckbox);
        click(specialOffersCheckbox);

        sendKeys(firstNameInput, firstName);
        sendKeys(lastNameInput, lastName);
        sendKeys(companyInput, "IBSU");
        sendKeys(address1Input, "Tbilisi, Chavchavadze Ave.");
        sendKeys(address2Input, "Building 2");
        new Select(countrySelect).selectByVisibleText("United States");
        sendKeys(stateInput, "Georgia");
        sendKeys(cityInput, "Tbilisi");
        sendKeys(zipInput, "0162");
        sendKeys(mobileInput, "599123456");

        click(createAccountBtn);
    }

    @Step("შემოწმება: შეიქმნა თუ არა ექაუნთი")
    public String getAccountCreatedMessage() {
        return getText(accountCreatedMsg);
    }

    public void clickContinue() {
        click(continueBtn);
        // რეკლამის შემოწმება და რეფრეში (სწრაფი ვერსია)
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                click(continueBtn);
            }
        } catch (Exception e) {}
    }

    public void deleteAccount() {
        click(deleteAccountBtn);
        // რეკლამის შემოწმება და რეფრეში (სწრაფი ვერსია)
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                click(deleteAccountBtn);
            }
        } catch (Exception e) {}
    }

    // აი ეს მეთოდი გაკლდა, რაზეც ერორს გირტყამდა:
    public String getAccountDeletedMessage() {
        return getText(accountDeletedMsg);
    }
}