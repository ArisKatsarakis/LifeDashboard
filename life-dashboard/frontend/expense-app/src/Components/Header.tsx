import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import { Nav, NavDropdown } from "react-bootstrap";

export const Header = () => {
  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary">
        <Container>
          <Navbar.Brand href="/">Dashboard</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="/expenses">Expenses</Nav.Link>
              <Nav.Link href="/incomes">Incomes</Nav.Link>
              <Nav.Link href="/stats">Statistics</Nav.Link>
              <Nav.Link href="/users">Users</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </>
  );
};
