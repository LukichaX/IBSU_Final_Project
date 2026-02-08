package com.ibsu.edu.ge.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(css = "a[href='/products']") private WebElement productsLink;
    @FindBy(css = "h2.title") private WebElement allProductsHeader;

    // ამას აღარ ვიყენებთ პირდაპირ, მეთოდში ვეძებთ დინამიურად
    // @FindBy(css = "div.product-image-wrapper") private List<WebElement> productList;

    @FindBy(xpath = "(//div[@class='product-overlay']//a[@data-product-id='1'])[1]") private WebElement firstProductAddBtn;
    @FindBy(xpath = "(//div[@class='product-overlay']//a[@data-product-id='2'])[1]") private WebElement secondProductAddBtn;
    @FindBy(css = "button.btn-success") private WebElement continueShoppingBtn;
    @FindBy(xpath = "//u[contains(text(), 'View Cart')]") private WebElement viewCartLink;
    @FindBy(id = "search_product") private WebElement searchInput;
    @FindBy(id = "submit_search") private WebElement searchBtn;
    @FindBy(css = "h2.title.text-center") private WebElement searchedProductsHeader;
    @FindBy(css = "a[href='/product_details/1']") private WebElement firstProductViewBtn;
    @FindBy(css = "div.product-information h2") private WebElement productName;

    // --- Methods ---

    @Step("გადასვლა Products გვერდზე")
    public void clickProductsLink() { click(productsLink); }

    @Step("შემოწმება: ჩანს თუ არა 'ALL PRODUCTS'")
    public boolean isAllProductsVisible() { return isDisplayed(allProductsHeader); }

    @Step("პირველი პროდუქტის დამატება კალათაში")
    public void addFirstProductToCart() {
        // ველოდებით, სანამ მინიმუმ 1 პროდუქტი გამოჩნდება
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.product-image-wrapper"), 0));

        // ხელახლა ვპოულობთ ელემენტებს (StaleElement თავიდან ასაცილებლად)
        List<WebElement> list = driver.findElements(By.cssSelector("div.product-image-wrapper"));

        // Hover
        actions.moveToElement(list.get(0)).perform();
        click(firstProductAddBtn);
    }

    @Step("მეორე პროდუქტის დამატება კალათაში")
    public void addSecondProductToCart() {
        // ველოდებით, სანამ მინიმუმ 2 პროდუქტი გამოჩნდება (აი აქ ვარდებოდა IndexOutOfBounds)
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.product-image-wrapper"), 1));

        List<WebElement> list = driver.findElements(By.cssSelector("div.product-image-wrapper"));

        actions.moveToElement(list.get(1)).perform();
        click(secondProductAddBtn);
    }

    @Step("Continue Shopping-ზე დაჭერა")
    public void clickContinueShopping() {
        click(continueShoppingBtn);
    }

    @Step("View Cart-ზე დაჭერა")
    public void clickViewCart() { click(viewCartLink); }

    public int getProductsCount() {
        // აქაც დაზღვევა
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.product-image-wrapper")));
        return driver.findElements(By.cssSelector("div.product-image-wrapper")).size();
    }

    public void clickFirstProductView() { click(firstProductViewBtn); }

    public boolean isProductNameVisible() { return isDisplayed(productName); }

    public void searchProduct(String name) {
        sendKeys(searchInput, name);
        click(searchBtn);
    }

    public boolean isSearchedProductsVisible() { return isDisplayed(searchedProductsHeader); }

    public String getSearchedHeaderText() { return getText(searchedProductsHeader); }
}