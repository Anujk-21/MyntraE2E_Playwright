package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import utils.MyntraLocators;

public class SearchPage {
    private final Page page;

    public SearchPage(Page page) {
        this.page = page;
    }

    public void navigate(String url) {
        System.out.println("Navigating to URL: " + url);
        page.navigate(url, new Page.NavigateOptions()
                .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
                .setTimeout(60000));
    }

    public void searchProduct(String product) {
        System.out.println("Searching for product: " + product);
        page.fill(MyntraLocators.SEARCH_INPUT, product);
        page.keyboard().press("Enter");
    }

    public void applyBlackFilter() {
        System.out.println("Applying 'black' colour filter...");
        page.locator(MyntraLocators.BLACK_COLOUR_FILTER).click();
    }

    public Page openFirstProduct() {
        System.out.println("Opening first product in a new tab...");
        // Clicking the product opens a new tab; wait for that page event.
        return page.context().waitForPage(() ->
                page.locator(MyntraLocators.FIRST_PRODUCT_LINK).first().click()
        );
    }
}
