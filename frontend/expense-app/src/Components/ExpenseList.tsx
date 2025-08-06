import type { expenseDTO } from "../interfaces/ExpenseDTO";
import type { SingleWallet } from "../interfaces/SingleWallet";

export const ExpenseList = (props: {
	expenses: expenseDTO[];
	wallet: SingleWallet | undefined;
}) => {
	return (
		<div>
			<ul className="list-group">
				<h2 className="text-danger"> Expenses </h2>
				<h3> Total Expenses: {props.wallet?.totalExpenses} </h3>
				{props.expenses.map((e) => {
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
