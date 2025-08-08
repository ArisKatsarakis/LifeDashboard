import {
	Button,
	Container,
	Form,
	FormControl,
	FormGroup,
	FormLabel,
	Row,
} from "react-bootstrap";
import type { SavingGoalDTO } from "../interfaces/SavingGoal";
import { addSavingGoal } from "../Utilities/SavingGoalClient";

export const SavingGoalAdd = () => {
	const handleSumbit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		const target = e.target as typeof e.target & {
			savingGoalMoney: { value: number };
			startFrom: { value: string };
			finishTo: { value: string };
		};
		const payload: SavingGoalDTO = {
			savingGoalId: 0,
			savingGoalMoney: target.savingGoalMoney.value,
			startFrom: target.startFrom.value,
			finishTo: target.finishTo.value,
		};
		console.log(payload);
		const response = await addSavingGoal(payload);
		console.log(response);
	};

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
					/>
				</FormGroup>
				<FormGroup as={Row}>
					<FormLabel htmlFor="startFrom" className="form-label-sm col">
						{" "}
						Saving Goal Start:{" "}
					</FormLabel>
					<FormControl type="date" id="startFrom" className="form-contol col" />
				</FormGroup>
				<FormGroup as={Row}>
					<FormLabel htmlFor="finishTo" className="form-label-sm col">
						{" "}
						Saving Goal Finish:{" "}
					</FormLabel>
					<FormControl type="date" id="finishTo" className="form-contol col" />
				</FormGroup>
				<Button type="submit"> Save Goal </Button>
			</Form>
		</Container>
	);
};
