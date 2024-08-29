
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';
import { BrowserRouter, Route, Routes, useNavigate } from "react-router-dom";
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import Dashboard from './Components/Dashboard';
import { ExpenseComponent } from './Components/ExpenseComponent';
import { FormEvent, useEffect, useState } from 'react';
import { Row, Col, Container, Form, InputGroup, Button } from 'react-bootstrap';
import { authenticateApi } from './Utilities/ApiClient';




function App() {
  function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const handleSubmit = async (event: FormEvent<HTMLElement>) => {
      event.preventDefault();
      const response = await authenticateApi(username, password);
      console.log(response);
      if (response != 'Credentials Invalid') {
        document.cookie += `walletUser:${username}`;
        console.log(document.cookie);
        console.log('fksjdlfjlaskjdkjf');
        navigate('/');
        setAuthenticated(true);
      }
    }
    return (
      <Container>
        <Row style={{ marginTop: '2rem' }}>

          <Form onSubmit={handleSubmit}>
            <InputGroup>
              <InputGroup.Text>Username: </InputGroup.Text>
              <Form.Control type="text" value={username} onChange={event => {
                setUsername(event.currentTarget.value)
              }} />
            </InputGroup>
            <InputGroup>
              <InputGroup.Text>Password: </InputGroup.Text>
              <Form.Control type="password" value={password} onChange={event => {
                setPassword(event.currentTarget.value)
              }} />
            </InputGroup>

            <InputGroup>
              <Button type='submit' >Login </Button>
            </InputGroup>
          </Form>
        </Row>
      </Container>
    )
  }
  const [username, setUsername] = useState<string>('');
  const [authenticated, setAuthenticated] = useState<boolean>(false);
  const getToken = () => {
    const cookie = document.cookie;
    console.log(cookie);
  }

  useEffect(
    () => {
      getToken();
    }, []
  );
  return (
    <Container>
      <BrowserRouter>
        {
          authenticated === true ?
            < Routes >
              <Route path='/' element={<Dashboard />} />
              <Route path='/expenses' element={<ExpenseComponent />} />
            </Routes>
            :
            <Login />
        }
      </BrowserRouter >
    </Container >

  );
}

export default App;
