import { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { Income } from "../interfaces/IncomeInterfaces";

export function Incomes(props: { items: Income[] }) {
  return (
    <Container>
      <h2>INCOMES</h2>
      <Row style={{ border: '1px solid black' }}>
        {props.items.filter(item => item.incomeType === 'NOVA').map(
          item => {
            return <Col key={item.incomeId}>{item.money}  {item.incomeType}</Col>
          }
        )}
      </Row>
      <Row style={{ border: '1px solid black' }}>
        {props.items.filter(item => item.incomeType === 'ATHALI').map(
          item => {
            return <Col key={item.incomeId}>{item.money} {item.incomeType}</Col>
          }
        )}
      </Row>
    </Container>
  )
}
