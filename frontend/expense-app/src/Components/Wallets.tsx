import { useEffect, useState } from "react"
import { Button, Col, Container, Row } from "react-bootstrap"
import { Wallet } from "../interfaces/Wallet"
import { fetchWallets } from "../Utilities/ApiClient";
import { WalletAdd } from "./WalletAdd";

export const WalletDisplay = () => {
	const [wallets, setWallets] = useState<Wallet[]>([]);
	const [editMode, setEditMode] = useState<boolean>(false);
	const initialize = async () => {
		const data = await fetchWallets();
		setWallets(data);
	}


	/** TODO  fix the dashboard so you add expenses to respective wallets */
	/** TODO  add Incomes in backend and frontend */
	const handleToglle = async () => {
		setEditMode(true);
	}

	useEffect(() => {
		initialize();
	}, [])
	return (
		<Container>
			<Row>
				<h2> Choose Wallet </h2>
				{
					wallets.length == 0 ? <h2> No Wallets added </h2> : <hr />
				}

				{
					wallets.map((w) => {
						return (
							<Col md={4} className="mt-2 mb-2">
								<span key={w.walletId} className="bg-success p-2 border-2">
									<a href={`/wallet/${w.walletId}`} className="link-light"> Name:{w.walletName} Pending: {w.totalPending} </a>
								</span>
							</Col>
						)
					})
				}
			</Row>
			<Button variant="success" onClick={handleToglle}> Add Wallet </Button>
			{editMode ? <WalletAdd /> : <hr />}
		</Container>
	)
}
