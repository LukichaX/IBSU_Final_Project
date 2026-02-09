package com.ibsu.edu.ge.api.tests;

import com.ibsu.edu.ge.api.client.BaseApiClient;
import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Api11_PostToCreateRegisterUserAccountTest extends BaseApiClient {

    @Test
    @Description("API 11: Create User Account")
    public void postToCreateAccount_shouldReturn201() {
        String email = "api_new_" + System.currentTimeMillis() + "@test.com";

        String raw = request()
                .formParam("name", "API User")
                .formParam("email", email)
                .formParam("password", "Test@123")
                .formParam("title", "Mr")
                .formParam("birth_date", "10")
                .formParam("birth_month", "May")
                .formParam("birth_year", "1990")
                .formParam("firstname", "First")
                .formParam("lastname", "Last")
                .formParam("company", "IBSU")
                .formParam("address1", "Address 1")
                .formParam("country", "Canada")
                .formParam("zipcode", "1001")
                .formParam("state", "State")
                .formParam("city", "City")
                .formParam("mobile_number", "555123456")
                .when()
                .post(ApiConfig.CREATE_ACCOUNT)
                .then()
                .extract()
                .asString();

        JsonPath json = JsonPath.from(extractJson(raw));

        Assert.assertEquals(json.getInt("responseCode"), 201);
        Assert.assertEquals(json.getString("message"), "User created!");
    }
}