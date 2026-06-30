# 🛍️ Myntra E-Commerce Automation (Playwright PoC)

Hi there! 👋 Welcome to my Playwright automation project. 

This repository is a **Proof of Concept (PoC)** that automates a complete end-to-end shopping journey on Myntra. Instead of just testing a single button or page, this script acts exactly like a real user—searching for shoes, filtering by color, picking a size, updating the cart, and making sure everything is correct.

## 🛠️ What's under the hood?
* **Tool:** Playwright (TypeScript)
* **Environment:** Node.js
* **Design Pattern:** Page Object Model (POM)

### 🤔 Why Page Object Model (POM)?
To keep the code clean and easy to maintain, I used the POM design pattern. Instead of writing one giant, messy script, I separated the code into specific "pages":
* `SearchPage` 🕵️‍♂️ - Handles searching, applying filters, and navigating.
* `ProductPage` 👟 - Captures product details and adds items to the bag.
* `BagPage` 🛒 - Manages the cart, updates quantities, and validates that the right product is there.

---

## 🗺️ The User Journey (How the flow works)

When you run this test, here is exactly what happens on the screen:

1. **Window Shopping:** The script opens the Myntra homepage and verifies it's on the right site.
2. **Searching:** It types "shoes" into the search bar and hits Enter.
3. **Filtering:** Because nobody likes scrolling forever, it applies a "Black" color filter.
4. **Picking a Product:** It clicks the very first shoe in the results. Playwright smartly handles the new browser tab that pops up!
5. **Taking Notes:** It reads the **Product Name** and **Price**, printing them straight to your console.
6. **Choosing a Size:** It selects a size (specifically the 3rd option, equivalent to Size 8) and clicks *Add to Bag*.
7. **Checking Out:** It navigates to the shopping bag.
8. **Updating Quantity:** Need more than one? The script opens the quantity modal, changes it to 2, and saves it.
9. **Validation (The most important part!):** It checks the cart to guarantee that the exact shoe we clicked on earlier is the one sitting in the bag.
10. **Say Cheese 📸:** Finally, it takes a screenshot (`bag_page.png`) of the cart as proof that the test passed!

---

## 📂 Project Structure

Here is how the project is organized:

```text
project-root/
│
├── pages/                  # This is where the Page Object Model lives
│   ├── searchPage.ts
│   ├── productPage.ts
│   ├── bagPage.ts
│
├── tests/                  # This is where the actual test script is
│   └── flow.spec.ts
│
├── playwright.config.ts    # Playwright settings
├── package.json            # Dependencies
└── README.md