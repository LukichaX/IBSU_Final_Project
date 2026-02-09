package com.ibsu.edu.ge.api.tests;

import com.ibsu.edu.ge.api.client.BaseApiClient;
import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Api06_PostToSearchProductWithoutParameterTest extends BaseApiClient {

    @Test
    @Description("API 6: POST To Search Product without parameter")
    public void postToSearchProduct_missingParam_shouldReturn400() {
        String raw = request()
                .when()
                .post(ApiConfig.SEARCH_PRODUCT)
                .then()
                .extract()
                .asString();

        JsonPath json = JsonPath.from(extractJson(raw));

        Assert.assertEquals(json.getInt("responseCode"), 400);
        Assert.assertEquals(json.getString("message"), "Bad request, search_product parameter is missing in POST request.");
    }
}