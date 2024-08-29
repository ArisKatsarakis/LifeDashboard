import { Container, Button } from "react-bootstrap"

export function Header() {
  return (
    <Container style={{ border: '1px solid black', marginBottom: '1rem' }}>
      <Button >
        Login
      </Button>
      <Button style={{}}>
        Register
      </Button>
    </Container >
  );
}
