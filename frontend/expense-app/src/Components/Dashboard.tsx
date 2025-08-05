import { useEffect, useState } from "react";
import {
	Button,
	Col,
	Container,
	Modal,
	ModalBody,
	ModalHeader,
	Row,
} from "react-bootstrap";
import { useParams } from "react-router-dom";
import type { expenseDTO, IncomeDTO } from "../interfaces/ExpenseDTO";
import type { SingleWallet } from "../interfaces/SingleWallet";
import {
	fetchExpenses,
	fetchIncomes,
	fetchSingleWallet,
} from "../Utilities/ApiClient";
import { ExpenseAdd } from "./ExpenseAdd";
import { ExpenseList } from "./ExpenseList";
import { IncomeAdd } from "./IncomeAdd";

export const Dashboard = () => {
	const [walletId, setWalletId] = useState<number>(0);
	const [wallet, setWalet] = useState<SingleWallet>();
	const [expenses, setExpenses] = useState<expenseDTO[]>([]);
	const [incomes, setIncomes] = useState<IncomeDTO[]>([]);
	const [expenseMode, setExpenseMode] = useState<boolean>(false);
	const [incomeMode, setIncomeMode] = useState<boolean>(false);
	const [transactionModalShow, setTransactionModalShow] =
		useState<boolean>(false);
	const { id } = useParams();

	useEffect(() => {
		console.log("testing the use effect");
	}, []);

	const initialize = async (walletId: number) => {
		setWalletId(walletId);
		const exp = await fetchExpenses(walletId);
		setExpenses(exp);
		const income = await fetchIncomes(walletId);
		setIncomes(income);
		const wl = await fetchSingleWallet(walletId);
		setWalet(wl);
	};

	const handleAdd = () => {
		setExpenseMode(true);
	};

	const handleIncomeAdd = () => {
		setIncomeMode(true);
	};

	useEffect(() => {
		console.log(id);
		if (id !== undefined) {
			initialize(parseInt(id));
		}
	}, []);

	const handleModalClose = () => {
		setTransactionModalShow(false);
	};

	return (
		<Container>
			<Row className="text-center">
				<h1>
					{" "}
					Wallet Chosen: {walletId}-{wallet?.walletName}{" "}
				</h1>
				<h2> Total Remaining: {wallet?.totalPending} </h2>
				<h2> Testing the whole damn thing </h2>
			</Row>
			<Row>
				<Col md="3" />
				<Col md="6" className="text-center">
					<Button className="btn btn-secondary p-3 rounded-circle"> + </Button>
					<Modal
						id="add-transaction-modal"
						show={transactionModalShow}
						onHide={handleModalClose}
						backdrop="static"
						keyboard={false}
					>
						<ModalHeader>
							<span> Choose kind of Transaction:</span>
						</ModalHeader>
						<ModalBody></ModalBody>
					</Modal>
				</Col>
				<Col md="3" />
			</Row>
			<Row>
				<Col md="6" className="text-center">
					<ExpenseList expenses={expenses} wallet={wallet} />
					<Button variant="warning" onClick={handleAdd}>
						{" "}
						Add Expense{" "}
					</Button>
					{expenseMode ? <ExpenseAdd walletId={walletId} /> : <br />}
				</Col>
				<Col md="6" className="text-center">
					<h2 className="text-success"> Incomes </h2>
					<h3 className="text-success">
						{" "}
						Total Incomes: {wallet?.totalIncomes}{" "}
					</h3>
					<ul className="list-group">
						{incomes.map((inc) => {
							return (
								<li className="list-group-item" key={inc.incomeId}>
									{" "}
									Date: {inc.dateCreated}: {inc.money}{" "}
								</li>
							);
						})}
					</ul>
					<Button variant="success" onClick={handleIncomeAdd}>
						{" "}
						Add Income{" "}
					</Button>
					{incomeMode ? <IncomeAdd walletId={walletId} /> : <br />}
				</Col>
			</Row>
			<Row></Row>
		</Container>
	);
};
