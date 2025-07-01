import { Button, Col, Container, Row } from "react-bootstrap";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from 'react';
import axios from "axios";
import { Income } from "../interfaces/IncomeInterfaces";
export function Dashboard(props: { username?: string }) {

	const navigate = useNavigate();
	const [cookies, setCookies] = useCookies(['jsonToken']);
	const [transactions, setTransactions] = useState<Income[]>([]);
	console.log(cookies);
	const MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'Octomber', 'November', 'December']


	const balance = {
		current: 4324.49
	};

	const handleLogout = () => {
		setCookies('jsonToken', null);
		window.location.reload();
		navigate('/');
	};

	const getTransactions = async () => {

		const response = await axios.get<Income[]>('http://localhost:8080/api/v1/transactions', {
			headers: {
				Authorization: 'Bearer ' + cookies.jsonToken
			}
		});
		setTransactions(response.data);

	};

	useEffect(
		() => {
			getTransactions();
		}, []
	);

	return (
		<Container>
			{
				transactions.map(
					(t) => {
						return (
							<h2> Money: {t.money} </h2>
						)
					}
				)

			}
		</Container >
	);
}

