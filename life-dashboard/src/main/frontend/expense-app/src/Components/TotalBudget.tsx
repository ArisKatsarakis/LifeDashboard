import { Row, Container, Col } from "react-bootstrap"
export const TotalBudget = () => {
    return (
        <Container>

            <Row>
                <Col md='3'>
                    <h2>
                        Budget Now 10e
                    </h2>
                    
                </Col>
                <Col md='4' style={{background:'green' , color: 'white'}} >
                    <h2>last income: 2023-10-30</h2>   
                </Col>
                <Col md='4' style={{background:'red' , color: 'white'}} >
                    <h2>last expense: 2023-10-30</h2>   
                </Col>
            </Row>
        </Container>
    )
}