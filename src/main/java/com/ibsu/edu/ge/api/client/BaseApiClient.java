package com.ibsu.edu.ge.api.client;

import com.ibsu.edu.ge.api.config.ApiConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiClient {

    protected RequestSpecification request() {
        return RestAssured.given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri(ApiConfig.BASE_URL)
                        .setBasePath(ApiConfig.API_BASE_PATH)
                        .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .setAccept("*/*")
                        .addFilter(new AllureRestAssured())
                        .build())
                .relaxedHTTPSValidation();
    }

    // ეს ასუფთავებს JSON-ს HTML ტეგებისგან (რადგან საიტი ცუდად აბრუნებს პასუხს)
    protected String extractJson(String rawResponse) {
        if (rawResponse == null) return "";

        // 1. თუ JSON-ია, პირდაპირ დააბრუნოს
        if (rawResponse.trim().startsWith("{") && rawResponse.trim().endsWith("}")) {
            return rawResponse;
        }

        // 2. თუ HTML-შია გახვეული, ამოვჭრათ
        return rawResponse.replaceAll("(?s).*<body>", "")
                .replaceAll("(?s)</body>.*", "")
                .replaceAll("<.*?>", "") // ნებისმიერი სხვა ტეგიც მოაშოროს
                .trim();
    }
}