import { useEffect, useState } from "react"
import { Container } from "react-bootstrap"
import { Wallet } from "../interfaces/Wallet"
import { fetchWallets } from "../Utilities/ApiClient";

export const WalletDisplay = () => {
	const [wallets, setWallets] = useState<Wallet[]>([]);
	const initialize = async () => {
		const data = await fetchWallets();
		setWallets(data);
	}


	useEffect(() => {
		initialize();
	}, [])
	return (
		<Container>
			<h2> Choose Wallet </h2>
			{
				wallets.length == 0 ? <h2> No Wallets added </h2> : <hr />
			}
			{
				wallets.map((w) => {
					return (
						<span key={w.walletId} className="bg-success p-2 border-2">
							<a href={`/wallet/${w.walletId}`} > Name:{w.walletName} Pending: {w.totalPending} </a>
						</span>
					)
				})
			}
		</Container>
	)
}
