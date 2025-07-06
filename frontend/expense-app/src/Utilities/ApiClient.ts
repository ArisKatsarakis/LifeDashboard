import axios from "axios"
import { expenseDTO } from "../interfaces/ExpenseDTO"
import { Wallet } from "../interfaces/Wallet";
import { apiLinks } from "./Variables"

const fetchExpenses = async (): Promise<expenseDTO[]> => {
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



export { fetchExpenses, addExpense, fetchWallets }
