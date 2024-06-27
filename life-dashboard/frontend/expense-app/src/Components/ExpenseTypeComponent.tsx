import { FormEvent, useState } from "react";
import { Button, Container, Form, InputGroup } from "react-bootstrap";
import { ExpenseType, Expense } from "../interfaces/ExpenseInterfaces";
import { addExpenseType } from "../Utilities/ApiClient";

export function ExpenseTypeComponent() {
  const [name, setName] = useState<string>('');
  const handleSubmit = async (event: FormEvent<HTMLElement>) => {
    event.preventDefault();
    const arr: Expense[] = [];
    const payload: ExpenseType = {
      expenseTypeName: name,
      expense: arr,
      expenseTypeId: null,
      expensesSum: null,
    };
    const response = await addExpenseType(payload);
    console.log(response);
    window.location.reload();
  }
  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <InputGroup>
          <InputGroup.Text>Name: </InputGroup.Text>
          <Form.Control type="text" value={name} onChange={event => setName(event.currentTarget.value)} />
        </InputGroup>
        <Form.Group>
          <Button type="submit">Save Type</Button>
        </Form.Group>
      </Form>
    </Container>
  )
}
