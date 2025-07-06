import { Button, Col, Container, Row } from "react-bootstrap"

const Header = (props: { loggedIn: boolean }) => {
	return (
		<Container style={{ borderBottom: '1px solid black' }} fluid>
			<Row>
				<Col md='6' style={{}}>
					<a href="/" className="link-secondary"> <h2> Expense App </h2> </a>
				</Col>
				<Col md='6' style={{ textAlign: 'right' }} className="mt-2">
					<Button variant="success" >Login </Button>
					<Button variant="danger" > Register </Button>
				</Col>
			</Row>
		</Container >
	)
}
export { Header }
