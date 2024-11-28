import { useEffect, useState } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";
import { getExpenses, getExpenseTypes, getExpenseTypesExpenses, getIncome, getLastWallet, getMontlyIncomes, getSavingGoals } from "../Utilities/ApiClient";
import { Expense, ExpenseType, ExpenseTypeSum } from "../interfaces/ExpenseInterfaces";
import { ExpenseTypes } from "./ExpenseTypes";
import { Income } from "../interfaces/IncomeInterfaces";
import { Wallet } from "../interfaces/WalletInterfaces";
import { Incomes } from "./Incomes";
import { Header } from "./Header";
import { SavingGoal } from "../interfaces/SavingGoalInterfaces";
import SavingGoals from "./SavingGoal";
import { useLocation, useNavigate } from "react-router-dom";
import { MicExternalOn } from "@mui/icons-material";
import { textAlign } from "@mui/system";

function Dashboard(props: { username?: string }) {
  const MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'Octomber', 'November', 'December']
  const [expenseTypes, setExpenseTypes] = useState<ExpenseType[]>([]);
  const [expensesSum, setExpensesSum] = useState<number>(0);
  const [incomes, setIncomes] = useState<Income[]>([]);
  const [lastWallet, setLastWallet] = useState<Wallet>();
  const [map, setMap] = useState<Map<number, Expense[]>>(new Map());
  const [savings, setSavings] = useState<SavingGoal[]>([]);
  const [month, setMonth] = useState<String>('');
  const [monthCounter, setMonthCounter] = useState<number>(0);
  const navigate = useNavigate();
  const location = useLocation();

  const setUp = async () => {
    setMonth(MONTHS[monthCounter]);
    const response: Expense[] = await getExpenses();
    const types: ExpenseType[] = await getExpenseTypes();
    const income: Income[] = await getMontlyIncomes(monthCounter);
    const wallet: Wallet = await getLastWallet();
    const sGoals: SavingGoal[] = await getSavingGoals();

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
    setSavings(sGoals);
    console.log(monthCounter)
  }

  useEffect(() => {
    setUp();
  }, []);

  const nextMonth = async () => {
    let temp = monthCounter;
    temp++;
    setMonthCounter(temp);
    setMonth(MONTHS[monthCounter])
    setIncomes(await getMontlyIncomes(monthCounter))
    navigate(location.pathname, { state: location.state, replace: true });
  }


  const prevMonth = async () => {
    let temp = monthCounter;
    temp--;
    setMonthCounter(temp);
    setMonth(MONTHS[monthCounter])
    setIncomes(await getMontlyIncomes(monthCounter))
    navigate(location.pathname, { state: location.state, replace: true });
  }

  return (
    <Container>
      <Header username={props?.username ? props.username : ''} />
      <Container>
        <Row>
          <Col md={2} onClick={prevMonth}>
            <Button>
              Prev
            </Button>
          </Col>
          <Col md={8} style={{ textAlign: 'center' }}>
            <h2> {month}</h2>
          </Col>
          <Col md={2} style={{ textAlign: 'center' }}>
            <Button onClick={nextMonth}>
              Next
            </Button>
          </Col>
        </Row>
      </Container>
      <SavingGoals savingGoals={savings} />
      <Incomes items={incomes} lastWallet={lastWallet?.moneyNow ? lastWallet?.moneyNow : 0} />
      <hr />
      <ExpenseTypes items={expenseTypes} expensesSum={expensesSum} />
    </Container >

  );

}

export default Dashboard;
