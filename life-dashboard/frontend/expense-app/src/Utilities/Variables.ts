const homeLink = 'http://localhost:3000';

const variables = {
  fetchExpensesURL: 'http://localhost:8080/api/v1/expenses',
  fetchIncomesURL: 'http://localhost:8080/api/v1/incomes',
  fetchIncomeSourcesURL: 'http://localhost:8080/api/v1/incomes/income-sources',
  fetchIncomeDTOURL: 'http://localhost:8080/api/v1/incomes/incomeDto/',
  updateIncomeURL: 'http://localhost:8080/api/v1/incomes/incomeDto/',
  expenseTypesURL: 'http://localhost:8080/api/v1/expenses/expense-types',
  fetchLast10ExpensesURL: 'http://localhost:8080/api/v1/expenses/expenses-last-10',
  fetchLast10IncomesURL: 'http://localhost:8080/api/v1/incomes-last-10',
  fetchHistory: 'http://localhost:8080/api/v1/budget/history',
  fetchExpenseStats: 'http://localhost:8080/api/v1/expenses/stats'
}


const systemLinks = {
  incomes: `/incomes`,
}

export { variables, systemLinks };
