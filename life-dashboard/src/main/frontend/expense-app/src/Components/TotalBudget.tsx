import { Row, Container, Col, Card } from "react-bootstrap"
import { Budget } from "../Interfaces/BudgetInterfaces"
import styles from './Dashboard.module.css';
import { PiggyBank } from "../Icons/CommonIcons";
export const TotalBudget = (budget: Budget) => {
    return (
        <Card className={styles.BudgetCard}>
            <Container>
                <Row md={12} className={styles.BudgetMoney}>
                    <Col md={4}>Budget:</Col>
                    <Col md={4}> €{budget.walletMoney} </Col>
                    <Col md={4}>
                        <PiggyBank />
                    </Col>
                </Row>
            </Container>
        </Card>

    )
}