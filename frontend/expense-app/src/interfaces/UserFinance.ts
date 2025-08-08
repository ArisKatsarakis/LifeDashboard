export interface UserFinanceDTO {
	totalMoneySpent: number;
	totalMoneyPending: number;
	totalMoneyReceived: number;
}

export interface CategoryMoneyDTO {
	name: string;
	money: number;
}
