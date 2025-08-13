import axios, { type AxiosError } from "axios";
import type { UserFinanceDTO } from "../interfaces/UserFinance";
import { fetchBearer, logout } from "./Variables";

const backend = process.env.REACT_APP_BACKEND;
const mainUrl = `${backend}/api/v1/user`;

const userClient = axios.create({
	baseURL: mainUrl,
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
