const backendLink = 'http://localhost:8080';

const apiLinks = {
  authenticateLink: `${backendLink}/auth/login`,
  expensesLink: `${backendLink}/api/v1/expenses`,
  expenseTypeLink: `${backendLink}/api/v1/expense-types`,
  incomeLink: `${backendLink}/api/v1/incomes`,
  montlyIncomeLink: `${backendLink}/api/v1/incomes/month/`,
  registrationLink: `${backendLink}/api/v1/registration`
}

export { apiLinks }
