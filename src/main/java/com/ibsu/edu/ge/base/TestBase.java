package com.ibsu.edu.ge.base;

import com.ibsu.edu.ge.ui.utils.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get("https://automationexercise.com");
    }

    @AfterMethod
    public void tearDown() {
        // ბრაუზერის დახურვა
        DriverFactory.quitDriver();
    }
}