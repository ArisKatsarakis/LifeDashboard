import { Button, Table, Container } from "react-bootstrap";
import React, { useEffect, useState } from "react";
import { samples } from "../Utilities/Samples";
import axios from "axios";
import { variables } from "../Utilities/Variables";
import { Pencil, Bin, Plus } from "../Icons/CommonIcons";
import { useNavigate } from "react-router-dom";
import { ExpenseType } from "../Interfaces/ExpenseInterfaces";
function Expenses() {
  const navigate = useNavigate();
  const [expenses, setExpenses] = useState(samples.SAMPLE_EXPENSES);
  const [expensesByType, setExpensesByType] = useState<ExpenseType>();
  const fetchExpenses = async () => {
    const data = await axios.get(variables.fetchExpensesURL);
    console.log(data.data);
    setExpenses(data.data);
    // return data.data;
  }

  const deleteExpenseByIdApi = async (expenseId: number) => {
    await axios.delete(`${variables.fetchExpensesURL}/${expenseId}`);
    navigate(0);
  }

  const fetchExpensesByType = async () => {
    const expenses: ExpenseType = await axios.get(`${variables.fetchExpensesURL}/type/fun`);
    setExpensesByType(expenses);
  }
  // @ts-ignore 
  useEffect(
    () => {
      fetchExpenses();
      fetchExpensesByType();
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
                  <td>
                    <Button variant='primary' onClick={() => {
                      navigate(`/expenses/${expense.expenseId}`)
                    }}>
                      <Pencil />
                    </Button>
                    <Button
                      onClick={async () => {
                        await deleteExpenseByIdApi(expense.expenseId);
                      }}
                      variant='danger'>
                      <Bin />
                    </Button>
                  </td>
                </tr>
            )
          }
        </tbody>
      </Table>
      <Button variant='danger' onClick={() => { navigate('/expenses/-1') }}>
        <span className='me-1'>Add Expense</span> <Plus />
      </Button>
      <Container>
        {JSON.stringify(expensesByType)}
      </Container>
    </>
  );
}

export default Expenses;
