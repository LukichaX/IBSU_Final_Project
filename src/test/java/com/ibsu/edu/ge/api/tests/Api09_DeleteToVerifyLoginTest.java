package com.ibsu.edu.ge.api.tests;

import com.ibsu.edu.ge.api.client.BaseApiClient;
import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Api09_DeleteToVerifyLoginTest extends BaseApiClient {

    @Test
    @Description("API 9: DELETE To Verify Login (Method Not Allowed)")
    public void deleteToVerifyLogin_shouldReturn405() {
        String raw = request()
                .when()
                .delete(ApiConfig.VERIFY_LOGIN)
                .then()
                .extract()
                .asString();

        JsonPath json = JsonPath.from(extractJson(raw));

        Assert.assertEquals(json.getInt("responseCode"), 405);
        Assert.assertEquals(json.getString("message"), "This request method is not supported.");
    }
}