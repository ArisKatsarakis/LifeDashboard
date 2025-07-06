import axios from "axios"
import { expenseDTO } from "../interfaces/ExpenseDTO"
import { apiLinks } from "./Variables"

const fetchExpenses = async (): Promise<expenseDTO[]> => {
	const { data } = await axios.get<expenseDTO[]>(apiLinks.expensesLink);
	return data;
}

const addExpense = async (payload: expenseDTO): Promise<expenseDTO> => {
	const { data } = await axios.post(apiLinks.expensesLink, payload);
	return data;
}



export { fetchExpenses, addExpense }
