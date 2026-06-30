import { test, expect } from '@playwright/test';
import { SearchPage } from '../pages/searchPage';
import { ProductPage } from '../pages/productPage';
import { BagPage } from '../pages/bagPage';

test.only('Flow test', async ({ page }) => {

  const searchPage = new SearchPage(page);

  await searchPage.navigate();
  await expect(page).toHaveTitle(/Myntra/);

  await searchPage.searchProduct('shoes');
  await searchPage.applyBlackFilter();

  const newPage = await searchPage.openFirstProduct();

  const productPage = new ProductPage(newPage);
  const bagPage = new BagPage(newPage);

  const productName = await productPage.getProductName();
  const productPrice = await productPage.getProductPrice();

  console.log(`Product Name: ${productName}`);
  console.log(`Product Price: ${productPrice}`);

  await productPage.selectSize();
  await productPage.addToBag();
  await productPage.goToBag();

  await bagPage.changeQuantityToTwo();
  await bagPage.verifyProduct(productName!);
  await bagPage.takeScreenshot();
});