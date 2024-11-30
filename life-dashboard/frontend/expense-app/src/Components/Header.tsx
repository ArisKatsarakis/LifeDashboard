import { useEffect } from "react";
import { Container, Button, Row, Col } from "react-bootstrap"
import { useCookies } from "react-cookie";
export function Header(props: { username: string }) {
  const [cookies, setCookies] = useCookies(['jsonToken']);
  const logoutFunction = () => {
    setCookies('jsonToken', null);
    window.location.reload();

  };

  return (
    <Container style={{ marginBottom: '1rem' }} fluid>
      <Row>
        <Col md='8'>
          <h2> Hello {props.username}</h2>
        </Col>
        <Col style={{ textAlign: 'right' }}>
          <Button variant="danger" onClick={logoutFunction}>
            Logout
          </Button>
        </Col>
      </Row>
    </Container >
  );
}
