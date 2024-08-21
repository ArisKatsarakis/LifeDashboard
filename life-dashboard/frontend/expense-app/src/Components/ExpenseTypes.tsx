import { Container, Row } from "react-bootstrap";
import { ExpenseType, Expense } from "../interfaces/ExpenseInterfaces";
import { Expenses } from "./Expenses";
import { useState, useEffect } from "react";
import { getExpenseTypesExpenses } from "../Utilities/ApiClient";
export function ExpenseTypes(props: { items: ExpenseType[] }) {

  const [expenses, setExpenses] = useState<Expense[]>([]);

  return (
    <Container>
      {
        props.items.map(
          item => {
            return (
              <Row key={item.expenseTypeId} >
                <h2 style={{ textAlign: 'center' }}>Expense Type: {item.expenseTypeName}</h2>
                {expenses.length === 0 ? <span style={{ textAlign: 'center' }}>No Expenses</span> : <Expenses items={expenses} />
                }
              </Row>
            )
          }
        )
      }
    </Container >
  )
}
