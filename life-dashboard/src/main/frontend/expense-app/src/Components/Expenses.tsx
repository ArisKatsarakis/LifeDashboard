import {Table} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import {samples} from "../Utilities/Samples";
import axios from "axios";
import {variables} from "../Utilities/Variables";
function Expenses() {
    const [expenses, setExpenses] = useState(samples.SAMPLE_EXPENSES);
    const fetchExpenses = async () => {
        const data = await axios.get(variables.fetchExpensesURL);
        console.log(data.data);
        setExpenses(data.data);
        // return data.data;
    }
    // @ts-ignore
    useEffect(
        () => {
             fetchExpenses();
        }, []
    )
    return (
        <>
            <h2 className='text-center text-bg-danger'>Expenses</h2>
            <Table striped bordered hover size='sm'>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {
                    expenses.map(
                        expense =>
                                <tr key={expense.expenseId}>
                                    <td>{expense.expenseId}</td>
                                    <td>{expense.expenseName}</td>
                                    <td>{expense.dateCreated}</td>
                                    <td>{expense.expenseType}</td>
                                    <td>{expense.moneySpent}</td>
                                </tr>
                    )
                }
                </tbody>
            </Table>
        </>
    );
}

export default Expenses;