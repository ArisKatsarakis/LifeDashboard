import {useState} from "react";
import {samples} from "../Utilities/Samples";

export const Dashboard = () => {

    const [expenses,setExpenses] = useState(samples.SAMPLE_EXPENSES);
    return (
        <>
            <div className='container'>
                <h2>
                    Expenses App
                </h2>
                <div>
                    Expenses
                    <div>
                        <table className= 'table table-sm table-light table-secondary'>
                            <thead>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Date Created</th>
                                <th>Ammount Spent</th>
                            </thead>
                            <tbody>
                            {
                                expenses.map(
                                    expense => {
                                        return (<tr>
                                            <td>{expense.expenseId}</td>
                                            <td>{expense.expenseName}</td>
                                            <td>{expense.expenseType}</td>
                                            <td>{expense.dateCreated}</td>
                                            <td>{expense.moneySpent}</td>
                                        </tr>);
                                    }
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    );
};