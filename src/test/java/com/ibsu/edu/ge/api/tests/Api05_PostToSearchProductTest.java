package com.ibsu.edu.ge.api.tests;

import com.ibsu.edu.ge.api.client.BaseApiClient;
import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Api05_PostToSearchProductTest extends BaseApiClient {

    @Test
    @Description("API 5: POST To Search Product")
    public void postToSearchProduct_shouldReturn200() {
        String raw = request()
                .formParam("search_product", "tshirt")
                .when()
                .post(ApiConfig.SEARCH_PRODUCT)
                .then()
                .statusCode(200)
                .extract()
                .asString();

        JsonPath json = JsonPath.from(extractJson(raw));

        Assert.assertEquals(json.getInt("responseCode"), 200);
        Assert.assertTrue(json.getList("products").size() > 0, "Products list is empty");
    }
}