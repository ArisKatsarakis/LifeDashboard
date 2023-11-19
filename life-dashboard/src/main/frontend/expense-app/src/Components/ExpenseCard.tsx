import {Card, Container, Row, Col} from  'react-bootstrap';
import styles from './Dashboard.module.css'
import { Expense } from '../Interfaces/ExpenseInterfaces';

export const ExpenseCard = (expense : Expense) => {

    return (
        <Card className={styles.ExpenseCard}>
        <Card.Title style={{ background: 'red', color: 'white' }}>
            Expense at {expense.dateCreated}
        </Card.Title>
        <Card.Body>
            <Card.Text>
                <Container>
                    <Row>
                        <Col md='6' className={styles.expense}>
                                {expense.moneySpent}€
                        </Col>
                        <Col md='6' className={styles.expense}>
                                Type: {expense.expenseType}
                        </Col>
                    </Row>

                </Container>
            </Card.Text>
        </Card.Body>

    </Card>

    )

}