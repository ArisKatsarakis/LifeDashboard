export interface expenseDTO {
	expenseId: number | null;
	money: number;
	dateCreated: string;
	name: string;
}
export interface User {
	loggedin: boolean;
}

export interface IncomeDTO {
	incomeId: number | null;
	money: number;
	dateCreated: string;
}
