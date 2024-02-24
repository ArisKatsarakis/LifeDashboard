export interface Expense {
    expenseId: number;
    expenseType: string;
    dateCreated: string;
    moneySpent: number;
    expenseName: string;
}

export interface ExpensePromise {
    data: Expense
    
}

export interface ExpensesPromise {
    data: [Expense]
    
}