import { Col, Container, Row } from "react-bootstrap";
import { DailyExpenseGoal, SavingGoal } from "../interfaces/SavingGoalInterfaces";

function transformTimeStampToDate(timestamp: string): Date {
  let dTime = new Date(timestamp);
  return dTime;
}

function getTodaysExpenseGoal(dailyGoals: DailyExpenseGoal[]): DailyExpenseGoal {
  let today = new Date();
  for (let i = 0; i < dailyGoals.length; i++) {
    if (transformTimeStampToDate(dailyGoals[i].regardingDay).toDateString() === today.toDateString()) {
      return dailyGoals[i];
    }
  }
  return dailyGoals[0];
}

function SavingGoals(props: { savingGoals: SavingGoal[] }) {

  const currentDate = new Date();
  return (
    <Container>
      <Row>
        <Col md={3} />
        <Col md={6} style={{ marginBottom: '1rem', textAlign: 'center' }}>
          <h2>{currentDate.toDateString()}</h2>
        </Col>
        <Col md={3} />
      </Row>

      {props.savingGoals.map(
        goal => {
          return (
            <Row style={{ textAlign: 'center', marginBottom: '1rem' }}>
              <Col md={4}>
                <h2>Goal: {goal.target}€</h2>
              </Col>
              <Col md={4}>
                <h2>Start: {goal.startDate.split('T')[0]}</h2>
              </Col>
              <Col md={4}>
                <h2>End: {goal.endDate.split('T')[0]}</h2>
              </Col>

              <hr />
              <Col md={6} style={{ textAlign: 'left' }}>
                <h2>Daily Expenses Goal</h2>
              </Col>
              <Col md={6} style={{ textAlign: 'right' }}>
                <h2>Achieved 6/30</h2>
              </Col>
              <Col>
                <h2>{getTodaysExpenseGoal(goal.dailyGoals).status}</h2>
              </Col>
              <Col>
                <h2>{getTodaysExpenseGoal(goal.dailyGoals).moneyGoal}€</h2>
              </Col>
              <Col>
                <h2>{getTodaysExpenseGoal(goal.dailyGoals).regardingDay}</h2>
              </Col>
              <hr />
            </Row>
          );
        }
      )}
    </Container >
  );
}
export default SavingGoals;
