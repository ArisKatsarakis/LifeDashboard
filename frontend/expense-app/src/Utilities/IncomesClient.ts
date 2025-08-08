import axios, { type AxiosError } from "axios";
import type { IncomeDTO } from "../interfaces/ExpenseDTO";
import { fetchBearer, logout } from "./Variables";

const incomeClient = axios.create({
	baseURL: "http://localhost:8080/api/v1/incomes",
	headers: {
		Authorization: "Bearer " + fetchBearer(),
	},
});

const fetchIncomeCategories = async (): Promise<string[]> => {
	try {
		const { data } = await incomeClient.get<string[]>("/categories");
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

const addIncome = async (incomeDto: IncomeDTO): Promise<IncomeDTO> => {
	try {
		const { data } = await incomeClient.post<IncomeDTO>("", incomeDto);
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return <IncomeDTO>{};
};

const fetcIncomes = async (): Promise<IncomeDTO[]> => {
	try {
		const { data } = await incomeClient.get<IncomeDTO[]>("");
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return <IncomeDTO[]>[];
};

const fetchIncomesByCategory = async (
	category: string,
): Promise<IncomeDTO[]> => {
	try {
		const { data } = await incomeClient.get<IncomeDTO[]>(
			"/categories/" + category,
		);
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
	fetchIncomeCategories,
	addIncome,
	fetcIncomes,
	fetchIncomesByCategory,
};
