import { Container, Row } from "react-bootstrap";
import { ExpenseType } from "../interfaces/ExpenseInterfaces";
import { Expenses } from "./Expenses";

export function ExpenseTypes(props: { items: ExpenseType[] }) {

  return (
    <Container>
      {props.items.map(
        item => {
          return (
            <Row key={item.expenseTypeId}>
              <h2 style={{ textAlign: 'center' }}>Expense Type: {item.expenseTypeName}</h2>
              {item.expense.length === 0 ? <span style={{ textAlign: 'center' }}>No Expenses</span> : <Expenses items={item.expense} />}
            </Row>
          )
        }
      )}
    </Container>
  )
}
