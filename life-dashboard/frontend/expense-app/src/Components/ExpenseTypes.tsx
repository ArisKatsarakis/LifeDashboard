import { Container, Row, Col } from "react-bootstrap";
import { ExpenseType } from "../interfaces/ExpenseInterfaces";

export function ExpenseTypes(props: { items: ExpenseType[] }) {
  return (
    <Container>
      <Row style={{ textAlign: 'center' }}>
        <h2>Expense Types</h2>
      </Row>
      <Row>
        {props.items.map(
          item => {
            return (
              <Col key={item.expenseTypeId}>
                <ul>
                  <li>ID: {item.expenseTypeId}</li>
                  <li>Name: {item.expenseTypeName}</li>
                  <li>Expenses: {JSON.stringify(item.expense)}</li>
                </ul>
              </Col>
            )
          }
        )}
      </Row>

    </Container>
  )
}
