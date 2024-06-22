import { Container, Row, Col } from "react-bootstrap";
import { Expense } from "../interfaces/ExpenseInterfaces";

export function Expenses(props: { items: Expense[] }) {

  return (
    <Container >
      <Row style={{ border: '10px', borderColor: 'black', marginTop: '1rem' }}>
        {props.items.map(
          item => {
            return (
              <Col key={item.expenseId}>
                <div style={{ textAlign: 'center' }} >
                  <span style={{ border: '1px solid black', padding: '5px', textAlign: 'center', fontSize: '30px', borderRadius: '1rem' }}>
                    {item.name}
                  </span>
                  <span style={{ border: '1px solid black', color: 'white', background: 'red', padding: '5px', textAlign: 'center', fontSize: '30px', borderRadius: '1rem' }}>
                    ${item.money} <br />
                  </span>
                  <span style={{ border: '1px dashed black', fontSize: '20px', marginTop: '1rem' }}>
                    {item.timestamp?.split("T")[0]}
                  </span>
                </div>
              </Col>
            )
          }
        )}
      </Row>
    </Container >
  )
}
