import { Card, Col, Container, Row } from "react-bootstrap"
import styles from './Dashboard.module.css'
import { IncomeDTO } from "../Interfaces/IncomeInterfaces"
export const IncomeCard = (income: IncomeDTO) => {

    return (
        <Card className={styles.IncomeCard}>
            <Card.Title className={styles.IncomePrice}>
                        {income.moneyReceived}€
            </Card.Title>
            <Card.Subtitle className={styles.IncomeDate}>
                {income.dateCreated}
            </Card.Subtitle>
            <Card.Body>
                <Row>
                    
                </Row>
                <Row>
                    <span className={styles.IncomeSource}>
                        {income.incomeSourceName}
                    </span>
                </Row>                
            </Card.Body>
        </Card>
    )

    // return (
    //     <Card className={styles.IncomeCard}>
    //         <Card.Title
    //             style={{ background: 'green', color: 'white' }}
    //         >Income at 2023-11-10</Card.Title>
    //         <Card.Body>
    //             <Card.Text>
    //                 <Container>
    //                     <Row  md='12' className={styles.income}>
    //                         <span style={{fontSize: '3rem'}}>
    //                           {income.moneyReceived}€
    //                         </span>
    //                         <span>
    //                             {income.incomeSourceName}
    //                         </span>
    //                     </Row>
    //                 </Container>
    //             </Card.Text>
    //         </Card.Body>
    //     </Card>
    // )

}