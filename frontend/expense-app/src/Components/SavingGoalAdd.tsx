import { useEffect, useState } from "react";
import {
	Button,
	Container,
	Form,
	FormControl,
	FormGroup,
	FormLabel,
	Row,
} from "react-bootstrap";
import { useParams } from "react-router-dom";
import type { SavingGoalDTO } from "../interfaces/SavingGoal";
import {
	addSavingGoal,
	fetchSavingGoalById,
	updateSavingGoal,
} from "../Utilities/SavingGoalClient";

export const SavingGoalAdd = (props: {
	savingGoal?: SavingGoalDTO | undefined;
}) => {
	const { goalId } = useParams();
	const [money, setMoney] = useState<number>(0);
	const [startFrom, setStartFrom] = useState<string>("");
	const [finishTo, setFinishTo] = useState<string>("");

	const handleSumbit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		const payload: SavingGoalDTO = {
			savingGoalId: goalId !== undefined ? parseInt(goalId) : 0,
			savingGoalMoney: money,
			startFrom: startFrom,
			finishTo: finishTo,
		};

		if (goalId !== undefined) {
			console.log(payload);
			const response = await updateSavingGoal(parseInt(goalId), payload);
			console.log(response);
			return;
		}
		const response = await addSavingGoal(payload);
		console.log(response);
	};
	useEffect(() => {
		const initialize = async () => {
			console.log(goalId);
			if (goalId !== undefined) {
				const savingGoal = await fetchSavingGoalById(parseInt(goalId));
				setMoney(savingGoal.savingGoalMoney);
				setStartFrom(savingGoal.startFrom);
				setFinishTo(savingGoal.finishTo);
			}
		};
		initialize();
	}, []);

	return (
		<Container>
			<Row>
				<h2> Add Saving Goal </h2>
			</Row>
			<Form onSubmit={handleSumbit}>
				<FormGroup as={Row}>
					<FormLabel htmlFor="savingGoalMoney" className="form-label-sm col">
						{" "}
						Saving Goal Money:{" "}
					</FormLabel>
					<FormControl
						type="number"
						id="savingGoalMoney"
						className="form-contol col"
						onChange={(e) => {
							e.preventDefault();
							setMoney(parseInt(e.currentTarget.value));
							console.log(money);
						}}
						value={money}
					/>
				</FormGroup>
				<FormGroup as={Row}>
					<FormLabel htmlFor="startFrom" className="form-label-sm col">
						{" "}
						Saving Goal Start:{" "}
					</FormLabel>
					<FormControl
						type="date"
						id="startFrom"
						className="form-contol col"
						onChange={(e) => {
							e.preventDefault();
							setStartFrom(e.currentTarget.value);
						}}
						value={startFrom}
					/>
				</FormGroup>
				<FormGroup as={Row}>
					<FormLabel htmlFor="finishTo" className="form-label-sm col">
						{" "}
						Saving Goal Finish:{" "}
					</FormLabel>
					<FormControl
						type="date"
						id="finishTo"
						className="form-contol col"
						onChange={(e) => {
							e.preventDefault();
							setFinishTo(e.currentTarget.value);
						}}
						value={finishTo}
					/>
				</FormGroup>
				<Button type="submit"> Save Goal </Button>
			</Form>
		</Container>
	);
};
