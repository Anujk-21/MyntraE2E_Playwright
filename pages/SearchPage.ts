import { Page } from '@playwright/test';

export class SearchPage {
  constructor(private page: Page) {}

  async navigate() {
    await this.page.goto('/');
  }

  async searchProduct(product: string) {
    await this.page.fill('input[placeholder="Search for products, brands and more"]', product);
    await this.page.keyboard.press('Enter');
  }

  async applyBlackFilter() {
    await this.page.locator("//li[@class='colour-listItem']//span[@data-colorhex='black']").click();
  }

  async openFirstProduct() {
    const [newPage] = await Promise.all([
      this.page.context().waitForEvent('page'),
      this.page.locator('a[href*="buy"]').first().click()
    ]);
    return newPage;
  }
}