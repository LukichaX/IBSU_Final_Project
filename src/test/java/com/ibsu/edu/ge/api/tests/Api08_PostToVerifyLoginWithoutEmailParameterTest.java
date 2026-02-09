package com.ibsu.edu.ge.api.tests;

import com.ibsu.edu.ge.api.client.BaseApiClient;
import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Api08_PostToVerifyLoginWithoutEmailParameterTest extends BaseApiClient {

    @Test
    @Description("API 8: Verify Login without email parameter")
    public void postToVerifyLoginWithoutEmail_shouldReturn400() {
        String raw = request()
                .formParam("password", "somePass")
                .when()
                .post(ApiConfig.VERIFY_LOGIN)
                .then()
                .statusCode(200) // API 200-ს აბრუნებს, მაგრამ JSON-ში 400-ია
                .extract()
                .asString();

        JsonPath json = JsonPath.from(extractJson(raw));

        Assert.assertEquals(json.getInt("responseCode"), 400);
        Assert.assertEquals(json.getString("message"), "Bad request, email or password parameter is missing in POST request.");
    }
}