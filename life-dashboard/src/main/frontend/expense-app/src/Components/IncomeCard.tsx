import { Card, Col, Container, Row } from "react-bootstrap"
import styles from './Dashboard.module.css'
import { IncomeDTO } from "../Interfaces/IncomeInterfaces"
export const IncomeCard = (income: IncomeDTO) => {
    return (
        <Card className={styles.IncomeCard}>
            <Card.Title
                style={{ background: 'green', color: 'white' }}
            >Income at 2023-11-10</Card.Title>
            <Card.Body>
                <Card.Text>
                    <Container>
                        <Row>
                            <Col md='6' className={styles.expense}>
                                {income.moneyReceived}€
                            </Col>
                            <Col md='6' className={styles.expense}>
                                {income.incomeSourceId}€
                            </Col>
                        </Row>
                    </Container>
                </Card.Text>
            </Card.Body>
        </Card>
    )

}