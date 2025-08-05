export interface LoginInput {
	username: string;
	password: string;
}

export interface LoginResponse {
	jwtToken: string | null;
	expiration: string | null;
	error: string | null;
}
