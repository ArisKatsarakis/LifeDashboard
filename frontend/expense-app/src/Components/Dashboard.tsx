import { useEffect, useState } from "react";
import { Button, Container, Row } from "react-bootstrap";
import { expenseDTO, User } from "../interfaces/ExpenseDTO";
import { fetchExpenses } from "../Utilities/ApiClient";
import { ExpenseAdd } from "./ExpenseAdd";

export const Dashboard = (props: { user: User | null }) => {
	const [expenses, setExpenses] = useState<expenseDTO[]>([]);
	const [editMode, setEditMode] = useState<boolean>(false);
	const initialize = async function() {
		const e = await fetchExpenses();
		setExpenses(e);
	}

	const handleAdd = function() {
		setEditMode(true);
	}

	useEffect(() => {
		initialize();
	}, []);
	return (
		<Container>
			<Row>
				<h2> Expenses </h2>
				{
					expenses.map((e) => {
						return (
							<div key={e.expenseId}>
								Money: {e.money},
								Date: {e.dateCreated}
							</div>
						)
					})
				}
			</Row>
			<Row>
				<Button variant="warning" onClick={handleAdd}> Add </Button>
				{editMode ? <ExpenseAdd /> : <hr />}
			</Row>

		</Container>
	)
}
