import React from 'react';
import logo from './logo.svg';
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

        </Routes>

      </BrowserRouter>
    </Container>

  );
}

export default App;
