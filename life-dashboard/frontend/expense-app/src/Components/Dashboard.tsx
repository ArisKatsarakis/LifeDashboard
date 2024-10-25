import { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { getExpenses, getExpenseTypes, getExpenseTypesExpenses, getIncome, getLastWallet, getSavingGoals } from "../Utilities/ApiClient";
import { Expense, ExpenseType, ExpenseTypeSum } from "../interfaces/ExpenseInterfaces";
import { ExpenseTypes } from "./ExpenseTypes";
import { Income } from "../interfaces/IncomeInterfaces";
import { Wallet } from "../interfaces/WalletInterfaces";
import { Incomes } from "./Incomes";
import { Header } from "./Header";
import { SavingGoal } from "../interfaces/SavingGoalInterfaces";
import SavingGoals from "./SavingGoal";

function Dashboard(props: { username?: string }) {

  const [expenseTypes, setExpenseTypes] = useState<ExpenseType[]>([]);
  const [expensesSum, setExpensesSum] = useState<number>(0);
  const [incomes, setIncomes] = useState<Income[]>([]);
  const [lastWallet, setLastWallet] = useState<Wallet>();
  const [map, setMap] = useState<Map<number, Expense[]>>(new Map());
  const [savings, setSavings] = useState<SavingGoal[]>([]);
  const setUp = async () => {
    const response: Expense[] = await getExpenses();
    const types: ExpenseType[] = await getExpenseTypes();
    const income: Income[] = await getIncome();
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
  }

  useEffect(() => {
    setUp();
  }, []);



  return (
    //@TODO fix header
    <Container>
      <Header username={props?.username ? props.username : ''} />
      <SavingGoals savingGoals={savings} />
      <Incomes items={incomes} lastWallet={lastWallet?.moneyNow ? lastWallet?.moneyNow : 0} />
      <hr />
      <ExpenseTypes items={expenseTypes} expensesSum={expensesSum} />
    </Container >

  );

}

export default Dashboard;
