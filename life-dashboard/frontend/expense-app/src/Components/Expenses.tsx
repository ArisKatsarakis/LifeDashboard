import { useEffect, useState } from "react";
import { Container, Row } from "react-bootstrap";
import { Expense } from "../interfaces/ExpenseInterfaces";

export function Expenses(props: { items: Expense[] }) {
  return (
    <Container>
      <Row>
        {props.items.map(
          item => {
            // setSum(sum + (item.money != null ? item.money : 0));
            return (
              <div key={item.expenseId} style={{ textAlign: 'center', border: '1px solid black', margin: '1rem', boxShadow: '2px 1px 11px 0px', backgroundColor: 'red' }} className={'col'}>
                <span style={{ margin: '1rem', fontSize: '30px' }}>Name: {item.name}</span>
                <hr />
                <span style={{ fontSize: '30px', color: 'white' }}>$ {item.money}</span>
              </div>
            )
          }
        )}
      </Row>
    </Container>
  )
}
