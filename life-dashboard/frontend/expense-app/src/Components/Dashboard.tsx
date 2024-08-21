import { useEffect, useState, MouseEvent } from "react";
import { Button, Container, Modal, Row, Col } from "react-bootstrap";
import { getExpenses, getExpenseTypes, getIncome, getLastWallet } from "../Utilities/ApiClient";
import { Expense, ExpenseType } from "../interfaces/ExpenseInterfaces";
import { ExpenseTypes } from "./ExpenseTypes";
import { ExpenseAmmount } from "./ExpenseAmmount";
import { ExpenseComponent } from "./ExpenseComponent";
import { ExpenseTypeComponent } from "./ExpenseTypeComponent";
import { Income } from "../interfaces/IncomeInterfaces";
import { Wallet } from "../interfaces/WalletInterfaces";
import { IncomeComponent } from "./IncomeComponent";
import { Incomes } from "./Incomes";

function Dashboard() {

  const [expenseTypes, setExpenseTypes] = useState<ExpenseType[]>([]);
  const [expensesSum, setExpensesSum] = useState<number>(0);
  const [show, setShow] = useState<boolean>(false);
  const [showExpenseType, setShowExpeseType] = useState<boolean>(false);
  const [incomes, setIncomes] = useState<Income[]>([]);
  const [lastWallet, setLastWallet] = useState<Wallet>();
  const [showIncome, setShowIncome] = useState<boolean>(false);

  const styles = {
    money: {
      border: '1px solid black',
      padding: '2px',
      marginLeft: '1rem',
      boxShadow: '0px 1px 1px 1px'
    }
  }


  const setUp = async () => {
    const response: Expense[] = await getExpenses();
    const types: ExpenseType[] = await getExpenseTypes();
    const income: Income[] = await getIncome();
    const wallet: Wallet = await getLastWallet();
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
    setIncomes(income);
    setLastWallet(wallet);

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
    } else if (id === 'add-income') {
      setShowIncome(!showIncome);
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
      <div style={{ textAlign: 'center', fontSize: '3rem' }}>
        <span>Money Now</span>
        <span style={styles.money}>{lastWallet?.moneyNow}$ </span>
      </div>
      <div>
        <Incomes items={incomes} />
      </div>
      <div>
        <div style={{ marginTop: '1rem' }}>
          <Button id='add-income' onClick={handleShow} variant="success">Add Income</Button>
          <Modal show={showIncome} onHide={handleClose} >
            <Modal.Header closeButton>
              <h2>Add Expenses</h2>
            </Modal.Header>
            <Modal.Body>
              <IncomeComponent />
            </Modal.Body>
          </Modal>
        </div>
      </div>
      <hr />
      <div style={{ textAlign: 'center' }}>
        <Button onClick={handleShow} id='add-expense' style={{ marginRight: '1rem' }}>Add Expense</Button>
        <ExpenseAmmount expensesSum={expensesSum} />
        <Button onClick={handleShow} id='add-expense-type' >Add Expense Type</Button>
      </div>
      <ExpenseTypes items={expenseTypes} />
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
    </Container >
  );

}

export default Dashboard;
