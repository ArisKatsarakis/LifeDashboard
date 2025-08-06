import type React from "react";
import { useEffect, useState } from "react";
import {
	Button,
	Col,
	Form,
	FormControl,
	FormGroup,
	FormLabel,
	FormSelect,
	Row,
} from "react-bootstrap";
import type { ExpenseDTO } from "../interfaces/ExpenseDTO";
import {
	addExpense,
	fetchExpenseCategories,
} from "../Utilities/ExpensesClient";

export const ExpenseAdd = () => {
	const [expenseCategories, setExpenseCategories] = useState<string[]>([]);
	const handleCancel = () => {
		window.location.reload();
	};

	useEffect(() => {
		const initialize = async () => {
			const data = await fetchExpenseCategories();
			setExpenseCategories(data);
		};
		initialize();
	}, []);

	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		const target = e.target as typeof e.target & {
			money: { value: number };
			dateCreated: { value: string };
			category: { value: string };
		};
		const payload: ExpenseDTO = {
			name: "Expense Name",
			expenseId: null,
			money: target.money.value,
			dateCreated: target.dateCreated.value,
			category: target.category.value,
		};

		const response = await addExpense(payload);
		window.location.reload();
		console.log(response);
	};
	return (
		<Form onSubmit={handleSubmit} className="text-center mt-4">
			<h2 className="text-warning"> Adding Expense </h2>
			<FormGroup as={Row}>
				<FormLabel column htmlFor="name" className="p-2 text-center" md="6">
					{" "}
					Name:{" "}
				</FormLabel>
				<Col md="6">
					<FormControl type="text" id="name" />
				</Col>
			</FormGroup>
			<FormGroup as={Row}>
				<FormLabel column htmlFor="money" className="p-2 text-center" md="6">
					{" "}
					Money:{" "}
				</FormLabel>
				<Col md="6">
					<FormControl type="number" id="money" />
				</Col>
			</FormGroup>

			<FormGroup as={Row}>
				<FormLabel
					column
					htmlFor="dateCreated"
					className="p-2 text-center"
					md="6"
				>
					{" "}
					Date:{" "}
				</FormLabel>
				<Col md="6">
					<FormControl type="date" id="dateCreated" />
				</Col>
			</FormGroup>
			<FormGroup as={Row}>
				<FormLabel column htmlFor="category" className="p-2 text-center">
					ExpenseCategory
				</FormLabel>
				<Col md="6">
					<FormSelect aria-label="Default select" required={true} id="category">
						<option value={0}> Select Category </option>
						{expenseCategories.map((category) => {
							return (
								<option value={category} key={category}>
									{" "}
									{category}{" "}
								</option>
							);
						})}
					</FormSelect>
				</Col>
			</FormGroup>
			<div>
				<Button type="submit" className="col-md-2 mt-2 p-1">
					Save{" "}
				</Button>
				<Button onClick={handleCancel} className="col-md-2 mt-2 p-1">
					Cancel{" "}
				</Button>
			</div>
		</Form>
	);
};
