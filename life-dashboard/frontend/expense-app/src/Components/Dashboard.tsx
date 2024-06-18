import { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { getExpenses } from "../Utilities/ApiClient";
import { Expense } from "../interfaces/ExpenseInterfaces";
import { AxiosResponse } from "axios";


function Dashboard() {
  const [expenses, setExpenses] = useState<Expense[]>([]);
  const setUp = async () => {
    const response: Expense[] = await getExpenses();
    response.forEach(
      expense => {
        console.log(expense);
      }
    )
    setExpenses(response);
  }
  useEffect(() => {
    setUp();

  }, [])
  return (
    <Container>
      <h2>Hello World !</h2>
      <div>
        {expenses.map(
          expense => {
            return (
              <ul>
                <li>ID: {expense.expenseId}</li>
                <li>Money: {expense.money}</li>
                <li>Timestamp: {expense.timestamp}</li>
              </ul>
            )
          }
        )}
      </div>
    </Container >
  );

}

export default Dashboard;
