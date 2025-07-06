import axios from "axios"
import { expenseDTO } from "../interfaces/ExpenseDTO"
import { Wallet } from "../interfaces/Wallet";
import { apiLinks } from "./Variables"

const fetchExpenses = async (walletId?: number): Promise<expenseDTO[]> => {
	if (walletId !== undefined) {

		return fetchWalletExpenses(walletId);
	}
	const { data } = await axios.get<expenseDTO[]>(apiLinks.expensesLink);
	return data;
}

const addExpense = async (payload: expenseDTO): Promise<expenseDTO> => {
	const { data } = await axios.post(apiLinks.expensesLink, payload);
	return data;
}


const fetchWallets = async (): Promise<Wallet[]> => {
	const { data } = await axios.get(apiLinks.walletsLink);
	return data;
}

const fetchWalletExpenses = async (walletId: number): Promise<expenseDTO[]> => {
	const url = apiLinks.walletsLink + '/' + walletId + '/expenses';
	const { data } = await axios.get<expenseDTO[]>(url);
	return data;
}

const createWallet = async (wallet: Wallet): Promise<Wallet> => {
	const { data } = await axios.post(apiLinks.walletsLink, wallet);
	return data;
}



export { fetchExpenses, addExpense, fetchWallets, createWallet }
