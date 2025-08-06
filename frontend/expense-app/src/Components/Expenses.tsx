import { Container } from "react-bootstrap";
import { ExpenseAdd } from "./ExpenseAdd";
import { ExpenseList } from "./ExpenseList";

export const Expenses = () => {
	return (
		<Container>
			<h2> Expenses Page </h2>
			<ExpenseList />
			<ExpenseAdd />
		</Container>
	);
};
