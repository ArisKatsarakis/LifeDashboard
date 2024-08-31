import { apiLinks } from "./Variables";
import { Expense, ExpenseType } from "../interfaces/ExpenseInterfaces";
import { Income } from "../interfaces/IncomeInterfaces";
import axios from "axios";
import { Wallet } from "../interfaces/WalletInterfaces";
const name = 'katsar';
const pass = 'test';
export const authenticateApi = async (username?: string, password?: string) => {
  let payload = {
    username: name,
    password: pass
  };
  if (username != null && password != null) {
    console.log(username, password)
    payload = {
      username: username,
      password: password
    };
  }

  const response = await axios.post(apiLinks.authenticateLink, payload);
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
  data.map(
    async (item) => {
      if (item.expenseTypeId != null) {
        const arrayOfExpenses: Expense[] = await getExpenseTypesExpenses(item.expenseTypeId);
        item.expense = arrayOfExpenses;
        return item;
      }

    }
  )
  return data;

}

export const getExpenseTypesExpenses = async (expenseTypeId: number): Promise<Expense[]> => {
  const bearerResponse = await authenticateApi();
  const { data } = await axios.get<Expense[]>(`http://localhost:8080/api/v1/expense-types/${expenseTypeId}/expenses`,
    { headers: { Authorization: `Bearer ${bearerResponse.token}` } }
  );
  return data;
}

export const getIncome = async (): Promise<Income[]> => {
  const bearerResponse = await authenticateApi();
  const { data } = await axios.get<Income[]>(apiLinks.incomeLink, { headers: { Authorization: `Bearer ${bearerResponse.token}` } });
  return data;
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


export const getLastWallet = async (): Promise<Wallet> => {
  const bearerResponse = await authenticateApi();
  try {

    const { data } = await axios.get<Wallet>(
      `http://localhost:8080/api/v1/last-wallet`,
      { headers: { Authorization: `Bearer ${bearerResponse.token}` } },
    )

    return data;
  } catch (error) {
    console.log("wallet not found");
    return error as Wallet;
  }
}

export const addIncome = async (income: Income): Promise<Income> => {
  const bearerResponse = await authenticateApi();
  try {
    const { data } = await axios.post<Income>(
      'http://localhost:8080/api/v1/incomes',
      income,
      { headers: { Authorization: `Bearer ${bearerResponse.token}` } },

    );
    return data;
  } catch (error) {
    return error as Income;
  }
}
