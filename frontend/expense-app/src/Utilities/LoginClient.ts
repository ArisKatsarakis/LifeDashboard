import axios from "axios";
import type { LoginInput, LoginResponse } from "../interfaces/LoginInput";

const urls = {
	loginUrl: "http://localhost:8080/auth",
};

const login = async (input: LoginInput): Promise<LoginResponse> => {
	const { data } = await axios.post<LoginResponse>(urls.loginUrl, input);
	return data;
};

export { login };
