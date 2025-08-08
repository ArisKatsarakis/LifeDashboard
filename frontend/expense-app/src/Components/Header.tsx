import { useEffect, useState } from "react";
import {
	Button,
	Col,
	Container,
	Modal,
	ModalBody,
	ModalFooter,
	ModalHeader,
	Row,
} from "react-bootstrap";
import { useLocation, useNavigate } from "react-router-dom";
import type { UserFinanceDTO } from "../interfaces/UserFinance";
import { fetchUserFinance } from "../Utilities/UserClient";
import { isLoggedIn } from "./RoutePublicProtected";

const Header = () => {
	const [userFinance, setUserFinance] = useState<UserFinanceDTO>();
	const badges = [
		{
			id: 1,
			name: "Expenses",
			search: "expenses",
			link: "/expenses",
		},
		{
			id: 2,
			name: "Incomes",
			search: "incomes",
			link: "/incomes",
		},
		{
			id: 3,
			name: "Stats",
			search: "stats",
			link: "/stats",
		},
	];

	const navigate = useNavigate();
	const [show, setShow] = useState<boolean>(false);
	const { pathname } = useLocation();
	const handleCloseModal = () => {
		setShow(false);
	};

	const handleLogout = () => {
		window.localStorage.clear();
		setShow(false);
		navigate("/");
	};

	useEffect(() => {
		const initialize = async () => {
			if (isLoggedIn() === true) {
				const data = await fetchUserFinance();
				setUserFinance(data);
			}
		};
		initialize();
	}, []);

	return (
		<Container fluid>
			<Row style={{ borderBottom: "1px solid black" }}>
				<Col md="2" style={{}}>
					<a
						href="/"
						className="link-secondary"
						style={{ textDecoration: "none" }}
					>
						{" "}
						<h2> Expense App </h2>{" "}
					</a>
				</Col>
				<Col md="8" className="text-center">
					{badges.map((b) => {
						return (
							<span
								className={
									pathname.includes(b.search) === true
										? "badge bg-success m-1"
										: "badge bg-secondary m-1"
								}
								key={b.id}
							>
								<h4>
									{" "}
									<a
										href={b.link}
										style={{ textDecoration: "none", color: "white" }}
									>
										{" "}
										{b.name}{" "}
									</a>{" "}
								</h4>
							</span>
						);
					})}
				</Col>
				<Col md="2" style={{ textAlign: "right" }} className="mt-2">
					{isLoggedIn() === true ? (
						<div>
							<span>
								{" "}
								{window.localStorage.getItem("username") !== null
									? window.localStorage.getItem("username")
									: null}{" "}
							</span>

							<Button
								variant="danger"
								onClick={() => {
									setShow(true);
								}}
							>
								{" "}
								Logout{" "}
							</Button>
							<Modal
								id="logoutModal"
								onHide={handleCloseModal}
								show={show}
								closeButton
							>
								<ModalHeader>
									<h2> Logout </h2>
								</ModalHeader>
								<ModalBody>
									<h4> Are you sure about logging out? </h4>
								</ModalBody>
								<ModalFooter>
									<Button className="btn btn-danger" onClick={handleLogout}>
										{" "}
										Logout{" "}
									</Button>
									<Button
										className="btn btn-secondary"
										onClick={handleCloseModal}
									>
										{" "}
										Cancel{" "}
									</Button>
								</ModalFooter>
							</Modal>
						</div>
					) : (
						<div>
							<Button variant="success" href="/login">
								Login{" "}
							</Button>
							<Button variant="danger" href="/register">
								{" "}
								Register{" "}
							</Button>
						</div>
					)}
				</Col>
			</Row>
			{isLoggedIn() === false ? (
				<Row> </Row>
			) : (
				<Row className="text-center" style={{ marginTop: "1rem" }}>
					<Col md="4" className="text-danger ">
						<span className="badge rounded-pill text-bg-danger text-center">
							{" "}
							<h4 className=""> Spent:{userFinance?.totalMoneySpent} </h4>{" "}
						</span>
					</Col>
					<Col md="4" className="text-warning">
						<span className="badge rounded-pill text-bg-warning align-middle p-2">
							{" "}
							<h4> Pending:{userFinance?.totalMoneyPending} </h4>{" "}
						</span>
					</Col>
					<Col md="4" className="text-success">
						<span className="badge rounded-pill text-bg-success align-middle p-2">
							{" "}
							<h4> Received:{userFinance?.totalMoneyReceived} </h4>{" "}
						</span>
					</Col>
				</Row>
			)}
		</Container>
	);
};
export { Header };
