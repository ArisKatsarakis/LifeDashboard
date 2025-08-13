import { useEffect, useState } from "react";
import type { ExpenseDTO } from "../interfaces/ExpenseDTO";
import { fetchExpenses } from "../Utilities/ExpensesClient";

export const ExpenseList = () => {
	const [expenses, setExpenses] = useState<ExpenseDTO[]>([]);
	useEffect(() => {
		const initialize = async () => {
			const data = await fetchExpenses();
			setExpenses(data);
		};
		initialize();
		const port = process.env.REACT_APP_BACKEND;
		console.log(port);
	}, []);
	return (
		<div>
			<ul className="list-group">
				<h2 className="text-danger"> Expenses: </h2>
				{expenses.map((e) => {
					return (
						<li key={e.expenseId} className="list-group-item">
							Name : {e?.name}, Money: {e.money}, Date: {e.dateCreated} ,
							Category: {e.category}
						</li>
					);
				})}
			</ul>
		</div>
	);
};
