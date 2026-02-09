package com.ibsu.edu.ge.api.tests;

import com.ibsu.edu.ge.api.client.BaseApiClient;
import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Api10_PostToVerifyLoginWithInvalidDetailsTest extends BaseApiClient {

    @Test
    @Description("API 10: Verify Login with invalid details")
    public void postToVerifyLogin_invalidDetails_shouldReturn404() {
        String raw = request()
                .formParam("email", "invalid_" + System.currentTimeMillis() + "@test.com")
                .formParam("password", "wrongPass")
                .when()
                .post(ApiConfig.VERIFY_LOGIN)
                .then()
                .extract()
                .asString();

        JsonPath json = JsonPath.from(extractJson(raw));

        Assert.assertEquals(json.getInt("responseCode"), 404);
        Assert.assertEquals(json.getString("message"), "User not found!");
    }
}