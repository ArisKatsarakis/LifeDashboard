
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import { Dashboard } from "./Components/Dashboard";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Expenses from "./Components/Expenses";
import { Header } from "./Components/Header";
import { Incomes } from "./Components/Incomes";
import { Container } from "react-bootstrap";
import { IncomeComponent } from "./Components/IncomeComponent";
import { ExpenseComponent } from './Components/ExpenseComponent';
import { IncomeSourceComponent } from './Components/IncomeSourceComponent';
import { Statistiscs } from './Components/Statistics';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import { User } from './Components/User';



function App() {
  return (
    <Container>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path='/' element={<Dashboard />} />
          <Route path='/expenses' element={<Expenses />} />
          <Route path='/incomes' element={<Incomes />} />
          <Route path='/income/:incomeId' element={<IncomeComponent />} />
          <Route path='/expenses/:expenseId' element={<ExpenseComponent />} />
          <Route path='/income-sources/:incomeSourceId' element={<IncomeSourceComponent />} />
          <Route path='/stats' element={<Statistiscs />} />
          <Route path='/users' element={<User />} />

        </Routes>

      </BrowserRouter>
    </Container>

  );
}

export default App;
