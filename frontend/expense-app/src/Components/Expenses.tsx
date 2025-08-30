import { Container } from "react-bootstrap";
import { ExpenseList } from "./ExpenseList";

export const Expenses = () => {
	return (
		<Container>
			<h2> Expenses Page </h2>
			<ExpenseList />
		</Container>
	);
};
