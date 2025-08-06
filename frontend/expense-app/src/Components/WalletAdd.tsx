import { Form, Button, Col, FormControl, FormGroup, FormLabel, Row } from "react-bootstrap";
import { Wallet } from "../interfaces/Wallet";
import { createWallet } from "../Utilities/ApiClient";

export const WalletAdd = () => {
	const handleSumbit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		const target = e.target as typeof e.target & {
			walletName: { value: string },
		}
		console.log(target.walletName.value);
		const wallet: Wallet = {
			walletId: null,
			walletName: target.walletName.value
		}
		const response = await createWallet(wallet);
		console.log(response);
		window.location.reload();
	}

	const handleCancel = () => { window.location.reload(); }

	return (
		<Form onSubmit={handleSumbit} className="mt-2 text-center">
			<FormGroup as={Row}>
				<FormLabel htmlFor="walletName" column md='6' className="text-center"> Name: </FormLabel>
				<Col md='6'>
					<FormControl type="text" id="walletName" />
				</Col>
			</FormGroup>
			<Button className="mt-2" type="submit">Save </Button>
			<Button className="mt-2" variant="danger" onClick={handleCancel}>Cancel </Button>
		</Form>
	)
}
