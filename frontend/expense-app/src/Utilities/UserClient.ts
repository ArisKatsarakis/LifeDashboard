import axios from "axios";
import type { UserFinanceDTO } from "../interfaces/UserFinance";
import { fetchBearer } from "./Variables";

const userClient = axios.create({
	baseURL: "http://localhost:8080/api/v1/user",
	headers: {
		Authorization: "Bearer " + fetchBearer(),
	},
});

const fetchUserFinance = async (): Promise<UserFinanceDTO> => {
	const { data } = await userClient.get<UserFinanceDTO>("/finance");
	return data;
};

export { fetchUserFinance };
