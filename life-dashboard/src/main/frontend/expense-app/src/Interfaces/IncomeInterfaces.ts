interface Income {
    "incomeId": string;
    "incomeSourceId": number;
    "description": string;
    "dateCreated":  string;
    "moneyReceived": number;

}
interface IncomeDTO {
    "incomeId": string;
    "incomeSourceId": number;
    "description": string;
    "dateCreated":  string;
    "moneyReceived": number;
    "incomeSourceName": string;
}
interface IncomesPromise {
    data: [IncomeDTO]
}
export type {Income, IncomeDTO, IncomesPromise};