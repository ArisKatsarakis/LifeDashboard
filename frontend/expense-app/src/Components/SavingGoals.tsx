import { Container } from "react-bootstrap";
import { SavingGoalAdd } from "./SavingGoalAdd";
import { SavingGoalList } from "./SavingGoalList";

export const SavingGoals = () => {
	return (
		<Container>
			<h2> Saving Goals: </h2>
			<SavingGoalList />
			<SavingGoalAdd />
		</Container>
	);
};
