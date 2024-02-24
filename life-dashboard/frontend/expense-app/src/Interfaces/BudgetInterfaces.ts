export interface Entry {
    "entryId": number;
    "money": number;
    "entryType": string;
    "dateInserted": string;
    "incomeId": number;
    "expenseId": number;
};


export interface Budget {
    "budgetId": number;
    "lastIncomeDate": string;
    "lastExpenseDate": string;
    "walletMoney": number;
    "dateCreated": string;
}



export interface BudgetHistory {
    "budget": Budget,
    "tenLastEntries": [
        Entry
    ]
}

export interface BudgetHistoryPromise {
    "data": BudgetHistory
    
}