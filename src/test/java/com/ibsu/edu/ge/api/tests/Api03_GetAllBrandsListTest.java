package com.ibsu.edu.ge.api.tests;

import com.ibsu.edu.ge.api.client.BaseApiClient;
import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Api03_GetAllBrandsListTest extends BaseApiClient {

    @Test
    @Description("API 3: Get All Brands List")
    public void getAllBrandsList_shouldReturn200() {
        String raw = request()
                .when()
                .get(ApiConfig.BRANDS_LIST)
                .then()
                .statusCode(200)
                .extract()
                .asString();

        // ვიყენებთ მშობლის მეთოდს
        JsonPath json = JsonPath.from(extractJson(raw));

        Assert.assertEquals(json.getInt("responseCode"), 200, "Response Code mismatch");
        Assert.assertTrue(json.getList("brands").size() > 0, "Brands list is empty");
        Assert.assertNotNull(json.getString("brands[0].brand"), "First brand name is null");
    }
}