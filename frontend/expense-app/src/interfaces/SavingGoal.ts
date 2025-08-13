export interface SavingGoalDTO {
	savingGoalId: number;
	savingGoalMoney: number;
	startFrom: string;
	finishTo: string;
}
export type SpendingGoalDTO = {
	spendingGoalId: number;
	spendingMoney: number;
	spendingDay: string;
	daySpented: number;
};
export type SavingGoalCalculationsDTO = {};
