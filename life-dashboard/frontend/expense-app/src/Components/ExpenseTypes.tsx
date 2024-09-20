import { useEffect, useState } from "react";
import { Container, Row, Col, Modal, Button } from "react-bootstrap";
import { ExpenseType } from "../interfaces/ExpenseInterfaces";
import { MouseEvent } from "react";
import { ExpenseTypeComponent } from "./ExpenseTypeComponent";
import { ExpenseComponent } from "./ExpenseComponent";
export function ExpenseTypes(props: { items: ExpenseType[], expensesSum: number }) {

  const [showExpenseType, setShowExpeseType] = useState<boolean>(false);
  const [show, setShow] = useState<boolean>(false);

  useEffect(
    () => {
      console.log("Expense Types")
      console.log(props.items)
    }, []
  )

  const handleShow = (event: MouseEvent<HTMLElement>) => {
    console.log(event.currentTarget.getAttribute('id'));
    const id = event.currentTarget.getAttribute('id');
    if (id === 'add-expense') {
      setShow(!show);
    }
    else if (id === 'add-expense-type') {
      setShowExpeseType(!showExpenseType);
    }
  }

  const handleClose = () => {
    setShow(false);
  }

  return (
    <Container>

      <Row>
        <Col>
          <Button onClick={handleShow} id='add-expense' style={{ fontSize: '15px', lineHeight: '32px', verticalAlign: 'top' }} variant='danger'>Add Expense</Button>
        </Col>
        <Col md='6' style={{ verticalAlign: 'top', textAlign: 'center', border: '1px solid black', margin: '1rem', boxShadow: '2px 1px 11px 0px', backgroundColor: 'gold', lineHeight: '32px', fontSize: '30px' }}>
          <span style={{ verticalAlign: 'top' }}>
            Money Spent ${props.expensesSum}
          </span>
        </Col>
        <Col>
          <Button onClick={handleShow} id='add-expense-type' variant="danger" style={{ fontSize: '15px', lineHeight: '32px', verticalAlign: 'top' }}>Add Expense Type</Button>
        </Col>
      </Row>

      {props.items.map(
        item => {
          return (
            <Row style={{ border: '1px solid black' }} key={item.expenseTypeId}>
              <h2> ${item.expensesSum} {item.expenseTypeName} </h2>
              <Container>
                <Row style={{ background: 'red', color: 'white' }}>
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

      <Container>
        <Row style={{ textAlign: 'center' }}>
          <Col xs={6}>
            <Modal show={show} onHide={handleClose} >
              <Modal.Header closeButton>
                <h2>Add Expenses</h2>
              </Modal.Header>
              <Modal.Body>
                <ExpenseComponent />
              </Modal.Body>
            </Modal>
          </Col>
          <Col xs={6}>
            <Modal show={showExpenseType} onHide={handleClose}>
              <Modal.Header closeButton>
                <h2>Add Expense Type</h2>
              </Modal.Header>
              <Modal.Body>
                <ExpenseTypeComponent />
              </Modal.Body>
            </Modal>
          </Col>
        </Row>
      </Container>
    </Container>
  )
}
