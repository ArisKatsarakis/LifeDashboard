import { FormEvent, useState } from "react";
import { Button, Container, Form, InputGroup } from "react-bootstrap";
import { Income } from "../interfaces/IncomeInterfaces";
import { addIncome } from "../Utilities/ApiClient";

export function IncomeComponent() {
  const [money, setMoney] = useState<number>(0);
  const handleSubmit = async (event: FormEvent<HTMLElement>) => {
    event.preventDefault();
    const payload: Income = {
      money: money,
      income_type: 0
    };
    const response = await addIncome(payload);
    console.log(response);
  }
  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <InputGroup>
          <InputGroup.Text>Money Received: </InputGroup.Text>
          <Form.Control type="number" value={money} onChange={event => setMoney(parseInt(event.currentTarget.value))} />
        </InputGroup>
        <Form.Group>
          <Button type="submit">Save Type</Button>
        </Form.Group>
      </Form>
    </Container>
  )
}
