package com.ibsu.edu.ge.ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {
    // ThreadLocal უზრუნველყოფს, რომ ყოველ ტესტს თავისი პირადი ბრაუზერი ჰქონდეს
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        if (driver.get() == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            // options.addArguments("--headless"); // თუ არ გინდა ბრაუზერის გახსნა დაინახო

            WebDriver localDriver = new ChromeDriver(options);

            // ლოდინის დროები
            localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            localDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

            driver.set(localDriver);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // ასუფთავებს მეხსიერებას
        }
    }
}