import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import { Dashboard } from "./Components/Dashboard";
import { Header } from "./Components/Header";
import { Login } from "./Components/Login";
import { Register } from "./Components/Register";
import {
	isLoggedIn,
	ProtectedRoute,
	PublicRoute,
} from "./Components/RoutePublicProtected";
import { WalletDisplay } from "./Components/Wallets";

function App() {
	return (
		<BrowserRouter>
			<Header />
			<Routes>
				<Route
					path="/"
					element={
						<Navigate to={isLoggedIn() === true ? "/wallets" : "/login"} />
					}
				/>
				<Route
					path="/register"
					element={
						<PublicRoute>
							<Register />
						</PublicRoute>
					}
				/>
				<Route
					path="/login"
					element={
						<PublicRoute>
							<Login />
						</PublicRoute>
					}
				/>
				:
				<Route
					path="/wallets"
					element={
						<ProtectedRoute>
							<WalletDisplay />
						</ProtectedRoute>
					}
				/>
				<Route
					path="/wallet/:id"
					element={
						<ProtectedRoute>
							<Dashboard />
						</ProtectedRoute>
					}
				/>
			</Routes>
		</BrowserRouter>
	);
}

export default App;
