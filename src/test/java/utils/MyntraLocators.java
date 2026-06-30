package utils;

public class MyntraLocators {
    // Search Page Locators ---
    public static final String SEARCH_INPUT = "input[placeholder=\"Search for products, brands and more\"]";
    public static final String BLACK_COLOUR_FILTER =
            "//li[@class='colour-listItem']//span[@data-colorhex='black']";
    public static final String FIRST_PRODUCT_LINK = "a[href*=\"buy\"]";

    // Product Page Locators ---
    public static final String PRODUCT_TITLE = "//h1[@class='pdp-title']";
    public static final String PRODUCT_PRICE = "//span[@class='pdp-price']";
    public static final String SIZE_BUTTON = "(//p[@class='size-buttons-unified-size'])[3]";
    public static final String ADD_TO_BAG = "ADD TO BAG";
    public static final String GO_TO_BAG = "GO TO BAG";

    // Bag Page Locators ---
    public static final String QUANTITY_SELECTOR = ".itemComponents-base-quantity";
    public static final String QUANTITY_DIALOG = "[role=\"dialog\"]";
    public static final String QUANTITY_TWO = "//div[@class='dialogs-base-qtyList']//div[@id='2']";
    public static final String BAG_PRODUCT_BRAND = ".itemContainer-base-brand";
}
