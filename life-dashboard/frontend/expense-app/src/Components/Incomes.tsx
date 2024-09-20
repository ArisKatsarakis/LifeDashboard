import { useState } from "react";
import { Col, Container, Row, Modal, Button } from "react-bootstrap";
import { Income } from "../interfaces/IncomeInterfaces";
import { IncomeComponent } from "./IncomeComponent";
import { MouseEvent } from "react";
export function Incomes(props: { items: Income[], lastWallet: number }) {
  const [showIncome, setShowIncome] = useState<boolean>(false);

  const handleClose = () => {
    setShowIncome(false);
  }
  const handleShow = (event: MouseEvent<HTMLElement>) => {
    setShowIncome(!showIncome);
  }

  const styles = {
    money: {
      border: '1px solid black',
      padding: '2px',
      marginLeft: '1rem',
      boxShadow: '0px 1px 1px 1px'
    }
  };

  return (
    <Container>
      <Row>
        <Col md={12} style={{ textAlign: 'center' }}>
          <h2>INCOMES</h2>
        </Col>
      </Row>
      <Row style={{ marginTop: '1rem', marginBottom: '2rem' }}>
        <Col style={{ textAlign: 'center', fontSize: '60px', lineHeight: '32px' }}>
          <span style={styles.money}>{props.lastWallet}$ </span>
        </Col>
        <Col md={6} style={{ textAlign: 'center' }}>
          <Button id='add-income' onClick={handleShow} variant="success" style={{ fontSize: '30px', lineHeight: '32px', verticalAlign: 'top' }}>Add Income</Button>
          <Modal show={showIncome} onHide={handleClose} >
            <Modal.Header closeButton>
              <h2>Add Incomes</h2>
            </Modal.Header>
            <Modal.Body>
              <IncomeComponent />
            </Modal.Body>
          </Modal>
        </Col>
      </Row>
      <Row style={{ border: '1px solid black', boxShadow: '2px 0px 8px 3px', marginTop: '10px' }}>
        <h2>Nova</h2>
      </Row>
      <Row>
        {props.items.filter(item => item.incomeType === 'NOVA').map(
          item => {
            return (
              <Row>
                <Col md={2} />
                <Col
                  key={item.incomeId}
                  style={{ border: '1px solid black', background: 'green', color: 'white', textAlign: 'center', fontSize: '20px', marginTop: '3px', borderRadius: '1rem' }}
                >
                  ${item.money} @:{item.timestamp}
                </Col>
                <Col md={2} />
              </Row>
            )
          }
        )}
      </Row>
      <Row style={{ border: '1px solid black', boxShadow: '2px 0px 8px 3px', marginTop: '10px' }}>
        <h2>Athali</h2>
      </Row>
      {props.items.filter(item => item.incomeType === 'ATHALI').map(
        item => {
          return (
            <Row>
              <Col md={2} />
              <Col
                key={item.incomeId}
                style={{ border: '1px solid black', background: 'green', color: 'white', textAlign: 'center', fontSize: '20px', marginTop: '3px', borderRadius: '1rem' }}
              >
                ${item.money} @:{item.timestamp}
              </Col>
              <Col md={2} />
            </Row>
          )
        }
      )}
    </Container>
  )
}
