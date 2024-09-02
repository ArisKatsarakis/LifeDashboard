import { Col, Container, Row } from "react-bootstrap";
import { Income } from "../interfaces/IncomeInterfaces";

export function Incomes(props: { items: Income[] }) {
  return (
    <Container>
      <h2>INCOMES</h2>
      <Row style={{ boxShadow: '2px 0px 8px 3px', border: '1px solid black' }}>

        <h2>Nova</h2>
        {props.items.filter(item => item.incomeType === 'NOVA').map(
          item => {
            return <Col key={item.incomeId} style={{ border: '1px solid black', background: 'green', color: 'white' }}>{item.money}  </Col>
          }
        )}
      </Row>
      <Row style={{ border: '1px solid black', boxShadow: '2px 0px 8px 3px', marginTop: '10px' }}>
        <h2>Athali</h2>
        {props.items.filter(item => item.incomeType === 'ATHALI').map(
          item => {
            return <Col key={item.incomeId} style={{ border: '1px solid black', background: 'green', color: 'white' }}>{item.money} {item.incomeType}</Col>
          }
        )}
      </Row>
    </Container>
  )
}
