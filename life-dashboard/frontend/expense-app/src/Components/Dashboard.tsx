import { useEffect } from "react";
import { Button, Col, Container, Row } from "react-bootstrap";


export function Dashboard(props: { username?: string }) {
  const MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'Octomber', 'November', 'December']


  const balance = {
    current: 4324.49
  };

  return (
    <Container>
      <Container>
        <h2>
          Hello {props.username}
        </h2>
      </Container>
      <Container>
        <Row style={{ border: '1px solid black', borderRadius: '1rem', alignItems: 'center', fontSize: '25px' }}>
          <Col md='8'>
            <span>
              Current Balance:
            </span>
            <br />
            <span>
              ${balance.current}
            </span>
          </Col>
          <Col md='4'>
            <Button variant="secondary-outline" style={{ borderRadius: '2rem', fontSize: '20px', border: '1px solid black' }}>
              +
            </Button>
          </Col>
        </Row>
      </Container>
    </Container>
  );
}

