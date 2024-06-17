import axios from "axios";
import { apiLinks } from "./Variables";
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


