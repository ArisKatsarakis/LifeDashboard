import axios from "axios";
import type { ExpenseDTO } from "../interfaces/ExpenseDTO";
import { fetchBearer } from "./Variables";

const expensesClient = axios.create({
	baseURL: "http://localhost:8080/api/v1/expenses",
	headers: {
		Authorization: "Bearer " + fetchBearer(),
	},
});

const fetchExpenses = async (): Promise<ExpenseDTO[]> => {
	const { data } = await expensesClient.get<ExpenseDTO[]>("");
	return data;
};

const fetchExpenseCategories = async (): Promise<string[]> => {
	const { data } = await expensesClient.get<string[]>("/categories");
	return data;
};

const addExpense = async (expense: ExpenseDTO): Promise<ExpenseDTO> => {
	const { data } = await expensesClient.post<ExpenseDTO>("", expense);
	return data;
};

const fetchExpensesByCategories = async (
	category: string,
): Promise<ExpenseDTO[]> => {
	const { data } = await expensesClient.get("/categories/" + category);
	return data;
};

export {
	fetchExpenses,
	fetchExpenseCategories,
	addExpense,
	fetchExpensesByCategories,
};
