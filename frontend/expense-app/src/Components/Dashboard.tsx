import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { expenseDTO, User } from "../interfaces/ExpenseDTO";
import { fetchExpenses } from "../Utilities/ApiClient";
import { ExpenseAdd } from "./ExpenseAdd";

export const Dashboard = (props: { user: User | null }) => {
	const [expenses, setExpenses] = useState<expenseDTO[]>([]);
	const [totalExpenses, setTotalExpenses] = useState<number>(0);
	const [expenseMode, setExpenseMode] = useState<boolean>(false);

	const { id } = useParams();
	const initialize = async function() {
		const e = await fetchExpenses();
		let sum = 0;
		for (let i = 0; i < e.length; i++) {
			sum += e[i].money;
		}
		setTotalExpenses(sum);
		setExpenses(e);
	}

	const handleAdd = function() {
		setExpenseMode(true);
	}

	useEffect(() => {
		initialize();
	}, []);
	return (
		<Container>
			<Row>
				<h1> Wallet Chosen: {id} </h1>
			</Row>
			<Row>
				<Col md='6' className="text-center">
					<h2 className="text-danger"> Expenses </h2>
					<h4> Total Expenses: {totalExpenses} </h4>
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
					<Button variant="warning" onClick={handleAdd}> Add Expense </Button>
					{expenseMode ? <ExpenseAdd /> : <br />}
				</Col>
				<Col md='6' className="text-center">
					<h2 className="text-success"> Wallet </h2>
					<h4> Pending: $100 </h4>
					<h4> Total Income: $400</h4>
					<Button variant="success"> Add Income </Button>
					{expenseMode ? <ExpenseAdd /> : <br />}
				</Col>
			</Row>
			<Row>
			</Row>

		</Container >
	)
}
