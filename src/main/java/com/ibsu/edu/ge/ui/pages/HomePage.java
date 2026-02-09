package com.ibsu.edu.ge.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    // ძველი, მოძრავი სლაიდერის მაგივრად, ვეძებთ ლოგოს
    // ეს 100%-ით სულ ჩანს და არ მოძრაობს
    @FindBy(css = "img[alt='Website for automation practice']")
    private WebElement homeLogo;

    // ან "Home" ღილაკი, რომელიც სტაფილოსფერია (active)
    @FindBy(xpath = "//a[@style='color: orange;']")
    private WebElement activeHomeBtn;

    public boolean isHomePageVisible() {
        // ვამოწმებთ ლოგოს - ყველაზე საიმედოა
        return isDisplayed(homeLogo);
    }
}