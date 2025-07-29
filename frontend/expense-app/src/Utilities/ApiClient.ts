import axios from "axios"
import { expenseDTO, IncomeDTO } from "../interfaces/ExpenseDTO"
import { SingleWallet } from "../interfaces/SingleWallet";
import { Wallet } from "../interfaces/Wallet";
import { apiLinks } from "./Variables"

const fetchExpenses = async (walletId?: number): Promise<expenseDTO[]> => {
	if (walletId !== undefined) {

		return fetchWalletExpenses(walletId);
	}
	const { data } = await axios.get<expenseDTO[]>(apiLinks.expensesLink);
	return data;
}

const addExpense = async (payload: expenseDTO, walletId: number): Promise<expenseDTO> => {
	const { data } = await axios.post(`${apiLinks.walletsLink}/${walletId}/expenses`, payload);
	return data;
}


const fetchWallets = async (): Promise<Wallet[]> => {
	const { data } = await axios.get(apiLinks.walletsLink);
	return data;
}

const fetchWalletExpenses = async (walletId: number): Promise<expenseDTO[]> => {
	console.log(`walletId ${walletId}`);
	const url = apiLinks.walletsLink + '/' + walletId + '/expenses';
	const { data } = await axios.get<expenseDTO[]>(url);
	return data;
}

const createWallet = async (wallet: Wallet): Promise<Wallet> => {
	const { data } = await axios.post(apiLinks.walletsLink, wallet);
	return data;
}

const fetchIncomes = async (walletId: number): Promise<IncomeDTO[]> => {
	const { data } = await axios.get<IncomeDTO[]>(`${apiLinks.walletsLink}/${walletId}/incomes`);
	return data;
}

const createIncome = async (payload: IncomeDTO, walletId: number): Promise<IncomeDTO> => {
	const { data } = await axios.post(`${apiLinks.walletsLink}/${walletId}/incomes`, payload);
	return data;
}
const fetchSingleWallet = async (walletId: number): Promise<SingleWallet> => {
	const { data } = await axios.get<SingleWallet>(`${apiLinks.walletsLink}/${walletId}`);
	return data;
}
export { fetchExpenses, addExpense, fetchWallets, createWallet, fetchIncomes, createIncome, fetchSingleWallet }
