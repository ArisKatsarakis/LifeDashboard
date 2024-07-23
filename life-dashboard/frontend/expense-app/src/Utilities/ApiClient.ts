import { apiLinks } from "./Variables";
import { Expense, ExpenseType } from "../interfaces/ExpenseInterfaces";
import { Income } from "../interfaces/IncomeInterfaces";
import axios from "axios";
const name = 'katsar';
const pass = 'test';

export const authenticateApi = async () => {
  const payload = {
    username: name,
    password: pass
  };

  const response = await axios.post(apiLinks.authenticateLink, payload);
  console.log(response);
  return response.data;
}

export const getExpenses = async (): Promise<Expense[]> => {
  const bearerResponse = await authenticateApi();
  const { data } = await axios.get<Expense[]>(apiLinks.expensesLink, { headers: { Authorization: `Bearer ${bearerResponse.token}` } });
  return data;
}


export const getExpenseTypes = async (): Promise<ExpenseType[]> => {
  const bearerResponse = await authenticateApi();
  const { data } = await axios.get<ExpenseType[]>(apiLinks.expenseTypeLink, { headers: { Authorization: `Bearer ${bearerResponse.token}` } });
  return data;
}

export const getIncome = async (): Promise<Income> => {
  const bearerResponse = await authenticateApi();
  const { data } = await axios.get<Income[]>(apiLinks.incomeLink, { headers: { Authorization: `Bearer ${bearerResponse.token}` } });
  return data[0];
}

export const addExpenseToExpenseType = async (expenseTypeId: number, expense: Expense): Promise<Expense> => {
  const bearerResponse = await authenticateApi();
  const { data } = await axios.post<Expense>(
    `http://localhost:8080/api/v1/expense-types/${expenseTypeId}/expenses`,
    expense,
    { headers: { Authorization: `Bearer ${bearerResponse.token}` } },
  )
  return data;
}

export const addExpenseType = async (expenseType: ExpenseType): Promise<ExpenseType> => {

  const bearerResponse = await authenticateApi();
  const { data } = await axios.post<ExpenseType>(
    `http://localhost:8080/api/v1/expense-types`,
    expenseType,
    { headers: { Authorization: `Bearer ${bearerResponse.token}` } },
  )
  return data;

}
