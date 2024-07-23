import { useEffect, useState, MouseEvent } from "react";
import { Button, Container, Modal, Row, Col } from "react-bootstrap";
import { getExpenses, getExpenseTypes, getIncome } from "../Utilities/ApiClient";
import { Expense, ExpenseType } from "../interfaces/ExpenseInterfaces";
import { ExpenseTypes } from "./ExpenseTypes";
import { ExpenseAmmount } from "./ExpenseAmmount";
import { ExpenseComponent } from "./ExpenseComponent";
import { ExpenseTypeComponent } from "./ExpenseTypeComponent";
import { Income } from "../interfaces/IncomeInterfaces";


function Dashboard() {
  const [expenseTypes, setExpenseTypes] = useState<ExpenseType[]>([]);
  const [expensesSum, setExpensesSum] = useState<number>(0);
  const [show, setShow] = useState<boolean>(false);
  const [showExpenseType, setShowExpeseType] = useState<boolean>(false);
  const [income, setIncome] = useState<Income>();

  const setUp = async () => {
    const response: Expense[] = await getExpenses();
    const types: ExpenseType[] = await getExpenseTypes();
    const income: Income = await getIncome();
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
    setIncome(income);
  }
  useEffect(() => {
    setUp();

  }, []);

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
    //@TODO fix header
    <Container>
      <div style={{ border: '1px solid black', textAlign: 'center' }}>
        <h2>Expenses Counter</h2>
      </div>
      <div style={{ textAlign: 'center' }}>
        <h2>Income Now:</h2>
        <span>
          <h2>$ {income?.money}</h2>
          <h2>Last Updated: {income?.timestamp.split('T')[0]}  {income?.timestamp.split('T')[1].split('.')[0]}</h2>
        </span>

      </div>
      <hr />
      <ExpenseTypes items={expenseTypes} />
      <hr />
      <ExpenseAmmount expensesSum={expensesSum} />
      <Container>
        <Row style={{ textAlign: 'center' }}>
          <Col xs={6}>
            <Button onClick={handleShow} id='add-expense' >Add Expense</Button>
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
            <Button onClick={handleShow} id='add-expense-type' >Add Expense Type</Button>
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
    </Container >
  );

}

export default Dashboard;
