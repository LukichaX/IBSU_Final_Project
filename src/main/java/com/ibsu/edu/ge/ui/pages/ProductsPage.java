package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BasePage {

    // --- Navigation ---
    @FindBy(css = "a[href='/products']")
    private WebElement productsLink;

    // ეს ლინკი არის მოდალურ ფანჯარაში
    @FindBy(xpath = "//u[contains(text(), 'View Cart')]")
    private WebElement viewCartLink;

    // --- Elements ---
    @FindBy(css = "h2.title")
    private WebElement allProductsHeader; // "ALL PRODUCTS"

    @FindBy(id = "search_product")
    private WebElement searchInput;

    @FindBy(id = "submit_search")
    private WebElement searchBtn;

    @FindBy(css = "h2.title.text-center")
    private WebElement searchedProductsHeader; // "SEARCHED PRODUCTS"

    // მოდალური ფანჯარა ("Added to cart")
    @FindBy(css = "button.btn-success")
    private WebElement continueShoppingBtn;

    // View Product ღილაკი (პირველი პროდუქტისთვის)
    @FindBy(css = "a[href='/product_details/1']")
    private WebElement viewFirstProductBtn;


    // --- Methods ---

    @Step("გადასვლა Products გვერდზე")
    public void clickProductsLink() {
        click(productsLink);
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

    @Step("პირველი პროდუქტის დამატება კალათაში")
    public void addFirstProductToCart() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.product-image-wrapper"), 0));

        List<WebElement> products = driver.findElements(By.cssSelector("div.product-image-wrapper"));
        WebElement firstProd = products.get(0);

        actions.moveToElement(firstProd).perform();

        WebElement addBtn = firstProd.findElement(By.cssSelector("a.add-to-cart"));
        click(addBtn);

        wait.until(ExpectedConditions.visibilityOf(continueShoppingBtn));

    }

    @Step("პირველი პროდუქტის დეტალების გახსნა")
    public void clickFirstViewProduct() {
        js.executeScript("arguments[0].click();", viewFirstProductBtn);
        handleVignette();
    }

    @Step("View Cart-ზე დაჭერა")
    public void clickViewCart() {
        click(viewCartLink);
    }

    private void handleVignette() {
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                click(productsLink);
            }
        } catch (Exception e) {}
    }
}