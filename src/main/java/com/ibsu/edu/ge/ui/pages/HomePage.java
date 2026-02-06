package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "div[id='slider-carousel']")
    private WebElement mainSlider;

    @Step("მთავარი გვერდის ვიზუალურად შემოწმება")
    public boolean isHomePageVisible() {
        return isDisplayed(mainSlider);
    }
}