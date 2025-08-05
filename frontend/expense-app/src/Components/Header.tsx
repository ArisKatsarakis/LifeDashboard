import { useState } from "react";
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
import { isLoggedIn } from "./RoutePublicProtected";

const Header = () => {
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
	return (
		<Container style={{ borderBottom: "1px solid black" }} fluid>
			<Row>
				<Col md="2" style={{}}>
					<a href="/" className="link-secondary">
						{" "}
						<h2> Expense App </h2>{" "}
					</a>
				</Col>
				<Col md="8" className="text-center">
					<span
						className={
							pathname.includes("wallet") === true
								? "badge bg-success m-1"
								: "badge bg-secondary m-1"
						}
					>
						<h4>
							{" "}
							<a
								href="/wallets"
								style={{ textDecoration: "none", color: "white" }}
							>
								{" "}
								Wallets{" "}
							</a>{" "}
						</h4>
					</span>

					<span
						className={
							pathname.includes("stats") === true
								? "badge bg-success m-1"
								: "badge bg-secondary m-1"
						}
					>
						<h4>
							{" "}
							<a
								href="/stats"
								style={{ textDecoration: "none", color: "white" }}
							>
								{" "}
								Stats{" "}
							</a>{" "}
						</h4>
					</span>
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
		</Container>
	);
};
export { Header };
