package com.ibsu.edu.ge.base;

import com.ibsu.edu.ge.ui.utils.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        // საიტზე გადასვლა ყოველი ტესტის წინ
        DriverFactory.getDriver().get("https://automationexercise.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}