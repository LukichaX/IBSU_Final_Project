package com.ibsu.edu.ge.api.tests;

import io.qameta.allure.Attachment;
import io.restassured.response.Response;

public class AllureApiAttachments {

    private AllureApiAttachments() {}

    @Attachment(value = "{name}", type = "text/plain")
    public static String attachText(String name, String content) {
        return content;
    }

    // აი აქ არის ცვლილება: მეთოდი აბრუნებს სტრინგს
    @Attachment(value = "API Response Body", type = "application/json")
    public static String attachResponse(Response response) {
        // ვაბრუნებთ ლამაზად დაფორმატებულ JSON-ს
        return response.asPrettyString();
    }
}