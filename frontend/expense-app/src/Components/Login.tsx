import { useState } from "react";
import {
	Button,
	Container,
	Form,
	FormGroup,
	FormLabel,
	Row,
	Spinner,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import type { LoginInput } from "../interfaces/LoginInput";
import { login } from "../Utilities/LoginClient";

export const Login = () => {
	const [errorMessage, setErrorMessage] = useState<string>("");
	const [loading, setLoading] = useState<boolean>(false);
	const navigate = useNavigate();

	const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
		setLoading(true);
		event.preventDefault();
		setErrorMessage("");
		const target = event.target as typeof event.target & {
			username: { value: string };
			password: { value: string };
		};

		const payload: LoginInput = {
			username: target.username.value,
			password: target.password.value,
		};

		try {
			const response = await login(payload);
			window.localStorage.setItem(
				"jwt",
				response.jwtToken !== null ? response.jwtToken : "",
			);
			window.localStorage.setItem(
				"username",
				response.username !== null ? response.username : "",
			);
			setTimeout(() => {
				navigate("/incomes");
				window.location.reload();
			}, 3000);
		} catch (error: any) {
			setErrorMessage("Bad Credentials");
			console.log(error);
		}
	};

	return (
		<Container className="text-center">
			{!loading ? (
				<Form onSubmit={handleSubmit}>
					<FormGroup className="row">
						<FormLabel htmlFor="username" className="form-label-col">
							{" "}
							Username or Email{" "}
						</FormLabel>
						<input type={"text"} id="username" className="form-control-col" />
					</FormGroup>
					<FormGroup className="row">
						<FormLabel htmlFor="password" className="form-label-col">
							{" "}
							Password{" "}
						</FormLabel>
						<input
							type={"password"}
							id="password"
							className="form-control-col"
						/>
					</FormGroup>
					<Button type="submit"> Login </Button>
				</Form>
			) : (
				<Spinner
					animation="border"
					role="status"
					style={{ width: "300px", height: "300px" }}
				>
					<span className="visually-hidden">Loading...</span>
				</Spinner>
			)}

			{errorMessage !== "" ? (
				<Row>
					{<h2 className="text-danger mt-2"> Error: {errorMessage} </h2>}
				</Row>
			) : (
				<Row></Row>
			)}
		</Container>
	);
};
