import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import type { ExpenseDTO } from "../interfaces/ExpenseDTO";
import { fetchExpenses, fetchExpensesCount } from "../Utilities/ExpensesClient";

export const ExpenseList = () => {
	const [expenses, setExpenses] = useState<ExpenseDTO[]>([]);
	const [pageCount, setPageCount] = useState<number>(0);
	const [page, setPage] = useState<number>(1);
	const [limit, setLimit] = useState<number>(5);

	useEffect(() => {
		const initialize = async () => {
			const count = await fetchExpensesCount();
			setPageCount(Math.floor(count / limit));
			const data = await fetchExpenses(page, limit);
			setExpenses(data);
		};
		initialize();
		const port = process.env.REACT_APP_BACKEND;
		console.log(port);
	}, [page]);
	return (
		<div style={{ textAlign: "center" }}>
			<ul className="list-group">
				<h2 className="text-danger"> Expenses: </h2>
				<span> Page Count: {pageCount} </span>
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
					<Col>Results: {limit}</Col>
				</Row>
			</Container>
		</div>
	);
};
