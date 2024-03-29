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
export interface ExpenseType {
  expenseType: string,
  expenses: [
    {
      expenseId: number,
      expenseName: string,
      expenseType: string,
      dateCreated: string,
      moneySpent: number
    }
  ],
  moneySum: number,
}

export interface ExpenseTypePromise {
  data: ExpenseType
}

export interface ExpenseStat {
  data: [ExpenseType]
}

export interface ExpenseSetPromise { }
