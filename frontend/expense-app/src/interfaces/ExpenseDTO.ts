export interface ExpenseDTO {
	expenseId: number | null;
	money: number;
	dateCreated: string;
	name: string;
	category: string;
}
export interface User {
	loggedin: boolean;
}

export interface IncomeDTO {
	incomeId: number | null;
	money: number;
	dateCreated: string;
	category: string;
}
