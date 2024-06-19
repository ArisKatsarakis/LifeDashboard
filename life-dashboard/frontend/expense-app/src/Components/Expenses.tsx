import { Container, Row, Col } from "react-bootstrap";
import { Expense } from "../interfaces/ExpenseInterfaces";

export function Expenses(props: { items: Expense[] }) {

  return (
    <Container >
      <Row style={{ textAlign: 'center' }}>
        <h2> Expenses </h2>
      </Row>
      <Row style={{ border: '10px', borderColor: 'black', marginTop: '1rem' }}>
        {props.items.map(
          item => {
            return (
              <Col key={item.expenseId}>
                <ul >
                  <li>ID: {item.expenseId}</li>
                  <li>Money: {item.money}</li>
                  <li>Timestamp: {item.timestamp}</li>
                </ul>
              </Col>
            )
          }
        )}
      </Row>
    </Container >
  )
}
