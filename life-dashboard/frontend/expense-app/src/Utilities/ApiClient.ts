import { apiLinks } from "./Variables";
import { Expense } from "../interfaces/ExpenseInterfaces";
import axios, { AxiosPromise } from "axios";
import { Battery0Bar } from "@mui/icons-material";
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

