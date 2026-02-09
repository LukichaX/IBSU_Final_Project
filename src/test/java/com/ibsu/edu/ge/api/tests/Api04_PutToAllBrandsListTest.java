package com.ibsu.edu.ge.api.tests;

import com.ibsu.edu.ge.api.client.BaseApiClient;
import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Api04_PutToAllBrandsListTest extends BaseApiClient {

    @Test
    @Description("API 4: PUT To All Brands List (Method Not Allowed)")
    public void putToAllBrandsList_shouldReturn405() {
        String raw = request()
                .when()
                .put(ApiConfig.BRANDS_LIST)
                .then()
                .extract()
                .asString();

        JsonPath json = JsonPath.from(extractJson(raw));

        Assert.assertEquals(json.getInt("responseCode"), 405);
        Assert.assertEquals(json.getString("message"), "This request method is not supported.");
    }
}