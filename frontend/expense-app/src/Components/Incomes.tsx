import { Container } from "react-bootstrap";
import { IncomeAdd } from "./IncomeAdd";
import { IncomeList } from "./IncomeList";

export const Incomes = () => {
	return (
		<Container>
			<h2> Incomes Page </h2>
			<IncomeList />
			<IncomeAdd />
		</Container>
	);
};
