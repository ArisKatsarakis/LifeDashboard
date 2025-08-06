export interface RegisterInput {
	username: string;
	email: string;
	password: string;
}

export interface RegisterResponse {
	message: string;
	status: string;
}
