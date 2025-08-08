import { useEffect, useState } from "react";
import { Row } from "react-bootstrap";
import type { SavingGoalDTO } from "../interfaces/SavingGoal";
import {
	fetchGoalCalculations,
	fetchSavingGoals,
} from "../Utilities/SavingGoalClient";

export const SavingGoalList = () => {
	const [savingGoals, setSavingGoals] = useState<SavingGoalDTO[]>([]);

	useEffect(() => {
		const initialize = async () => {
			const response = await fetchSavingGoals();
			response.forEach(async (r) => {
				const resp = await fetchGoalCalculations(r.savingGoalId);
				console.log(resp);
			});
			setSavingGoals(response);
		};
		initialize();
	}, []);
	return (
		<Row>
			<ul className="list-group">
				{savingGoals.map((s) => {
					return (
						<li className="list-group-item" key={s.savingGoalId}>
							{" "}
							Goal: {s.savingGoalMoney} From: {s.startFrom}
							{"  "}
							To: {s.finishTo}
						</li>
					);
				})}
			</ul>
		</Row>
	);
};
