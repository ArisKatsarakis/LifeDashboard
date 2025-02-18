import { FormEvent, useState } from "react";
import { Col, Container, InputGroup, Row, Form, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { authenticateApi } from "../Utilities/ApiClient";
import { useCookies } from "react-cookie";

export function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [cookies, setCookies] = useCookies(['jsonToken']);

  const navigate = useNavigate();
  const handleSubmit = async (event: FormEvent<HTMLElement>) => {
    event.preventDefault();
    const response = await authenticateApi(username, password);
    console.log(response);
    if (response != 'Credentials Invalid') {
      setCookies('jsonToken', response.token);
      window.location.reload();
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

          <Row style={{ textAlign: 'center', marginTop: '1rem' }}>
            <Col>
              <Button type='submit' >Login </Button>
              <Button href="/register"> Register </Button>
            </Col>
          </Row>

        </Form>
      </Row>
    </Container>
  )
}
