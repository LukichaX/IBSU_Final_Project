package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

    // --- Navigation ---
    @FindBy(css = "a[href='/products']")
    private WebElement productsLink;

    // --- Elements ---
    @FindBy(css = "h2.title")
    private WebElement allProductsHeader; // "ALL PRODUCTS"

    @FindBy(id = "search_product")
    private WebElement searchInput;

    @FindBy(id = "submit_search")
    private WebElement searchBtn;

    @FindBy(css = "h2.title.text-center")
    private WebElement searchedProductsHeader; // "SEARCHED PRODUCTS"

    // --- Methods ---

    @Step("გადასვლა Products გვერდზე")
    public void clickProductsLink() {
        click(productsLink);
        // აქ შეგვიძლია ანტი-რეკლამა ჩავამატოთ, რადგან products-ზე გადასვლისას ხშირად ვარდება
        handleVignette();
    }

    @Step("შემოწმება: ჩანს თუ არა 'ALL PRODUCTS'")
    public boolean isAllProductsVisible() {
        return isDisplayed(allProductsHeader);
    }

    @Step("პროდუქტის ძებნა: {productName}")
    public void searchProduct(String productName) {
        sendKeys(searchInput, productName);
        click(searchBtn);
    }

    @Step("შემოწმება: ჩანს თუ არა 'SEARCHED PRODUCTS'")
    public boolean isSearchedProductsVisible() {
        return isDisplayed(searchedProductsHeader);
    }

    // დამხმარე მეთოდი რეკლამისთვის (მსგავსი რაც RegisterPage-ში გვაქვს)
    private void handleVignette() {
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                click(productsLink); // თავიდან დაჭერა
            }
        } catch (Exception e) {}
    }
}