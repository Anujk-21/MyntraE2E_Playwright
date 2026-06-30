import * as XLSX from "xlsx";

export interface TestData {
  url: string;
  product: string;
  quantity: number;
}

export function getTestData(): TestData[] {
  const wb = XLSX.readFile("data/data.xlsx");
  const sheet = wb.Sheets[wb.SheetNames[0]];
  return XLSX.utils.sheet_to_json<TestData>(sheet);
}