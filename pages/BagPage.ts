import { Page, expect } from '@playwright/test';

export class BagPage {
  constructor(private page: Page) {}

  async changeQuantityToTwo() {
    await this.page.locator(".itemComponents-base-quantity").click();

    const modal = this.page.locator('[role="dialog"]');
    await modal.waitFor();

    await modal.locator("//div[@class='dialogs-base-qtyList']//div[@id='2']").click();
    await modal.getByRole('button', { name: 'DONE' }).click();
  }

  async verifyProduct(productName: string) {
    const bagProductName = await this.page.locator(".itemContainer-base-brand").textContent();
    expect(bagProductName).toContain(productName);
  }

  async takeScreenshot() {
    await this.page.screenshot({ path: 'bag_page.png' });
  }
}