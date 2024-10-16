import { useEffect, useState, MouseEvent } from "react";
import { Button, Container, Modal, Row, Col } from "react-bootstrap";
import { getExpenses, getExpenseTypes, getExpenseTypesExpenses, getIncome, getLastWallet } from "../Utilities/ApiClient";
import { Expense, ExpenseType, ExpenseTypeSum } from "../interfaces/ExpenseInterfaces";
import { ExpenseTypes } from "./ExpenseTypes";
import { Income } from "../interfaces/IncomeInterfaces";
import { Wallet } from "../interfaces/WalletInterfaces";
import { Incomes } from "./Incomes";
import { Header } from "./Header";

function Dashboard(props: { username?: string }) {

  const [expenseTypes, setExpenseTypes] = useState<ExpenseType[]>([]);
  const [expensesSum, setExpensesSum] = useState<number>(0);
  const [incomes, setIncomes] = useState<Income[]>([]);
  const [lastWallet, setLastWallet] = useState<Wallet>();
  const [map, setMap] = useState<Map<number, Expense[]>>(new Map());

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
    );
    setExpensesSum(sum);
    setExpenseTypes(types);
    console.log(expenseTypes);
    expenseTypes.forEach(
      async (expensetype) => {
        if (expensetype.expenseTypeId != null) {
          const arrayOfExpenses: ExpenseTypeSum = await getExpenseTypesExpenses(expensetype.expenseTypeId);
          const temp = map;
          temp.set(expensetype.expenseTypeId, arrayOfExpenses.expenses);
          setMap(temp);
          console.log(map);
        }
      }
    );
    setIncomes(income);
    setLastWallet(wallet);
  }

  useEffect(() => {
    setUp();
  }, []);



  return (
    //@TODO fix header
    <Container>
      <Header username={props?.username ? props.username : ''} />
      <Container style={{ border: '1px solid black', marginBottom: '2rem' }}>
        <Row >
          <Col md={12} style={{ textAlign: 'center' }}>
            <h2>Goal of this Month</h2>
          </Col>
          <Col md={6} style={{ border: '1px solid black' }}>
            <h2>You can spend: $20</h2>
          </Col>
          <Col md={6}>
            <h2>You have spend: $20</h2>
          </Col>
        </Row>
      </Container>
      <Incomes items={incomes} lastWallet={lastWallet?.moneyNow ? lastWallet?.moneyNow : 0} />
      <hr />
      <ExpenseTypes items={expenseTypes} expensesSum={expensesSum} />
    </Container >

  );

}

export default Dashboard;
