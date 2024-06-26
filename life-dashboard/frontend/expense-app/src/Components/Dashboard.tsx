import { useEffect, useState, MouseEvent } from "react";
import { Button, Container, Modal, Row, Col } from "react-bootstrap";
import { getExpenses, getExpenseTypes } from "../Utilities/ApiClient";
import { Expense, ExpenseType } from "../interfaces/ExpenseInterfaces";
import { ExpenseTypes } from "./ExpenseTypes";
import { ExpenseAmmount } from "./ExpenseAmmount";
import { ExpenseComponent } from "./ExpenseComponent";
import { ExpenseTypeComponent } from "./ExpenseTypeComponent";


function Dashboard() {
  const [expenseTypes, setExpenseTypes] = useState<ExpenseType[]>([]);
  const [expensesSum, setExpensesSum] = useState<number>(0);
  const [show, setShow] = useState<boolean>(false);
  const [showExpenseType, setShowExpeseType] = useState<boolean>(false);

  const setUp = async () => {
    const response: Expense[] = await getExpenses();
    const types: ExpenseType[] = await getExpenseTypes();
    let sum = 0;
    response.forEach(
      expense => {
        if (expense.money != null) {
          sum += expense.money;
        }
      }
    )
    setExpensesSum(sum);
    setExpenseTypes(types);
  }
  useEffect(() => {
    setUp();

  }, []);

  const handleShow = (event: MouseEvent<HTMLElement>) => {
    // const id = event.currentTarget.getAttribute('id');
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
    // setShow(false);
  }

  return (
    //@TODO fix header
    <Container>
      <div style={{ border: '1px solid black', textAlign: 'center' }}>
        <h2>Expenses Counter</h2>
      </div>
      <ExpenseTypes items={expenseTypes} />
      <hr />
      <ExpenseAmmount expensesSum={expensesSum} />
      <Container>
        <Row style={{ textAlign: 'center' }}>
          <Col xs={6}>
            <Button onClick={handleShow} id='add-expense' >Add Expense</Button>
            <Modal show={show} onHide={handleClose}>
              <Modal.Header>
                <h2>Add Expenses</h2>
              </Modal.Header>
              <Modal.Body>
                <ExpenseComponent />
              </Modal.Body>
            </Modal>
          </Col>
          <Col xs={6}>
            <Button onClick={handleShow} id='add-expense-type' >Add Expense Type</Button>
            <Modal show={showExpenseType} onHide={handleClose}>
              <Modal.Header>
                <h2>Add Expenses</h2>
              </Modal.Header>
              <Modal.Body>
                <ExpenseTypeComponent />
              </Modal.Body>
            </Modal>
          </Col>
        </Row>
      </Container>
    </Container >
  );

}

export default Dashboard;
