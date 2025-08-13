import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import type { SavingGoalDTO, SpendingGoalDTO } from "../interfaces/SavingGoal";
import {
	addSpendingGoals,
	fetchSavingGoals,
} from "../Utilities/SavingGoalClient";

export const SavingGoalList = () => {
	const [savingGoals, setSavingGoals] = useState<SavingGoalDTO[]>([]);
	const [savingGoalsMap, setSavingGoalsMap] = useState<
		Map<SavingGoalDTO, SpendingGoalDTO[]>
	>(new Map<SavingGoalDTO, SpendingGoalDTO[]>());

	const navigate = useNavigate();

	const createSpendingGoals = async (
		e: React.MouseEvent<HTMLButtonElement>,
	) => {
		e.preventDefault();
		console.log(e.currentTarget.value);
		const savingGoalId = parseInt(e.currentTarget.value);
		const spendingGoals = await addSpendingGoals(savingGoalId);
		console.log(spendingGoals);
	};

	useEffect(() => {
		const initialize = async () => {
			const response = await fetchSavingGoals();
			const tempMap = new Map<SavingGoalDTO, SpendingGoalDTO[]>();
			for (let i = 0; i < response.length; i++) {
				const savingGoal = response[i];
				const spendingGoals = await addSpendingGoals(savingGoal.savingGoalId);
				tempMap.set(savingGoal, spendingGoals);
			}
			setSavingGoalsMap(tempMap);
			setSavingGoals(response);
		};
		initialize();
	}, []);

	const editSavingGoal = async (event: React.MouseEvent<HTMLButtonElement>) => {
		event.preventDefault();
		const item = savingGoals.filter(
			(i) => i.savingGoalId === parseInt(event.currentTarget.value),
		);
		console.log(item[0]);
		navigate("/goals/" + item[0].savingGoalId);
	};

	const updateSpendingGoals = async (
		e: React.MouseEvent<HTMLButtonElement>,
	) => {
		e.preventDefault();
	};

	return (
		<Container>
			<Row className="m-4">
				<ul className="list-group">
					{Array.from(savingGoalsMap.entries()).map(
						([savingGoal, spendingGoals]) => (
							<li
								key={savingGoal.savingGoalId}
								className="list-group-item  list-group-item-primary"
							>
								<Container>
									<Row>
										<Col md={5}>
											<Button
												className="btn btn-warning"
												value={savingGoal.savingGoalId}
												onClick={editSavingGoal}
											>
												Edit
											</Button>{" "}
											<Button
												className="btn btn-danger"
												value={savingGoal.savingGoalId}
												onClick={updateSpendingGoals}
											>
												Update Spending
											</Button>{" "}
										</Col>
										<Col md={2} />

										<Col md={5}>
											SAVE : {savingGoal.savingGoalMoney} From:{" "}
											{savingGoal.startFrom}
											{"  "}
											To: {savingGoal.finishTo}
										</Col>
									</Row>
								</Container>
								{spendingGoals.map((sp) => (
									<Container className="mt-4">
										<Row>
											<Col md={6}>
												<ul className="list-group">
													<li
														className="list-group-item list-group-item-success"
														key={sp.spendingGoalId}
													>
														SPEND: {sp.spendingMoney} @{sp.spendingDay}
													</li>
												</ul>
											</Col>
											<Col md={6}>
												<ul className="list-group">
													<li
														className="list-group-item list-group-item-danger"
														key={sp.spendingGoalId}
													>
														Spent: {sp.daySpented}
													</li>
												</ul>
											</Col>
										</Row>
									</Container>
								))}
							</li>
						),
					)}
				</ul>
			</Row>
		</Container>
	);
};
