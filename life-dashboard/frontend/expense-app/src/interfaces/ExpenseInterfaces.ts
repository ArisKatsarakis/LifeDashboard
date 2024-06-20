export interface Expense {
  expenseId?: number | null;
  money?: number | null;
  timestamp?: string | null;
}

export interface ExpenseType {
  expenseTypeId: number | null;
  expense: Expense[];
  expenseTypeName: string | null;
}
