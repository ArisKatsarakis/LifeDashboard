import { useEffect, useState } from "react";
import { Button, Container } from "react-bootstrap";
import { getExpenses, getExpenseTypes } from "../Utilities/ApiClient";
import { Expense, ExpenseType } from "../interfaces/ExpenseInterfaces";
import { Expenses } from "./Expenses";
import { ExpenseTypes } from "./ExpenseTypes";


function Dashboard() {
  const [expenses, setExpenses] = useState<Expense[]>([]);
  const [expenseTypes, setExpenseTypes] = useState<ExpenseType[]>([]);
  const [expensesSum, setExpensesSum] = useState<number>(0);
  const setUp = async () => {
    const response: Expense[] = await getExpenses();
    const types: ExpenseType[] = await getExpenseTypes();
    let sum = 0;
    response.forEach(
      expense => {
        if (expense.money != null) {
          sum += expense.money;
        }
      }
    )
    setExpenses(response);
    setExpensesSum(sum);
    setExpenseTypes(types);
  }
  useEffect(() => {
    setUp();

  }, [])
  return (
    <Container>
      <div style={{ border: '1px solid black', textAlign: 'center' }}>
        <h2>Expenses Counter</h2>
      </div>
      <div style={{ border: '1px solid black', textAlign: 'center' }}>
        <span style={{ border: '1px solid black', marginTop: '1rem', textAlign: 'center' }}>
          Money Spent ${expensesSum}
        </span>
      </div>
      <Expenses items={expenses} />
      <ExpenseTypes items={expenseTypes} />
      <Button href="/expenses">Add Expense</Button>
    </Container >
  );

}

export default Dashboard;
