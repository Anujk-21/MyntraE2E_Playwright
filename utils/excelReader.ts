import * as XLSX from "xlsx";

// read excel and return typed data
export interface TestData {
  url: string;
  product: string;
  quantity: number;
}

export function getTestData(): TestData[] {
  const workbook = XLSX.readFile("data/data.xlsx");
  const sheet = workbook.Sheets[workbook.SheetNames[0]];
  return XLSX.utils.sheet_to_json<TestData>(sheet);
}