package com.ibsu.edu.ge.api.tests;

import com.ibsu.edu.ge.api.client.BaseApiClient;
import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Api07_PostToVerifyLoginWithValidDetailsTest extends BaseApiClient {

    private String email;
    private final String password = "Test12345!";
    private final String name = "Api Test User";

    @BeforeClass
    @Step("Create user for API 7")
    public void createUser() {
        // უნიკალური მეილის გენერაცია (ეს კარგია, დატოვე)
        email = "api07_" + System.currentTimeMillis() + "@test.com";

        Response response = request()
                .formParam("name", name)
                .formParam("email", email)
                .formParam("password", password)
                .formParam("title", "Mr")
                .formParam("birth_date", "10")
                .formParam("birth_month", "May")
                .formParam("birth_year", "1998")
                .formParam("firstname", "Api")
                .formParam("lastname", "User")
                .formParam("company", "IBSU")
                .formParam("address1", "Tbilisi")
                .formParam("country", "India")
                .formParam("state", "Tbilisi")
                .formParam("city", "Tbilisi")
                .formParam("zipcode", "0101")
                .formParam("mobile_number", "555111222")
                .post(ApiConfig.CREATE_ACCOUNT);

        // რეპორტში ჩამატება
        AllureApiAttachments.attachResponse(response);

        // --- გამარტივებული შემოწმება ---
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");

        // Map-ის მაგივრად, პირდაპირ jsonPath-ს ვიყენებთ (უფრო გასაგებია)
        String responseCode = response.jsonPath().getString("responseCode");
        String message = response.jsonPath().getString("message");

        Assert.assertEquals(responseCode, "201", "Response Code mismatch");
        Assert.assertEquals(message, "User created!", "Message mismatch");
    }

    @Test
    @Description("API 7: Verify Login with valid details")
    public void postToVerifyLoginWithValidDetails_shouldReturn200() {

        Response response = request()
                .formParam("email", email)
                .formParam("password", password)
                .post(ApiConfig.VERIFY_LOGIN);

        AllureApiAttachments.attachResponse(response);

        Assert.assertEquals(response.statusCode(), 200, "HTTP Status should be 200");

        // აქაც პირდაპირ ვიღებთ მნიშვნელობებს
        String responseCode = response.jsonPath().getString("responseCode");
        String message = response.jsonPath().getString("message");

        Assert.assertEquals(responseCode, "200", "Response Code mismatch");
        Assert.assertEquals(message, "User exists!", "Message mismatch");
    }

    @AfterClass(alwaysRun = true)
    @Step("Delete created user")
    public void deleteUser() {
        if (email == null) return;

        Response response = request()
                .formParam("email", email)
                .formParam("password", password)
                .delete(ApiConfig.DELETE_ACCOUNT);

        AllureApiAttachments.attachResponse(response);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), "Account deleted!");
    }
}