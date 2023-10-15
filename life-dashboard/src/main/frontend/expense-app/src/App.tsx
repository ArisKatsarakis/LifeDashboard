import React from 'react';
import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import {Dashboard} from "./Components/Dashboard";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Expenses from "./Components/Expenses";
import {Header} from "./Components/Header";
import {Incomes} from "./Components/Incomes";
import {Container} from "react-bootstrap";



function App() {
  return (
      <Container>
          <BrowserRouter>
              <Header />
              <Routes>
                  <Route  path='/'  element={<Dashboard />}/>
                  <Route  path='/expenses' element={<Expenses />} />
                  <Route  path='/incomes' element={<Incomes />} />
              </Routes>

          </BrowserRouter>
      </Container>

  );
}

export default App;
