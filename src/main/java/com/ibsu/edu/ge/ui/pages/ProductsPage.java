package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {

    // --- Navigation ---
    @FindBy(css = "a[href='/products']")
    private WebElement productsLink;

    @FindBy(css = "h2.title")
    private WebElement allProductsHeader;

    // --- Product List ---
    @FindBy(css = "div.product-image-wrapper")
    private List<WebElement> productList; // მთლიანი ბლოკები

    // პირველი პროდუქტის "Add to cart" (Overlay-ში)
    @FindBy(xpath = "(//div[@class='product-overlay']//a[@data-product-id='1'])[1]")
    private WebElement firstProductAddBtn;

    // მეორე პროდუქტის "Add to cart" (Overlay-ში)
    @FindBy(xpath = "(//div[@class='product-overlay']//a[@data-product-id='2'])[1]")
    private WebElement secondProductAddBtn;

    // Popup ღილაკი "Continue Shopping"
    @FindBy(css = "button.btn-success")
    private WebElement continueShoppingBtn;

    // ლინკი "View Cart" (Popup-ზე)
    @FindBy(xpath = "//u[contains(text(), 'View Cart')]")
    private WebElement viewCartLink;


    // --- Search Elements (რაც უკვე გქონდა) ---
    @FindBy(id = "search_product")
    private WebElement searchInput;
    @FindBy(id = "submit_search")
    private WebElement searchBtn;
    @FindBy(css = "h2.title.text-center")
    private WebElement searchedProductsHeader;
    @FindBy(css = "a[href='/product_details/1']")
    private WebElement firstProductViewBtn;
    @FindBy(css = "div.product-information h2")
    private WebElement productName;


    // --- Methods ---

    @Step("გადასვლა Products გვერდზე")
    public void clickProductsLink() {
        click(productsLink);
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                click(productsLink);
            }
        } catch (Exception e) {}
    }

    @Step("შემოწმება: ჩანს თუ არა 'ALL PRODUCTS'")
    public boolean isAllProductsVisible() {
        return isDisplayed(allProductsHeader);
    }

    @Step("პირველი პროდუქტის დამატება კალათაში (Hover-ით)")
    public void addFirstProductToCart() {
        // 1. მაუსის მიტანა პირველ პროდუქტზე
        Actions actions = new Actions(driver);
        actions.moveToElement(productList.get(0)).perform();

        // 2. ღილაკზე დაჭერა
        click(firstProductAddBtn);
    }

    @Step("მეორე პროდუქტის დამატება კალათაში (Hover-ით)")
    public void addSecondProductToCart() {
        // 1. მაუსის მიტანა მეორე პროდუქტზე
        Actions actions = new Actions(driver);
        actions.moveToElement(productList.get(1)).perform();

        // 2. ღილაკზე დაჭერა
        click(secondProductAddBtn);
    }

    @Step("Continue Shopping-ზე დაჭერა")
    public void clickContinueShopping() {
        click(continueShoppingBtn);
    }

    @Step("View Cart-ზე დაჭერა")
    public void clickViewCart() {
        click(viewCartLink);
    }

    // --- ძველი მეთოდები (შენარჩუნებულია) ---
    public int getProductsCount() { return productList.size(); }

    public void clickFirstProductView() {
        click(firstProductViewBtn);
        try {
            if (driver.getCurrentUrl().contains("#google_vignette")) {
                driver.navigate().refresh();
                click(firstProductViewBtn);
            }
        } catch (Exception e) {}
    }

    public boolean isProductNameVisible() { return isDisplayed(productName); }

    public void searchProduct(String name) {
        sendKeys(searchInput, name);
        click(searchBtn);
    }

    public boolean isSearchedProductsVisible() { return isDisplayed(searchedProductsHeader); }

    public String getSearchedHeaderText() { return getText(searchedProductsHeader); }
}