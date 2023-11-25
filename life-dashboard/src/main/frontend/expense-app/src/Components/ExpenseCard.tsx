import {Card, Container, Row, Col} from  'react-bootstrap';
import styles from './Dashboard.module.css'
import { Expense } from '../Interfaces/ExpenseInterfaces';

export const ExpenseCard = (expense : Expense) => {

    return (
        <Card className={styles.ExpenseCard} >
        <Card.Title className={styles.ExpensePrice}>
         {expense.moneySpent} € 
        </Card.Title>
        <Card.Subtitle className={styles.ExpenseDate}>
            {expense.dateCreated}
        </Card.Subtitle>
        <Card.Body className={styles.ExpenseType}>
            <Card.Text >
            <span>Type: {expense.expenseType}</span>
            </Card.Text>
        </Card.Body>

    </Card>

    )

}