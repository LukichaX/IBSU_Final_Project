package com.ibsu.edu.ge.api.tests;

import com.ibsu.edu.ge.api.client.BaseApiClient;
import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Api12_DeleteMethodToDeleteUserAccountTest extends BaseApiClient {

    @Test
    @Description("API 12: Delete User Account")
    public void deleteAccount_shouldReturn200() {
        String email = "api_del_" + System.currentTimeMillis() + "@test.com";
        String password = "Test@123";

        // 1. Precondition: Create User
        request()
                .formParam("name", "User")
                .formParam("email", email)
                .formParam("password", password)
                .formParam("title", "Mr")
                .formParam("birth_date", "01")
                .formParam("birth_month", "January")
                .formParam("birth_year", "1990")
                .formParam("firstname", "F")
                .formParam("lastname", "L")
                .formParam("company", "C")
                .formParam("address1", "A")
                .formParam("country", "Canada")
                .formParam("zipcode", "0000")
                .formParam("state", "S")
                .formParam("city", "C")
                .formParam("mobile_number", "000000000")
                .post(ApiConfig.CREATE_ACCOUNT)
                .then()
                .statusCode(200);

        // 2. Test: Delete User
        String raw = request()
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .delete(ApiConfig.DELETE_ACCOUNT)
                .then()
                .extract()
                .asString();

        JsonPath json = JsonPath.from(extractJson(raw));

        Assert.assertEquals(json.getInt("responseCode"), 200);
        Assert.assertEquals(json.getString("message"), "Account deleted!");
    }
}