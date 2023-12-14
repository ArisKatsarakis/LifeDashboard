import { useEffect, useState } from "react";
import { sampleBudget } from "../Utilities/Samples";
import { variables } from "../Utilities/Variables";
import axios from "axios";
import { Button, Row, Col, } from "react-bootstrap";
import styles from './Dashboard.module.css'
import { ExpenseCard } from "./ExpenseCard";
import { IncomeCard } from "./IncomeCard";
import { TotalBudget } from "./TotalBudget";
import { Budget,  BudgetHistoryPromise, Entry } from "../Interfaces/BudgetInterfaces";
import { Minus, Plus } from "../Icons/CommonIcons";
import { useNavigate } from "react-router-dom";
export const Dashboard = () => {

    const [entries, setEntries] = useState<Entry[]>([]);
    const [budget, setBudget] = useState<Budget>(sampleBudget)
    const getBudugetHistoryFromApi = async () => {
        const results: BudgetHistoryPromise = await axios.get(variables.fetchHistory);
        setEntries(results.data.tenLastEntries);
        setBudget(results.data.budget);
    }
    const navigate = useNavigate();
    const addExpense = () => {
        navigate('./expenses/-1');
    }

    const addIncome = () => {
        navigate('./income/-1');
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
                    <Col md='1'>
                        <Button variant="danger" className={styles.AddExpense} onClick={addExpense}>
                            <Minus />
                        </Button>
                    </Col>
                    <Col md='9' style={{padding:'1rem'}}>
                        <TotalBudget
                            key={budget.budgetId}
                            lastExpenseDate={budget.lastExpenseDate}
                            dateCreated={budget.dateCreated}
                            lastIncomeDate={budget.lastIncomeDate}
                            budgetId={budget.budgetId}
                            walletMoney={budget.walletMoney}
                        />
                    </Col>

                    <Col md='1'>
                    <Button variant="success" className={styles.AddExpense} onClick={addIncome}>
                            <Plus />
                        </Button>
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