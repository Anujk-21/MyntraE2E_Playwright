package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.MyntraLocators;

import java.nio.file.Paths;

public class BagPage {
    private final Page page;

    public BagPage(Page page) {
        this.page = page;
    }

    public void changeQuantityToTwo() {
        System.out.println("Changing quantity to 2...");
        page.locator(MyntraLocators.QUANTITY_SELECTOR).click();

        Locator modal = page.locator(MyntraLocators.QUANTITY_DIALOG);
        modal.waitFor();

        modal.locator(MyntraLocators.QUANTITY_TWO).click();
        modal.getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName("DONE")).click();
    }

    public String getBagProductBrand() {
        return page.locator(MyntraLocators.BAG_PRODUCT_BRAND).textContent();
    }

    public void takeScreenshot() {
        System.out.println("Capturing bag page screenshot...");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("bag_page.png")));
    }
}
