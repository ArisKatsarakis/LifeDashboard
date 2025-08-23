import { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { cursorTo } from "readline";
import type { ExpenseDTO, IncomeDTO } from "../interfaces/ExpenseDTO";
import {
	fetchExpenseCategories,
	fetchExpensesByCategories,
} from "../Utilities/ExpensesClient";
import {
	fetchIncomeCategories,
	fetchIncomesByCategory,
} from "../Utilities/IncomesClient";
export const Statistics = () => {
	const [expensesCatMap, setExpensesCatMap] = useState<
		Map<string, ExpenseDTO[]>
	>(new Map<string, ExpenseDTO[]>());
	const [incomesMap, setIncomesMap] = useState<Map<string, IncomeDTO[]>>(
		new Map<string, IncomeDTO[]>(),
	);
	useEffect(() => {
		const initialize = async () => {
			const cats = await fetchExpenseCategories();
			const expMap = new Map<string, ExpenseDTO[]>();
			for (const cat in cats) {
				const expenses = await fetchExpensesByCategories(cats[cat]);
				expMap.set(cats[cat], expenses);
			}
			console.log(expMap);
			setExpensesCatMap(expMap);
			const incomeCats = await fetchIncomeCategories();
			const incomeMap = new Map<string, IncomeDTO[]>();
			for (let i = 0; i < incomeCats.length; i++) {
				const incomes = await fetchIncomesByCategory(incomeCats[i]);
				incomeMap.set(incomeCats[i], incomes);
			}
			setIncomesMap(incomeMap);
		};
		initialize();
	}, []);

	return (
		<Container>
			{/*Expenses Statistics*/}
			<Row className="mt-4">
				<Col md="12" className="text-center">
					<h2 className="text-bg-danger"> Expenses </h2>
				</Col>{" "}
				{Array.from(expensesCatMap.entries()).map(([category, expenses]) => {
					return (
						<Col md="3" key={category} className="text-center">
							<h4 className="text-bg-primary">
								{category} : ${" "}
								{expenses
									.map((e) => e.money)
									.reduce((sum, curValue) => {
										return sum + curValue;
									}, 0)}
							</h4>
							<ul className="list-group">
								{expenses.map((expense) => (
									<li
										key={expense.expenseId}
										className="list-group-item list-group-item-primary"
									>
										{expense.name}: ${expense.money}
									</li>
								))}
							</ul>
						</Col>
					);
				})}
			</Row>
			{/*Incomes Statistics*/}
			<Row className="mt-4">
				<Col md="12" className="text-center">
					<h2 className="text-bg-success"> Incomes </h2>
				</Col>{" "}
				{Array.from(incomesMap.entries()).map(([category, incomes]) => (
					<Col md="3" key={category} className="text-center">
						<h4 className="text-bg-primary">
							{category} $
							{incomes
								.map((i) => i.money)
								.reduce((sum, curValue) => {
									return sum + curValue;
								}, 0)}
						</h4>
						<ul className="list-group">
							{incomes.map((income) => (
								<li
									key={income.incomeId}
									className="list-group-item list-group-item-primary"
								>
									{income.category}: ${income.money}
								</li>
							))}
						</ul>
					</Col>
				))}
			</Row>
		</Container>
	);
};
