package com.ibsu.edu.ge.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "img[alt='Website for automation practice']")
    private WebElement homeLogo;

    @FindBy(xpath = "//a[@style='color: orange;']")
    private WebElement activeHomeBtn;

    public boolean isHomePageVisible() {
        return isDisplayed(homeLogo);
    }
}