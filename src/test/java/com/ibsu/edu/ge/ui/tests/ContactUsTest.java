package com.ibsu.edu.ge.ui.tests;

import com.ibsu.edu.ge.base.TestBase;
import com.ibsu.edu.ge.ui.pages.ContactUsPage;
import com.ibsu.edu.ge.ui.pages.HomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Customer Support")
@Feature("Contact Form")
public class ContactUsTest extends TestBase {

    @Test(description = "Test Case 6: Contact Us Form")
    @Severity(SeverityLevel.NORMAL)
    public void contactUsFormTest() {
        HomePage homePage = new HomePage();
        ContactUsPage contactPage = new ContactUsPage();

        // 1. მთავარი გვერდი
        Assert.assertTrue(homePage.isHomePageVisible());

        // 2. Contact Us-ზე გადასვლა
        contactPage.clickContactUs();
        Assert.assertTrue(contactPage.isGetInTouchVisible(), "Get In Touch header not visible");

        // 3. შევსება და ატვირთვა
        contactPage.fillFormAndUpload("Luka", "contact@test.com", "Subject Test", "Hello IBSU!");

        // 4. გაგზავნა (Alert-ის დახურვით)
        contactPage.submitForm();

        // 5. წარმატების შემოწმება
        Assert.assertTrue(contactPage.isSuccessMsgVisible(), "Success message not visible");

        // 6. უკან დაბრუნება
        contactPage.clickHome();
        Assert.assertTrue(homePage.isHomePageVisible());
    }
}