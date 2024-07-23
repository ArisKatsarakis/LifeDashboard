import { useState, useEffect } from "react";
import { Button, Container, Form, InputGroup } from "react-bootstrap";
import { ExpenseType, Expense } from "../interfaces/ExpenseInterfaces";
import { useNavigate } from "react-router-dom";
import { addExpenseToExpenseType, getExpenseTypes } from "../Utilities/ApiClient";

export function ExpenseComponent() {

  const navigate = useNavigate();
  const [expenseTypes, setExpenseTypes] = useState<ExpenseType[]>([]);
  const [type, setType] = useState<number>();
  const [money, setMoney] = useState<number>();
  const [name, setName] = useState<string>();
  const [warningMessage, setWarningMessage] = useState<string>();
  const setUp = async () => {
    const types: ExpenseType[] = await getExpenseTypes();
    setExpenseTypes(types);
  }
  useEffect(() => {
    setUp();

  }, []);


  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const payload: Expense = {
      expenseId: null,
      money: null,
      timestamp: null
    };
    console.log(type, money);
    payload.money = money;
    payload.name = name;
    if (type != null) {
      const response = await addExpenseToExpenseType(type, payload);
      console.log(response);
      navigate('/');
      window.location.reload();
    } else {
      setWarningMessage("Please choose an Expense Type");
    }
  }
  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <Form.Group>
          {warningMessage ? <span style={{ color: 'red' }}>{warningMessage}</span> : <span></span>}
          <Form.Label>Type of Expense:</Form.Label>

          <Form.Select aria-label="Default select example" onChange={type => { setType(parseInt(type.target.value)) }}>
            <option>Select Expense Type</option>
            {expenseTypes.map(
              expense => {
                return (
                  <option key={expense.expenseTypeId} value={expense.expenseTypeId ? expense.expenseTypeId : 0}>{expense?.expenseTypeName}</option>
                );
              }
            )}
          </Form.Select>
        </Form.Group>
        <InputGroup className="mb-3">
          <InputGroup.Text>Expense Description</InputGroup.Text>
          <Form.Control aria-label="Amount (to the nearest dollar)" type="text" inputMode="text" value={name} onChange={event => setName(event.target.value)} />
        </InputGroup>
        <InputGroup className="mb-3">
          <InputGroup.Text>$</InputGroup.Text>
          <Form.Control aria-label="Amount (to the nearest dollar)" type="number" inputMode="numeric" value={money} onChange={event => setMoney(parseInt(event.target.value))} />
          <InputGroup.Text>.00</InputGroup.Text>
        </InputGroup>
        <Form.Group style={{ marginTop: '1rem' }}>
          <Button type="submit">Save</Button>
        </Form.Group>
      </Form>
    </Container >
  )
}
