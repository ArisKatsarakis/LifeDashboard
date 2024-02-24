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
interface IncomeSourcePromiese{
    data: [IncomeSource]
}
export type { Income, IncomeDTO, IncomesPromise, IncomeSource, IncomeSourcePromiese };