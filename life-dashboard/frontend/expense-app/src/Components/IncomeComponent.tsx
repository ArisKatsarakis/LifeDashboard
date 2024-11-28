import { FormEvent, useState } from "react";
import { Button, Container, Form, InputGroup } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { Income } from "../interfaces/IncomeInterfaces";
import { addIncome } from "../Utilities/ApiClient";

export function IncomeComponent() {
  const navigate = useNavigate();
  const [money, setMoney] = useState<number>(0);
  const [incomeType, setIncomeType] = useState<string>('');
  const handleSubmit = async (event: FormEvent<HTMLElement>) => {
    event.preventDefault();
    const payload: Income = {
      money: money,
      incomeType: incomeType
    };
    const response = await addIncome(payload);
    console.log(response);
    navigate('/');
    window.location.reload();
  }
  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <InputGroup>
          <InputGroup.Text>Money Received: </InputGroup.Text>
          <Form.Control type="number" value={money} onChange={event => setMoney(parseInt(event.currentTarget.value))} />
        </InputGroup>
        <InputGroup>
          <InputGroup.Text>Money Received: </InputGroup.Text>
          <Form.Select required={true} value={incomeType} onChange={event => setIncomeType(event.target.value)}>
            <option value={''}>Select Income Stream </option>
            <option value={"NOVA"} selected>NOVA</option>
          </Form.Select>
        </InputGroup>
        <Form.Group>
          <Button type="submit">Save Type</Button>
        </Form.Group>
      </Form>
    </Container>
  )
}


