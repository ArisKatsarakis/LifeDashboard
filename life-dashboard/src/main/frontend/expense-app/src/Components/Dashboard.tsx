import { useEffect, useState } from "react";
import { sampleBudget, sampleExpense, sampleIncome, samples } from "../Utilities/Samples";
import { variables } from "../Utilities/Variables";
import axios from "axios";
import { Button, Row, Col, } from "react-bootstrap";
import styles from './Dashboard.module.css'
import { ExpenseCard } from "./ExpenseCard";
import { Expense, ExpensesPromise } from "../Interfaces/ExpenseInterfaces";
import { IncomeDTO, IncomesPromise } from "../Interfaces/IncomeInterfaces";
import { IncomeCard } from "./IncomeCard";
import { TotalBudget } from "./TotalBudget";
import { Budget, BudgetHistory, BudgetHistoryPromise, Entry } from "../Interfaces/BudgetInterfaces";
export const Dashboard = () => {

    const [entries, setEntries] = useState<Entry[]>([]);
    const [budget, setBudget] = useState<Budget>(sampleBudget)
    const getBudugetHistoryFromApi = async () => {
        const results: BudgetHistoryPromise = await axios.get(variables.fetchHistory);
        setEntries(results.data.tenLastEntries);
        setBudget(results.data.budget);
    }
    useEffect(
        () => {
            getBudugetHistoryFromApi();
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
                    <Col md='2'>
                        <span>

                        </span>
                    </Col>
                    <Col md='8'>
                        <TotalBudget
                            key={budget.budgetId}
                            lastExpenseDate={budget.lastExpenseDate}
                            dateCreated={budget.dateCreated}
                            lastIncomeDate={budget.lastIncomeDate}
                            budgetId={budget.budgetId}
                            walletMoney={budget.walletMoney}
                        />
                    </Col>

                    <Col md='2'>
                    </Col>
                </Row>
                <Row md={'12'}>
                    <Col md='6'>
                        {entries.filter(entry => entry.entryType === 'EXPENSE').map(
                            entry => {
                                return (
                                    <ExpenseCard
                                        key={entry.expenseId}
                                        expenseId={entry.expenseId}
                                        expenseName={''}
                                        expenseType={entry.entryType}
                                        moneySpent={entry.money}
                                        dateCreated={
                                            entry.dateInserted
                                        }
                                    />
                                )
                            }
                        )}
                    </Col>
                    <Col md='6'>
                        {entries.filter(entry => entry.entryType === 'INCOME').map(
                            entry => {
                                return (
                                    <IncomeCard
                                        key={entry.incomeId}
                                        incomeId={entry.entryId.toString()}
                                        incomeSourceName={entry.entryType}
                                        incomeSourceId={entry.entryId}
                                        moneyReceived={entry.money}
                                        dateCreated={entry.dateInserted}
                                        description={''}
                                    />

                                )
                            }
                        )}

                    </Col>


                </Row>

            </div>
        </>
    );
};