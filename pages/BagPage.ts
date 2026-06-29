import { Page, Locator, expect } from "@playwright/test";

export class BagPage {
  page: Page;
  qtyDropdown: Locator;
  modal: Locator;
  doneBtn: Locator;
  productName: Locator;

  constructor(page: Page, locators: any) {
    this.page = page;

    this.qtyDropdown = page.locator(locators.bag.qtyDropdown);
    this.modal = page.getByRole("dialog");
    this.doneBtn = page.locator(locators.bag.doneBtn);
    this.productName = page.locator(locators.bag.productName);
  }

  async changeQty(qty: number) {
    await this.qtyDropdown.click();
    await expect(this.modal).toBeVisible();

    await this.modal.getByText(String(qty)).click();
    await this.doneBtn.click();
  }

  async getName() {
    return await this.productName.textContent();
  }
}