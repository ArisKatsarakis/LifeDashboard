import type { AxiosError } from "axios";
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
import type { IncomeDTO } from "../interfaces/ExpenseDTO";
import { addIncome, fetchIncomeCategories } from "../Utilities/IncomesClient";

export const IncomeAdd = () => {
	const [incomeCategories, setIncomeCategories] = useState<string[]>([]);
	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		const target = e.target as typeof e.target & {
			money: { value: number };
			dateCreated: { value: string };
			category: { value: string };
		};

		const payload: IncomeDTO = {
			incomeId: null,
			money: target.money.value,
			dateCreated: target.dateCreated.value,
			category: target.category.value,
		};
		console.log(payload);

		try {
			const response = await addIncome(payload);
			console.log(response);
		} catch (e) {
			const error = e as AxiosError;
			console.log(error.message);
		}
	};

	const handleCancel = () => {
		window.location.reload();
	};

	useEffect(() => {
		const initisialize = async () => {
			const response = await fetchIncomeCategories();
			setIncomeCategories(response);
		};
		initisialize();
	}, []);

	return (
		<Form onSubmit={handleSubmit} className="text-center mt-4">
			<h2 className="text-success"> Adding Income </h2>
			<FormGroup as={Row}>
				<FormLabel column htmlFor="money" className="p-2 text-center" md="6">
					{" "}
					Money:{" "}
				</FormLabel>
				<Col md="6">
					<FormControl type="number" id="money" required />
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
					<FormControl type="date" id="dateCreated" required />
				</Col>
			</FormGroup>

			<FormGroup as={Row}>
				<FormLabel column htmlFor="category" className="p-2 text-center" md="6">
					{" "}
					Date:{" "}
				</FormLabel>
				<Col md="6">
					<FormSelect id="category" aria-label="Default example">
						<option value={0}> Select a Category </option>
						{incomeCategories.map((category) => {
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
