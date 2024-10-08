export interface Expense {
  expenseId?: number | null;
  money?: number | null;
  timestamp?: string | null;
  name?: string | null;
}

export interface ExpenseType {
  expenseTypeId: number | null;
  expense: Expense[];
  expenseTypeName: string | null;
  expensesSum: number | null;
}

export interface ExpenseTypeSum {
  expenseSum: number | null;
  expenses: Expense[];
}
