import { useEffect, useState } from "react";
import { Container } from "react-bootstrap";
import { authenticateApi } from "../Utilities/ApiClient";


function Dashboard() {
  const [message, setMessage] = useState('');
  const setUp = async () => {
    const response = await authenticateApi();
    setMessage(response.token);
  }
  useEffect(() => {
    setUp();

  }, [])
  return (
    <Container>
      <h2>Hello World !</h2>
      <h2>Token {message}</h2>
    </Container >
  );

}

export default Dashboard;
