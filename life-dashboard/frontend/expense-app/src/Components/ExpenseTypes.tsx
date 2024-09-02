import { useEffect } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { ExpenseType } from "../interfaces/ExpenseInterfaces";
import { Expenses } from "./Expenses";
export function ExpenseTypes(props: { items: ExpenseType[] }) {

  useEffect(
    () => {
      console.log("Expense Types")
      console.log(props.items)
    }, []
  )
  return (
    <Container>
      <Row>
        {props.items.map(
          item => {
            return (
              <Col style={{ border: '1px solid black' }}>
                <h2>{item.expenseTypeName} ${item.expensesSum}</h2>
                <Container>
                  <Row>
                    {item.expense.map(
                      expense => {
                        if (item.expensesSum != null && expense.money != null) {
                          const widthPercentage = (100 / item.expensesSum) * expense.money;
                          return <Col style={{ width: `${widthPercentage}%`, border: '1px solid black' }}>{expense.money}</Col>
                        }

                      }
                    )}
                  </Row>

                </Container>
              </Col>
            )
          }
        )}
      </Row>
      {/* {props.items.map( */}
      {/*   item => { */}
      {/*     return ( */}
      {/*       <Row key={item.expenseTypeId}> */}
      {/*         <h2 style={{ textAlign: 'center' }}>Expense Type: {item.expenseTypeName}</h2> */}
      {/*         {item.expense.length === 0 ? <span>No Expenses</span> : <Expenses items={item.expense} />} */}
      {/*       </Row> */}
      {/*     ) */}
      {/*   } */}
      {/* )} */}
    </Container>
  )
}
