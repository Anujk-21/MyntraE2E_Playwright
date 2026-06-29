import { Page, Locator } from "@playwright/test";

export class ProductPage {
  page: Page;
  title: Locator;
  price: Locator;
  sizes: Locator;
  addToBag: Locator;
  goToBag: Locator;

  constructor(page: Page, locators: any) {
    this.page = page;

    this.title = page.locator(locators.product.title);
    this.price = page.locator(locators.product.price);
    this.sizes = page.locator(locators.product.sizes);
    this.addToBag = page.locator(locators.product.addToBag);
    this.goToBag = page.locator(locators.product.goToBag);
  }

  async details() {
    return {
      name: await this.title.textContent(),
      price: await this.price.textContent(),
    };
  }

  async selectSize() {
    await this.sizes.nth(2).click();
  }

  async addToCart() {
    await this.addToBag.click();
    await this.goToBag.click();
  }
}