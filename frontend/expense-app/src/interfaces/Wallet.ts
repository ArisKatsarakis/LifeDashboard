import { expenseDTO } from "./ExpenseDTO";

export interface Wallet {
	walletName: string | null,
	expenses?: expenseDTO[] | null | undefined,
	totalPending?: number | null,
	walletId: number | null,
}

