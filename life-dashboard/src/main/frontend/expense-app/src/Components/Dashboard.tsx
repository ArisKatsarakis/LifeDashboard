import { useEffect, useState } from "react";
import { sampleExpense, sampleIncome, samples } from "../Utilities/Samples";
import { variables } from "../Utilities/Variables";
import axios from "axios";
import { Button, Row, Col, } from "react-bootstrap";
import styles from './Dashboard.module.css'
import { ExpenseCard } from "./ExpenseCard";
import { Expense, ExpensesPromise } from "../Interfaces/ExpenseInterfaces";
import { IncomeDTO, IncomesPromise } from "../Interfaces/IncomeInterfaces";
import { IncomeCard } from "./IncomeCard";
import { TotalBudget } from "./TotalBudget";
export const Dashboard = () => {

    const [expenses, setExpenses] = useState<Expense[]>(samples.SAMPLE_EXPENSES);
    const [incomes, setIncomes] = useState<IncomeDTO[]>(samples.SAMPLE_INCOMES);

    const getLast10ExpensesfromApi = async () => {
        const expenses: ExpensesPromise = await axios.get(variables.fetchLast10ExpensesURL);
        setExpenses(expenses.data);
    }

    const getLast10IncomesFromApi = async () => {
        const incomes: IncomesPromise = await axios.get(variables.fetchLast10IncomesURL)
        setIncomes(incomes.data);
    }
    useEffect(
        () => {
            getLast10ExpensesfromApi();
            getLast10IncomesFromApi();
        }, []
    )
    return (
        <>
            <div className='container'>
                <Row>
                    <h2 className='text-center'>
                        Dashboard
                    </h2>
                </Row>
                <Row>
                    <ExpenseCard
                        key={sampleExpense.expenseId}
                        expenseId={sampleExpense.expenseId}
                        expenseName={sampleExpense.expenseName}
                        expenseType={sampleExpense.expenseType}
                        moneySpent={sampleExpense.moneySpent}
                        dateCreated={
                            sampleExpense.dateCreated
                        }
                    />
                    <ExpenseCard
                        key={sampleExpense.expenseId}
                        expenseId={sampleExpense.expenseId}
                        expenseName={sampleExpense.expenseName}
                        expenseType={sampleExpense.expenseType}
                        moneySpent={sampleExpense.moneySpent}
                        dateCreated={
                            sampleExpense.dateCreated
                        }
                    />
                    <IncomeCard
                        key={sampleIncome.incomeId}
                        incomeId={sampleIncome.incomeId}
                        incomeSourceName={sampleIncome.incomeSourceName}
                        incomeSourceId={sampleIncome.incomeSourceId}
                        moneyReceived={sampleIncome.moneyReceived}
                        dateCreated={sampleIncome.dateCreated}
                        description={sampleIncome.description}

                    />
                </Row>
                <Row className='text-center'>
                    <Col className="expenses">
                        {expenses.map(
                            expense => {
                                return (
                                    <ExpenseCard
                                        key={expense.expenseId}
                                        expenseId={expense.expenseId}
                                        expenseName={expense.expenseName}
                                        expenseType={expense.expenseType}
                                        moneySpent={expense.moneySpent}
                                        dateCreated={
                                            expense.dateCreated
                                        }
                                    />
                                )
                            }
                        )}
                        <Button variant='danger' > Add Expense </Button>
                    </Col>
                    <Col className="incomes">
                        {incomes.map(
                            income => {
                                return (
                                    <IncomeCard

                                        key={income.incomeId}
                                        incomeId={income.incomeId}
                                        incomeSourceName={income.incomeSourceName}
                                        incomeSourceId={income.incomeSourceId}
                                        moneyReceived={income.moneyReceived}
                                        dateCreated={income.dateCreated}
                                        description={income.description}

                                    />
                                )
                            }
                        )}

                        <Button variant='success'>Add Income</Button>
                    </Col>
                </Row>
                <Row>
                    <TotalBudget />
                </Row>
            </div>
        </>
    );
};