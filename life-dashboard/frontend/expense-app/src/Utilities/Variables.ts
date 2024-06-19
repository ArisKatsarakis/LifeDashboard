const backendLink = 'http://localhost:8080';

const apiLinks = {
  authenticateLink: `${backendLink}/auth/login`,
  expensesLink: `${backendLink}/api/v1/expenses`,
  expenseTypeLink: `${backendLink}/api/v1/expense-types`
}

export { apiLinks }
