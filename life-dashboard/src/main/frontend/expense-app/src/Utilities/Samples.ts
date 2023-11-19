import { Expense } from "../Interfaces/ExpenseInterfaces";
import { Income, IncomeDTO } from "../Interfaces/IncomeInterfaces";
export const sampleExpense: Expense  = {
    "expenseId": 1001,
    "expenseName": "utilities_expense",
    "expenseType": "UTILITIES",
    "dateCreated": "2023-01-04",
    "moneySpent": 100.0
}

export const sampleIncome :IncomeDTO = {
        incomeId: '10001',
        moneyReceived: 100.00,
        description: "income descr",
        dateCreated: '2022-10-22',
        incomeSourceId: 1, 
        incomeSourceName: "income source name"
}
export const samples  = {
    SAMPLE_EXPENSES : [sampleExpense],
    SAMPLE_INCOMES: [
       sampleIncome
    ]
};
