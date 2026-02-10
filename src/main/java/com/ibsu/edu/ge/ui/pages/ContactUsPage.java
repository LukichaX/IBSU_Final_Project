package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class ContactUsPage extends BasePage {

    // --- Navigation ---
    @FindBy(css = "a[href='/contact_us']")
    private WebElement contactUsLink;

    // --- Form ---
    @FindBy(css = "div.contact-form h2")
    private WebElement getInTouchHeader;

    @FindBy(name = "name") private WebElement nameInput;
    @FindBy(name = "email") private WebElement emailInput;
    @FindBy(name = "subject") private WebElement subjectInput;
    @FindBy(id = "message") private WebElement messageInput;

    @FindBy(name = "upload_file") private WebElement uploadInput; // type="file"

    @FindBy(name = "submit") private WebElement submitBtn;

    // --- Success ---
    @FindBy(css = "div.status.alert-success")
    private WebElement successMsg; // "Success! Your details have been submitted successfully."

    @FindBy(css = "a.btn-success")
    private WebElement homeBtn;

    // --- Methods ---

    @Step("გადასვლა Contact Us გვერდზე")
    public void clickContactUs() {
        click(contactUsLink);
    }

    @Step("ფორმის ჩატვირთვის შემოწმება")
    public boolean isGetInTouchVisible() {
        return isDisplayed(getInTouchHeader);
    }

    @Step("ფორმის შევსება და ფაილის ატვირთვა")
    public void fillFormAndUpload(String name, String email, String subject, String message) {
        sendKeys(nameInput, name);
        sendKeys(emailInput, email);
        sendKeys(subjectInput, subject);
        sendKeys(messageInput, message);

        try {
            File file = File.createTempFile("test_upload", ".txt");
            uploadInput.sendKeys(file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("File upload failed: " + e.getMessage());
        }
    }

    @Step("Submit და Alert-ის მიღება (OK)")
    public void submitForm() {
        click(submitBtn);

        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
        }
    }

    @Step("წარმატების შეტყობინების შემოწმება")
    public boolean isSuccessMsgVisible() {
        return isDisplayed(successMsg);
    }

    public void clickHome() {
        click(homeBtn);
    }
}