import { Button, Col, Form, FormControl, FormGroup, FormLabel, Row } from "react-bootstrap"
import { IncomeDTO } from "../interfaces/ExpenseDTO";
import { createIncome } from "../Utilities/ApiClient";

export const IncomeAdd = function(props: { walletId: number }) {
	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		const target = e.target as typeof e.target & {
			money: { value: number },
			dateCreated: { value: string }
		}
		const payload: IncomeDTO = {
			incomeId: null,
			money: target.money.value,
			dateCreated: target.dateCreated.value
		}
		const resp = await createIncome(payload, props.walletId);
		console.log(resp)
		window.location.reload();
	}

	const handleCancel = () => {
		window.location.reload();
	}
	return (
		<Form onSubmit={handleSubmit} className="text-center mt-4">
			<FormGroup as={Row}>
				<FormLabel column htmlFor="money" className="p-2 text-center" md='6'> Money: </FormLabel>
				<Col md='6'>
					<FormControl type="number" id="money" />
				</Col>
			</FormGroup>

			<FormGroup as={Row}>
				<FormLabel column htmlFor="dateCreated" className="p-2 text-center" md='6'> Date: </FormLabel>
				<Col md='6'>
					<FormControl type="date" id="dateCreated" />
				</Col>
			</FormGroup>
			<div>
				<Button type="submit" className="col-md-2 mt-2 p-1">Save </Button>
				<Button onClick={handleCancel} className="col-md-2 mt-2 p-1">Cancel </Button>
			</div>
		</Form>
	)
}
