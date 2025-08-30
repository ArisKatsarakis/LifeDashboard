import axios, { type AxiosError } from "axios";
import type { ExpenseDTO } from "../interfaces/ExpenseDTO";
import { fetchBearer, logout } from "./Variables";

const backend = process.env.REACT_APP_BACKEND;

const expensesClient = axios.create({
	baseURL: `${backend}/api/v1/expenses`,
	headers: {
		Authorization: "Bearer " + fetchBearer(),
	},
});

const fetchExpenses = async (
	page: number,
	limit: number,
): Promise<ExpenseDTO[]> => {
	try {
		page = page - 1;
		const { data } = await expensesClient.get<ExpenseDTO[]>("", {
			params: {
				page: page,
				limit: limit,
			},
		});
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

const fetchExpensesCount = async (): Promise<number> => {
	try {
		const { data } = await expensesClient.get<number>("/count");
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return 0;
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
	fetchExpensesCount,
};
