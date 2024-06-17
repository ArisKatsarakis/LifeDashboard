import { apiLinks } from "./Variables";
import axios, { AxiosRequestConfig } from "axios";
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
}

