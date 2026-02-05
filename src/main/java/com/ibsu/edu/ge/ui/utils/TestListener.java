package com.ibsu.edu.ge.ui.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // ტესტი თუ ჩავარდა, ვიღებთ დრაივერს და სქრინშოთს
        saveScreenshot(DriverFactory.getDriver());
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        if (driver != null) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }
}