import { Page, Locator } from "@playwright/test";

export class SearchPage {
  page: Page;
  searchBox: Locator;
  blackFilter: Locator;
  firstProduct: Locator;

  constructor(page: Page, locators: any) {
    this.page = page;

    // assign from excel
    this.searchBox = page.locator(locators.search.searchBox);
    this.blackFilter = page.locator(locators.search.blackFilter);
    this.firstProduct = page.locator(locators.search.firstProduct).first();
  }

  async goto(url: string) {
    await this.page.goto(url);
  }

  async search(product: string) {
    await this.searchBox.fill(product);
    await this.page.keyboard.press("Enter");
  }

  async filterBlack() {
    await this.blackFilter.click();
  }

  async openProduct(): Promise<Page> {
    const [newPage] = await Promise.all([
      this.page.context().waitForEvent("page"),
      this.firstProduct.click(),
    ]);
    return newPage;
  }
}