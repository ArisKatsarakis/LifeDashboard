import { useEffect } from "react";
import { Container, Button, Row, Col } from "react-bootstrap"
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
export function Header(props: { username: string }) {
  const [cookies, setCookies] = useCookies(['jsonToken']);
  const navigate = useNavigate();
  const logoutFunction = () => {
    setCookies('jsonToken', null);
    window.location.reload();

  };

  return (
    <Container style={{ border: '1px solid black', marginBottom: '1rem' }}>
      <Row>
        <Col md='8'>
          <h2>{props.username}'s wallet</h2>
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
