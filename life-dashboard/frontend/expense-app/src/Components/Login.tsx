import { FormEvent, useState } from "react";
import { Container, InputGroup, Row, Form, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { authenticateApi } from "../Utilities/ApiClient";

export function Login() {
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
