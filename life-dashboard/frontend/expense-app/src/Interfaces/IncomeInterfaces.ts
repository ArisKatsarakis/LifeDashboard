interface Income {
  "incomeId": string;
  "incomeSourceId": number;
  "description": string;
  "dateCreated": string;
  "moneyReceived": number;

}
interface IncomeDTO {
  "incomeId": string;
  "incomeSourceId": number;
  "description": string;
  "dateCreated": string;
  "moneyReceived": number;
  "incomeSourceName": string;
}
interface IncomesPromise {
  data: [IncomeDTO]
}

interface IncomeSource {

  "incomeSourceId": number,
  "stabilityType": string,
  "incomeType": string,
  "name": string

}

interface IncomeSourcePromiese {
  data: [IncomeSource]
}

export interface IncomeStats {
  moneySum: number;
  incomes: [
    Income
  ],
  incomeType: string,
  incomeSource: string

}
export interface IncomeStatsPromise {
  data: [IncomeStats]
}

export type { Income, IncomeDTO, IncomesPromise, IncomeSource, IncomeSourcePromiese };
