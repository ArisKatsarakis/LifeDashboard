
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Container } from "react-bootstrap";
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import Dashboard from './Components/Dashboard';



function App() {
  return (
    <Container>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Dashboard />} />

        </Routes>

      </BrowserRouter>
    </Container>

  );
}

export default App;
