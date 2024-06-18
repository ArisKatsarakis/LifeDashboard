import { apiLinks } from "./Variables";
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

export const getExpenses = async () => {
  const bearerResponse = await authenticateApi();
  console.log(bearerResponse.token);
  const response = await axios.get(apiLinks.expensesLink, { headers: { Authorization: `Bearer ${bearerResponse.token}` } });
  console.log(response);
}

