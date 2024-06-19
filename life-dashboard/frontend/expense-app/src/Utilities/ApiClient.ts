import { apiLinks } from "./Variables";
import { Expense, ExpenseType } from "../interfaces/ExpenseInterfaces";
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
