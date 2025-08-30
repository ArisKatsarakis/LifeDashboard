import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import type { ExpenseDTO } from "../interfaces/ExpenseDTO";
import { fetchExpenses, fetchExpensesCount } from "../Utilities/ExpensesClient";
import { ExpenseAdd } from "./ExpenseAdd";

export const ExpenseList = () => {
	const [expenses, setExpenses] = useState<ExpenseDTO[]>([]);
	const [pageCount, setPageCount] = useState<number>(0);
	const [page, setPage] = useState<number>(1);
	const [limit, setLimit] = useState<number>(5);
	const [results, setResults] = useState<number>(0);

	useEffect(() => {
		const initialize = async () => {
			const count = await fetchExpensesCount();
			setResults(count);
			setPageCount(Math.round(count / limit));
			const data = await fetchExpenses(page, limit);
			setExpenses(data);
		};
		initialize();
		const port = process.env.REACT_APP_BACKEND;
		console.log(port);
	}, [page]);

	return (
		<div style={{ textAlign: "center" }} className="container">
			<ul className="list-group">
				<Row style={{ marginBottom: "1rem" }}>
					<Col>
						<h2 className="text-danger"> Expenses: </h2>
						<span> Page Count: {pageCount} </span>
					</Col>
					<Col>
						<ExpenseAdd />
					</Col>
				</Row>
				{expenses.map((e) => {
					return (
						<li key={e.expenseId} className="list-group-item">
							Name : {e?.name}, Money: {e.money}, Date: {e.dateCreated} ,
							Category: {e.category}
						</li>
					);
				})}
			</ul>
			<Container>
				<Row>
					<Col>
						<Button
							variant="outline-primary"
							onClick={() => {
								if (page > 1) {
									setPage(page - 1);
								}
							}}
							size={"sm"}
						>
							previous{" "}
						</Button>
						<span> Page: {page} </span>
						<Button
							variant="outline-primary"
							onClick={() => {
								if (page < pageCount) {
									setPage(page + 1);
								}
							}}
							size={"sm"}
						>
							next{" "}
						</Button>
					</Col>
					<Col md="6" />
					<Col>Results: {results}</Col>
				</Row>
			</Container>
		</div>
	);
};
