import { test, expect } from "@playwright/test";
import { getLocators } from "../utils/locatorReader";
import { getTestData } from "../utils/excelReader";
import { SearchPage } from "../pages/SearchPage";
import { ProductPage } from "../pages/ProductPage";
import { BagPage } from "../pages/BagPage";

const data = getTestData();
const locators = getLocators();

for (const row of data) {

  test(`Flow test - ${row.product}`, async ({ page }) => {

    const search = new SearchPage(page, locators);

    await search.goto(row.url);
    await expect(page).toHaveTitle(/Myntra/);

    await search.search(row.product);
    await search.filterBlack();

    const newPage = await search.openProduct();

    const product = new ProductPage(newPage, locators);

    const details = await product.details();

    await product.selectSize();
    await product.addToCart();

    const bag = new BagPage(newPage, locators);

    await bag.changeQty(row.quantity);

    const name = await bag.getName();
    expect(name).toContain(details.name);

    await newPage.screenshot({ path: "Final.png" });

  });

}