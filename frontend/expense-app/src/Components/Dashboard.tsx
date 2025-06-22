import { Button, Col, Container, Row } from "react-bootstrap";
import { useCookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from 'react';
import axios from "axios";
export function Dashboard(props: { username?: string }) {

  const navigate = useNavigate();
  const [cookies, setCookies] = useCookies(['jsonToken']);
  const [transactions, setTransactions] = useState([]);
  console.log(cookies);
  const MONTHS = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'Octomber', 'November', 'December']


  const balance = {
    current: 4324.49
  };

  const handleLogout = () => {
    setCookies('jsonToken', null);
    window.location.reload();
    navigate('/');
  };

  const getTransactions = async () => {

    const response = await axios.get('http://localhost:8080/api/v1/transactions', {
      headers: {
	Authorization: 'Bearer ' + cookies.jsonToken
      }
    });
    console.log(response.data);

  };

  useEffect(
    () => {
      getTransactions();
    }, []
  );

  return (
    <Container>
    <Row>
      <Button onClick={handleLogout}> Logout </Button>
    </Row>
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
      <Container> 
	  {
	    transactions
	  }
      </Container>
    </Container>
  );
}

