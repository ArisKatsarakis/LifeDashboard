import { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import type { IncomeDTO } from "../interfaces/ExpenseDTO";
import { fetcIncomes } from "../Utilities/IncomesClient";

export const IncomeList = () => {
	const [incomes, setIncomes] = useState<IncomeDTO[]>([]);

	useEffect(() => {
		const initialize = async () => {
			const data = await fetcIncomes();
			setIncomes(data);
		};
		initialize();
	}, []);
	return (
		<Container>
			<ul className="list-group">
				<h2 className="text-succes"> Incomes </h2>
				<h3> Total Incomes: </h3>
				{incomes.map((i) => {
					return (
						<li key={i.incomeId} className="list-group-item">
							Money: {i.money}, Date: {i.dateCreated} , Category:{" "}
							{i.dateCreated}
						</li>
					);
				})}
			</ul>
		</Container>
	);
};
