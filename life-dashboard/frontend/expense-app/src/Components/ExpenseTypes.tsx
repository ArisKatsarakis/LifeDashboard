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
    <Container style={{ border: '1px solid black', boxShadow: '2px 0px 8px 3px' }}>
      <Row>
        <Col style={{ textAlign: 'center' }}>
          <h2>EXPENSES</h2>
        </Col>
      </Row>
      <Row style={{ lineHeight: '30px', verticalAlign: 'top', marginBottom: '1rem' }}>
        <Col md='3' style={{ textAlign: 'center' }}>
          <Button onClick={handleShow} id='add-expense' style={{}} variant='danger'>Add Expense</Button>
        </Col>
        <Col md='6' style={{ verticalAlign: 'top', textAlign: 'center', border: '1px solid black', boxShadow: '2px 1px 11px 0px', backgroundColor: 'gold', lineHeight: '32px', fontSize: '30px' }}>
          <span style={{ verticalAlign: 'top' }}>
            Money Spent ${props.expensesSum}
          </span>
        </Col>
        <Col md='3' style={{ textAlign: 'center' }}>
          <Button onClick={handleShow} id='add-expense-type' variant="danger" style={{}}>Add Exp Type</Button>
        </Col>
      </Row>

      {props.items.map(
        item => {
          return (
            <Row style={{ marginTop: '4px' }}>
              <Col md={3} />
              <Col md={6} style={{ background: 'white', borderRadius: '1rem', border: '1px solid black', textAlign: 'center', marginBottom: '10px' }}>
                <h2> ${item.expensesSum} {item.expenseTypeName} </h2>
              </Col>
              <Col md={3} />
              <Container>
                {item.expense.map(
                  expense => {
                    return (
                      <Row style={{ background: '#f50057', color: 'black' }}>
                        <Col style={{ textAlign: 'center', border: '1px solid black' }}>
                          Name: {expense.name}
                        </Col>
                        <Col style={{ textAlign: 'center', border: '1px solid black' }}>
                          Cost: {expense.money}$
                        </Col>
                        <Col style={{ textAlign: 'center', border: '1px solid black' }}>
                          Date: {expense.timestamp}
                        </Col>
                      </Row>
                    );
                  }
                )}
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
