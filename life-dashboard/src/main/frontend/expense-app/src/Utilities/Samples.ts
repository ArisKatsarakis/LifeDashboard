import { Budget } from "../Interfaces/BudgetInterfaces";
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

export const sampleBudget: Budget = {
        "budgetId": 3,
        "lastIncomeDate": "2022-10-10T15:42:35.789+00:00",
        "lastExpenseDate": "2023-11-10T16:43:14.633+00:00",
        "walletMoney": 290.00,
        "dateCreated": "2023-11-25T16:43:14.672+00:00"

}