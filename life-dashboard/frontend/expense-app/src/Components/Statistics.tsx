import { Container } from "react-bootstrap";

export function Statistiscs() {

  return (
    <Container>
      <h2> Statistiscs </h2>
      <Container>
        <h3 style={{ background: 'red' }}>Expenses</h3>
      </Container>
      <Container>
        <h3 style={{ background: 'green' }}>Incomes</h3>
      </Container>
    </Container>
  );
}
