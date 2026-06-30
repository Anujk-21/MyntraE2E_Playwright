package pages;

import com.microsoft.playwright.Page;
import utils.MyntraLocators;

public class ProductPage {
    private final Page page;

    public ProductPage(Page page) {
        this.page = page;
    }

    public String getProductName() {
        return page.locator(MyntraLocators.PRODUCT_TITLE).textContent();
    }

    public String getProductPrice() {
        return page.locator(MyntraLocators.PRODUCT_PRICE).textContent();
    }

    public void selectSize() {
        System.out.println("Selecting a size...");
        page.locator(MyntraLocators.SIZE_BUTTON).click();
    }

    public void addToBag() {
        System.out.println("Adding product to bag...");
        page.getByText(MyntraLocators.ADD_TO_BAG).click();
    }

    public void goToBag() {
        System.out.println("Navigating to bag...");
        page.getByText(MyntraLocators.GO_TO_BAG).click();
    }
}
