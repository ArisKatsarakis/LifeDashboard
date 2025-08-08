import axios, { type AxiosError } from "axios";
import type { ExpenseDTO } from "../interfaces/ExpenseDTO";
import { fetchBearer, logout } from "./Variables";

const expensesClient = axios.create({
	baseURL: "http://localhost:8080/api/v1/expenses",
	headers: {
		Authorization: "Bearer " + fetchBearer(),
	},
});

const fetchExpenses = async (): Promise<ExpenseDTO[]> => {
	try {
		const { data } = await expensesClient.get<ExpenseDTO[]>("");
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return [];
};

const fetchExpenseCategories = async (): Promise<string[]> => {
	try {
		const { data } = await expensesClient.get<string[]>("/categories");
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return [];
};

const addExpense = async (expense: ExpenseDTO): Promise<ExpenseDTO> => {
	try {
		const { data } = await expensesClient.post<ExpenseDTO>("", expense);
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return <ExpenseDTO>{};
};

const fetchExpensesByCategories = async (
	category: string,
): Promise<ExpenseDTO[]> => {
	try {
		const { data } = await expensesClient.get("/categories/" + category);
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return [];
};

export {
	fetchExpenses,
	fetchExpenseCategories,
	addExpense,
	fetchExpensesByCategories,
};
