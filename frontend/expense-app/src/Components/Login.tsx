import { Button, Container, Form, FormGroup, FormLabel } from "react-bootstrap";
import type { LoginInput } from "../interfaces/LoginInput";

export const Login = () => {
	const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
		event.preventDefault();
		const target = event.target as typeof event.target & {
			username: { value: string };
			password: { value: string };
		};

		const payload: LoginInput = {
			username: target.username.value,
			password: target.password.value,
		};

		console.log(payload);
	};

	return (
		<Container className="text-center">
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
					<input type={"password"} id="password" className="form-control-col" />
				</FormGroup>
				<Button type="submit"> Login </Button>
			</Form>
		</Container>
	);
};
