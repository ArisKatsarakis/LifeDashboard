const homeLink = 'http://localhost:3000';

const variables = {
    fetchExpensesURL : 'http://localhost:8080/api/v1/expenses',
    fetchIncomesURL : 'http://localhost:8080/api/v1/incomes',
    fetchIncomeSourcesURL : 'http://localhost:8080/api/v1/income-sources',
    fetchIncomeDTOURL: 'http://localhost:8080/api/v1/incomeDto/',
    updateIncomeURL: 'http://localhost:8080/api/v1/incomeDto/'
}


const systemLinks = {
    incomes: `/incomes`,
}

export {variables, systemLinks};