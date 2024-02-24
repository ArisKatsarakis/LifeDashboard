import { Card, Container, Row, Col } from 'react-bootstrap';
import styles from './Dashboard.module.css'
import { Expense } from '../Interfaces/ExpenseInterfaces';
import { Cash } from '../Icons/CommonIcons';

export const ExpenseCard = (expense: Expense) => {

    return (
        <Card className={styles.ExpenseCard} >
            <Card.Body >
                <Card.Text  >
                    <Container>
                        <Row md={12}>

                            <Col md={4}> <Cash /> </Col>
                            <Col md={5}>   <span className={styles.ExpenseType}> {expense.expenseType}</span></Col>
                            <Col md={3}> <span className={styles.ExpensePrice}> - {expense.moneySpent} €  </span></Col>
                        </Row>
                    </Container>
                </Card.Text>

            </Card.Body>

        </Card>

    )

}