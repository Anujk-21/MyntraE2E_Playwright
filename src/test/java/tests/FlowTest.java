package tests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BagPage;
import pages.ProductPage;
import pages.SearchPage;
import utils.DriverUtils;

import java.io.FileReader;
import java.io.IOException;

@Epic("Myntra E2E")
@Feature("Search, Add to Bag and Verify Flow")
public class FlowTest {

    private Page page;
    private JsonObject config;
    private JsonObject testData;

    @BeforeEach
    public void setup() throws IOException {
        // Load Configuration
        try (FileReader configReader = new FileReader("src/test/resources/config.json")) {
            config = JsonParser.parseReader(configReader).getAsJsonObject();
        }

        // Load Test Data
        try (FileReader dataReader = new FileReader("src/test/resources/testData.json")) {
            testData = JsonParser.parseReader(dataReader).getAsJsonObject();
        }

        // Initialize Driver
        boolean isHeadless = config.get("headless").getAsBoolean();
        page = DriverUtils.initDriver(isHeadless);
    }

    @Test
    @Story("End-to-end purchase flow")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Search a product, filter, add to bag and verify in bag")
    @Description("Searches for a product, applies the black colour filter, opens the first product "
            + "in a new tab, adds it to the bag, changes the quantity to 2 and verifies the product in the bag.")
    public void testFlow() {
        System.out.println("--- Executing Myntra Flow Scenario ---");

        SearchPage searchPage = new SearchPage(page);

        // Landing page ---
        searchPage.navigate(config.get("baseUrl").getAsString());

        String expectedTitle = testData.get("expectedTitle").getAsString();
        Assertions.assertTrue(
                page.title().contains(expectedTitle),
                "Validation Failed: Page title should contain '" + expectedTitle + "'!"
        );

        // Search and filter ---
        searchPage.searchProduct(testData.get("searchProduct").getAsString());
        searchPage.applyBlackFilter();

        // The first product opens in a new tab ---
        Page productTab = searchPage.openFirstProduct();

        ProductPage productPage = new ProductPage(productTab);
        BagPage bagPage = new BagPage(productTab);

        // Product details ---
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);
        Allure.parameter("Product Name", productName);
        Allure.parameter("Product Price", productPrice);

        // Add to bag ---
        productPage.selectSize();
        productPage.addToBag();
        productPage.goToBag();

        // Bag actions and validation ---
        bagPage.changeQuantityToTwo();

        String bagProductBrand = bagPage.getBagProductBrand();
        System.out.println("Brand in bag: " + bagProductBrand);

        Assertions.assertTrue(
                bagProductBrand != null && productName != null && bagProductBrand.contains(productName),
                "Validation Failed: Bag product '" + bagProductBrand
                        + "' does not match the selected product '" + productName + "'!"
        );

        System.out.println("Flow Validation Passed!");

        bagPage.takeScreenshot();
        attachScreenshot(productTab, "bag_page");
    }

    // Attaches a screenshot of the given page to the Allure report.
    @io.qameta.allure.Attachment(value = "{name}", type = "image/png")
    private byte[] attachScreenshot(Page targetPage, String name) {
        return targetPage.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Closing browser...");
        DriverUtils.quitDriver();
    }
}
