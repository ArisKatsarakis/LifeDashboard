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
      {props.items.map(
        item => {
          return (
            <Row style={{ border: '1px solid black' }} key={item.expenseTypeId}>
              <h2> ${item.expensesSum} {item.expenseTypeName} </h2>
              <Container>
                <Row>
                  {item.expense.map(
                    expense => {
                      if (item.expensesSum != null && expense.money != null) {
                        const widthPercentage = Math.floor((12 / item.expensesSum) * expense.money);
                        /*if > 0.5 use Math.ceil if < 0.5 use Math.Floor*/
                        return <Col md={widthPercentage} style={{ border: '1px solid black' }} key={expense.expenseId}>{expense.money}</Col>
                      }
                    }
                  )}
                </Row>
              </Container>
            </Row>
          )
        }
      )}

    </Container>
  )
}
