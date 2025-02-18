import { FormEvent, useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { apiLinks } from "../Utilities/Variables";
export function Register() {
  const navigate = useNavigate();
  const [email, setEmail] = useState<string>("");
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const handleSubmit = async function (event: FormEvent<HTMLElement>) {
    event.preventDefault();
    console.log(`Email ${email}`);
    console.log(`username ${username}`);
    console.log(`password ${password}`);
    const payload = {

    }
    const response = await axios.post(apiLinks.registrationLink, payload);
    console.log(response);
  }

  const navigateBack = () => {
    navigate('/');
  }

  // TODO  finish the regitration form add the appropriate forms.
  return (
    <Container>
      <Row>
        <Col> <Button variant="primary" onClick={navigateBack}>Back</Button> </Col>
      </Row>
      <Row style={{ textAlign: 'center' }}><h2> Registration Form </h2></Row>
      <Row>
        <Form onSubmit={handleSubmit} id="registration-form"   >
          <Form.Group className="mb-3 col-md-8">
            <Form.Label>Email: </Form.Label>
            <Form.Control type="email" placeholder="Enter Email"
              onChange={(event) => { setEmail(event.target.value) }} />
            <Form.Label>Username: </Form.Label>
            <Form.Control type="text" placeholder="Enter username"
              onChange={(event) => { setUsername(event.target.value) }} />
            <Form.Label>Password: </Form.Label>
            <Form.Control type="password" placeholder="Enter username"
              onChange={(event) => { setPassword(event.target.value) }} />
          </Form.Group>
          <Form.Group>
            <Button type="submit" >Register</Button>
          </Form.Group>
        </Form>
      </Row>
    </Container>
  )
}
