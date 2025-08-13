import "bootstrap/dist/css/bootstrap.css";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import { Expenses } from "./Components/Expenses";
import { Header } from "./Components/Header";
import { Incomes } from "./Components/Incomes";
import { Login } from "./Components/Login";
import { Register } from "./Components/Register";
import {
	isLoggedIn,
	ProtectedRoute,
	PublicRoute,
} from "./Components/RoutePublicProtected";
import { SavingGoalAdd } from "./Components/SavingGoalAdd";
import { SavingGoals } from "./Components/SavingGoals";
import { Statistics } from "./Components/Statistics";

function App() {
	return (
		<BrowserRouter>
			<Header />
			<Routes>
				<Route
					path="/"
					element={
						<Navigate to={isLoggedIn() === true ? "/incomes" : "/login"} />
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
				<Route
					path="/incomes"
					element={
						<ProtectedRoute>
							<Incomes />
						</ProtectedRoute>
					}
				/>
				<Route
					path="/stats"
					element={
						<ProtectedRoute>
							<Statistics />
						</ProtectedRoute>
					}
				/>
				<Route
					path="/expenses"
					element={
						<ProtectedRoute>
							<Expenses />
						</ProtectedRoute>
					}
				/>
				<Route
					path="/goals"
					element={
						<ProtectedRoute>
							<SavingGoals />
						</ProtectedRoute>
					}
				/>
				<Route
					path="/goals/:goalId"
					element={
						<ProtectedRoute>
							<SavingGoalAdd />
						</ProtectedRoute>
					}
				/>
			</Routes>
		</BrowserRouter>
	);
}

export default App;
