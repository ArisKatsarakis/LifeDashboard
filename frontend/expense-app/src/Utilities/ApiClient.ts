import axios from "axios";
import type { expenseDTO, IncomeDTO } from "../interfaces/ExpenseDTO";
import type { SingleWallet } from "../interfaces/SingleWallet";
import type { Wallet } from "../interfaces/Wallet";
import { apiLinks } from "./Variables";

const fetchBearer = (): string => {
	const token = window.localStorage.getItem("jwt")?.toString();
	console.log(token);
	return token !== undefined ? token : "";
};

const client = axios.create({
	headers: {
		Authorization: "Bearer " + fetchBearer(),
	},
});

const fetchExpenses = async (walletId?: number): Promise<expenseDTO[]> => {
	if (walletId !== undefined) {
		return fetchWalletExpenses(walletId);
	}
	const { data } = await client.get<expenseDTO[]>(apiLinks.expensesLink);
	return data;
};

const addExpense = async (
	payload: expenseDTO,
	walletId: number,
): Promise<expenseDTO> => {
	const { data } = await client.post(
		`${apiLinks.walletsLink}/${walletId}/expenses`,
		payload,
	);
	return data;
};

const fetchWallets = async (): Promise<Wallet[]> => {
	const { data } = await client.get(apiLinks.walletsLink);
	return data;
};

const fetchWalletExpenses = async (walletId: number): Promise<expenseDTO[]> => {
	const url = apiLinks.walletsLink + "/" + walletId + "/expenses";
	const { data } = await client.get<expenseDTO[]>(url);
	return data;
};

const createWallet = async (wallet: Wallet): Promise<Wallet> => {
	const { data } = await client.post(apiLinks.walletsLink, wallet);
	return data;
};

const fetchIncomes = async (walletId: number): Promise<IncomeDTO[]> => {
	const { data } = await client.get<IncomeDTO[]>(
		`${apiLinks.walletsLink}/${walletId}/incomes`,
	);
	return data;
};

const createIncome = async (
	payload: IncomeDTO,
	walletId: number,
): Promise<IncomeDTO> => {
	const { data } = await client.post(
		`${apiLinks.walletsLink}/${walletId}/incomes`,
		payload,
	);
	return data;
};
const fetchSingleWallet = async (walletId: number): Promise<SingleWallet> => {
	const { data } = await client.get<SingleWallet>(
		`${apiLinks.walletsLink}/${walletId}`,
	);
	return data;
};

export {
	fetchExpenses,
	addExpense,
	fetchWallets,
	createWallet,
	fetchIncomes,
	createIncome,
	fetchSingleWallet,
};
