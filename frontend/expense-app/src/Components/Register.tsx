import type { AxiosError } from "axios";
import { useState } from "react";
import {
	Button,
	Col,
	Container,
	Form,
	FormControl,
	FormGroup,
	FormLabel,
	Row,
	Spinner,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import type { RegisterInput } from "../interfaces/Register";
import { register } from "../Utilities/LoginClient";

export const Register = () => {
	const [errorMessage, setErrorMessage] = useState<string>("");
	const [loading, setLoading] = useState<boolean>(false);
	const navigate = useNavigate();
	const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		const target = e.target as typeof e.target & {
			username: { value: string };
			email: { value: string };
			password: { value: string };
		};

		const payload: RegisterInput = {
			username: target.username.value,
			email: target.email.value,
			password: target.password.value,
		};

		console.log(payload);
		try {
			const response = await register(payload);
			console.log(response);
			setErrorMessage("Successful Registration");
			setLoading(true);
			setTimeout(() => {
				navigate("/");
			}, 3000);
		} catch (error) {
			const e = error as AxiosError;
			switch (e.status) {
				case 409:
					setErrorMessage("User Not Found");
					break;

				default:
					setErrorMessage("Registration Error");
					console.log(e.message);
					break;
			}
		}
	};

	return (
		<Container className="text-left">
			<h2 className="mb-2"> Register Page </h2>
			{loading === true ? (
				<Spinner animation="border" className="col-md-4" />
			) : (
				<Form onSubmit={handleSubmit} className="text-center m-2">
					<FormGroup as={Row}>
						<FormLabel htmlFor="username" column sm="2">
							Username:
						</FormLabel>
						<Col sm="6">
							<FormControl type="text" id="username" />
						</Col>
					</FormGroup>
					<FormGroup as={Row}>
						<FormLabel htmlFor="email" column sm="2">
							Email:
						</FormLabel>
						<Col sm="6">
							<FormControl type="email" id="email" />
						</Col>
					</FormGroup>
					<FormGroup as={Row}>
						<FormLabel htmlFor="password" column sm="2">
							Password:
						</FormLabel>
						<Col sm="6">
							<FormControl type="password" id="password" />
						</Col>
					</FormGroup>
					<Button type="submit"> Save </Button>
				</Form>
			)}
			<Row>
				<h2 className="text-danger">
					{" "}
					{errorMessage !== "" ? errorMessage : ""}{" "}
				</h2>
			</Row>
		</Container>
	);
};
