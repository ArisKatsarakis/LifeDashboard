import { Navigate } from "react-router-dom";

interface ProtectedRouteProps {
	children: JSX.Element;
}

export const isLoggedIn = (): boolean => {
	const jwt = window.localStorage.getItem("jwt");
	if (jwt === undefined || jwt === null) {
		return false;
	}
	return true;
};
export const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children }) => {
	return isLoggedIn() === true ? children : <Navigate to={"/login"} />;
};
export const PublicRoute: React.FC<ProtectedRouteProps> = ({ children }) => {
	return isLoggedIn() === false ? children : <Navigate to={"/login"} />;
};
