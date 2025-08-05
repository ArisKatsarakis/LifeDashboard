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
import { useNavigate } from "react-router-dom";
import { isLoggedIn } from "./RoutePublicProtected";

const Header = () => {
	const navigate = useNavigate();
	const [show, setShow] = useState<boolean>(false);
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
				<Col md="6" style={{}}>
					<a href="/" className="link-secondary">
						{" "}
						<h2> Expense App </h2>{" "}
					</a>
				</Col>
				{isLoggedIn() === true ? (
					<Col md="6" style={{ textAlign: "right" }} className="mt-2">
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
					</Col>
				) : (
					<Col md="6" style={{ textAlign: "right" }} className="mt-2">
						<Button variant="success" href="/login">
							Login{" "}
						</Button>
						<Button variant="danger" href="/register">
							{" "}
							Register{" "}
						</Button>
					</Col>
				)}
			</Row>
		</Container>
	);
};
export { Header };
