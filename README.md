# Myntra E2E — Java + Playwright

End-to-end test for the Myntra shopping flow, converted from the original
Playwright TypeScript project to **Java + Playwright** using JUnit 5, Gson and Allure.

## Tech stack
- Java 17 / Maven
- [Playwright for Java](https://playwright.dev/java/) `1.60.0`
- JUnit 5 (Jupiter)
- Gson — reads `config.json` / `testData.json`
- Allure — reporting (JUnit 5 integration)

## Project structure
```
src/test/java
  ├─ pages/           Page Objects (SearchPage, ProductPage, BagPage)
  ├─ tests/           FlowTest (the end-to-end scenario)
  └─ utils/           DriverUtils (Playwright lifecycle), MyntraLocators
src/test/resources
  ├─ config.json      baseUrl + headless flag
  └─ testData.json    search term + expected title
```

## Test flow (`FlowTest#testFlow`)
1. Navigate to Myntra and assert the page title contains "Myntra".
2. Search for the configured product and apply the black colour filter.
3. Open the first product (opens in a **new tab**).
4. Read the product name and price.
5. Select a size, add to bag, go to bag.
6. Change the quantity to 2 and verify the product matches in the bag.
7. Capture a screenshot (`bag_page.png`) and attach it to the Allure report.

## Running

First install the Playwright browsers (one-time):
```bash
mvn compile
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

Run the tests:
```bash
mvn test
```

Generate and open the Allure report:
```bash
allure serve target/allure-results
```

Set `headless` in `src/test/resources/config.json` to `true` to run without a visible browser.
