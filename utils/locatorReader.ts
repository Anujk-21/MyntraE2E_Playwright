import * as XLSX from "xlsx";

type LocatorMap = {
  [page: string]: {
    [element: string]: string;
  };
};

export function getLocators(): LocatorMap {
  const workbook = XLSX.readFile("data/locator.xlsx");
  const sheet = workbook.Sheets[workbook.SheetNames[0]];
  const rows = XLSX.utils.sheet_to_json<any>(sheet);

  const locators: LocatorMap = {};

  for (const row of rows) {
    if (!locators[row.page]) {
      locators[row.page] = {};
    }
    locators[row.page][row.element] = row.locator;
  }

  return locators;
}