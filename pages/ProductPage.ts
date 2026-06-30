import { Page } from '@playwright/test';

export class ProductPage {
  constructor(private page: Page) {}

  async getProductName() {
    return await this.page.locator("//h1[@class='pdp-title']").textContent();
  }

  async getProductPrice() {
    return await this.page.locator("//span[@class='pdp-price']").textContent();
  }

  async selectSize() {
    await this.page.locator("(//p[@class='size-buttons-unified-size'])[3]").click();
  }

  async addToBag() {
    await this.page.getByText('ADD TO BAG').click();
  }

  async goToBag() {
    await this.page.getByText('GO TO BAG').click();
  }
}