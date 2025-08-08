import axios, { Axios, type AxiosError } from "axios";
import type {
	SavingGoalCalculationsDTO,
	SavingGoalDTO,
} from "../interfaces/SavingGoal";
import { fetchBearer, logout } from "./Variables";

const savingGoalClient = axios.create({
	baseURL: "http://localhost:8080/api/v1/saving-goals",
	headers: {
		Authorization: "Bearer " + fetchBearer(),
	},
});

const fetchSavingGoals = async (): Promise<SavingGoalDTO[]> => {
	try {
		const { data } = await savingGoalClient.get<SavingGoalDTO[]>("");
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return [];
};

const addSavingGoal = async (
	payload: SavingGoalDTO,
): Promise<SavingGoalDTO> => {
	try {
		const { data } = await savingGoalClient.post<SavingGoalDTO>("", payload);
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return <SavingGoalDTO>{};
};

const fetchGoalCalculations = async (
	id: number,
): Promise<SavingGoalCalculationsDTO> => {
	try {
		const { data } = await savingGoalClient.get<SavingGoalCalculationsDTO>(
			"/" + id,
		);
		return data;
	} catch (error) {
		console.log(error);
		const e = error as AxiosError;
		if (e.status === 401) {
			logout();
		}
	}
	return <SavingGoalCalculationsDTO>{};
};

export { fetchSavingGoals, addSavingGoal, fetchGoalCalculations };
