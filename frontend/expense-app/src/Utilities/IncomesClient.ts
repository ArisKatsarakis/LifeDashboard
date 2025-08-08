import axios from "axios";
import type { IncomeDTO } from "../interfaces/ExpenseDTO";
import { fetchBearer } from "./Variables";

const incomeClient = axios.create({
	baseURL: "http://localhost:8080/api/v1/incomes",
	headers: {
		Authorization: "Bearer " + fetchBearer(),
	},
});

const fetchIncomeCategories = async (): Promise<string[]> => {
	const { data } = await incomeClient.get<string[]>("/categories");
	return data;
};

const addIncome = async (incomeDto: IncomeDTO): Promise<IncomeDTO> => {
	const { data } = await incomeClient.post<IncomeDTO>("", incomeDto);
	return data;
};

const fetcIncomes = async (): Promise<IncomeDTO[]> => {
	const { data } = await incomeClient.get<IncomeDTO[]>("");
	return data;
};

const fetchIncomesByCategory = async (
	category: string,
): Promise<IncomeDTO[]> => {
	const { data } = await incomeClient.get<IncomeDTO[]>(
		"/categories/" + category,
	);
	return data;
};

export {
	fetchIncomeCategories,
	addIncome,
	fetcIncomes,
	fetchIncomesByCategory,
};
