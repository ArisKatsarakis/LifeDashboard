import axios from "axios";
import type { LoginInput, LoginResponse } from "../interfaces/LoginInput";
import type { RegisterInput, RegisterResponse } from "../interfaces/Register";

const urls = {
	loginUrl: "http://localhost:8080/auth",
	registerUrl: "http://localhost:8080/register",
};

const login = async (input: LoginInput): Promise<LoginResponse> => {
	const { data } = await axios.post<LoginResponse>(urls.loginUrl, input);
	return data;
};

const register = async (input: RegisterInput): Promise<RegisterResponse> => {
	const { data } = await axios.post<RegisterResponse>(urls.registerUrl, input);
	return data;
};

export { login, register };
