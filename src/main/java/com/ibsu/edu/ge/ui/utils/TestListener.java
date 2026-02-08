package com.ibsu.edu.ge.ui.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] saveScreenshot(org.openqa.selenium.WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (DriverFactory.getDriver() != null) {
            saveScreenshot(DriverFactory.getDriver());
        }
    }
}