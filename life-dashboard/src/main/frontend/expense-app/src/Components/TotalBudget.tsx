import { Row, Container, Col, Card } from "react-bootstrap"
import { Budget } from "../Interfaces/BudgetInterfaces"
import styles from './Dashboard.module.css';
export const TotalBudget = (budget: Budget) => {
    return (
        <Card className={styles.BudgetCard}>
            <Card.Body className={styles.BudgetMoney}>
                <h2>Budget: €{budget.walletMoney} </h2>
            </Card.Body>
        </Card>

    )
}