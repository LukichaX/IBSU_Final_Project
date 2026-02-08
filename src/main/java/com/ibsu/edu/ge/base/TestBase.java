package com.ibsu.edu.ge.base;

import com.ibsu.edu.ge.ui.utils.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    // ეს გაეშვება ავტომატურად ყოველი @Test-ის წინ
    @BeforeMethod
    public void setUp() {
        // 1. დრაივერის ინიციალიზაცია
        DriverFactory.initDriver();
        // 2. საიტზე გადასვლა (რადგან ყველა ტესტი ამ საიტზეა)
        DriverFactory.getDriver().get("https://automationexercise.com");
    }

    // ეს გაეშვება ავტომატურად ყოველი @Test-ის შემდეგ (სულერთია ტესტი ჩაიშალა თუ არა)
    @AfterMethod
    public void tearDown() {
        // ბრაუზერის დახურვა
        DriverFactory.quitDriver();
    }
}