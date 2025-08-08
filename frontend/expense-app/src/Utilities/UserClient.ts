import axios, { type AxiosError } from "axios";
import type { UserFinanceDTO } from "../interfaces/UserFinance";
import { fetchBearer, logout } from "./Variables";

const userClient = axios.create({
	baseURL: "http://localhost:8080/api/v1/user",
	headers: {
		Authorization: "Bearer " + fetchBearer(),
	},
});

const fetchUserFinance = async (): Promise<UserFinanceDTO> => {
	try {
		const { data } = await userClient.get<UserFinanceDTO>("/finance");
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return <UserFinanceDTO>{};
};

export { fetchUserFinance };
