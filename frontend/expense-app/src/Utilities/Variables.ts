const backendLink = "http://localhost:8080";

const apiLinks = {
	expensesLink: `${backendLink}/api/v1/expenses`,
	walletsLink: `${backendLink}/api/v1/wallet`,
	incomesLink: `${backendLink}/api/v1/incomes`,
};

const fetchBearer = (): string => {
	const token = window.localStorage.getItem("jwt")?.toString();
	console.log(token);
	return token !== undefined ? token : "";
};

export { apiLinks, fetchBearer };
