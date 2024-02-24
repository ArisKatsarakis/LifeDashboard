import { Card, Col, Container, Row } from "react-bootstrap"
import styles from './Dashboard.module.css'
import { IncomeDTO } from "../Interfaces/IncomeInterfaces"
import { Coin } from "../Icons/CommonIcons"
export const IncomeCard = (income: IncomeDTO) => {

    return (
        <Card className={styles.IncomeCard}>
            <Card.Body>
                <Row md={12}>
                    <Col md={4}>
                        <span>
                            <Coin />
                        </span>
                    </Col>
                    <Col md={4}>  <span className={styles.IncomeSource}>
                        {income.incomeSourceName}
                    </span></Col>
                    <Col md={4}>
                        <span className={styles.IncomePrice}>
                            + {income.moneyReceived}€
                        </span>
                    </Col>

                </Row>
            </Card.Body>
        </Card>
    )



}