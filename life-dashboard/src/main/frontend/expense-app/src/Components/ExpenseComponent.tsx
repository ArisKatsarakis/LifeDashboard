import { FormEvent, useEffect, useState } from "react";
import { Form, Row, Col, DropdownButton, Dropdown, InputGroup, Button } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { variables } from "../Utilities/Variables";
import axios from "axios";
import { ExpensePromise, Expense } from "../Interfaces/ExpenseInterfaces";
export const ExpenseComponent = () => {
    const { expenseId } = useParams();
    const today = new Date();

    const [expenseName, setExpenseName] = useState('');
    const [expenseType, setExpenseType] = useState('');
    const [moneySpent, setMoneySpent] = useState(0);
    const [dateCreated, setDateCreated] = useState('');
    const [types, setTypes] = useState([])
    const fetchExpenseById = async () => {
        const expense: ExpensePromise = await axios.get(variables.fetchExpensesURL + '/' + expenseId,);
        console.log(expense.data);
        setExpenseName(expense.data.expenseName);
        setDateCreated(expense.data.dateCreated);
        setMoneySpent(expense.data.moneySpent);
        setExpenseType(expense.data.expenseType);

    }
    const fetchTypes = async () => {
        const result = await axios.get(variables.expenseTypesURL);
        setTypes(result.data);

    }
    useEffect(
        () => {
            if (expenseId != '-1') {
                fetchExpenseById();
            }
            fetchTypes();

        }, []
    )
    const handleSubmit = async ( event :FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        console.log(moneySpent, expenseName, expenseType, dateCreated)
        const expenseData: Expense = {
            expenseId: 0,
            moneySpent: moneySpent,
            expenseName: expenseName,
            dateCreated: dateCreated,
            expenseType: expenseType
        }
        if (expenseId === '-1') {
            await createNewExpenseFromApi(expenseData);
        }

    }

    const createNewExpenseFromApi = async (expenseData: Expense) => {
        const response = await axios.post(variables.fetchExpensesURL, expenseData);
        console.log(response.data);
    }
    return (
        <>
        <h2 className='text-center text-bg-danger'>Add/Edit Expnese</h2>
        <Form onSubmit={handleSubmit}>
            <Row className="mb-3">
                <Form.Group as={Col} md='12'>
                    <Form.Label>Expense Description</Form.Label>
                    <Form.Control
                        required
                        type="text"
                        value={expenseName}
                        onChange={event => (setExpenseName(event.currentTarget.value))}
                    />
                </Form.Group>



            </Row>
            <Row className="mb-3">
                <Form.Group as={Col} md='4' >
                    <Form.Label>
                        Expense Type
                    </Form.Label>
                    <InputGroup>
                        <Form.Select value={expenseType}
                            onInput={event => (setExpenseType(event.currentTarget.value))}>
                            <option>Select Expense Type</option>
                            {
                                types.map(
                                    type => {
                                        return (
                                            <option value={type} key={type}>
                                                {type}
                                            </option>
                                        )
                                    }
                                )
                            }
                        </Form.Select>
                    </InputGroup>
                </Form.Group>
                <Form.Group as={Col} md='4'>
                    <Form.Label>Date Created</Form.Label>
                    <Form.Control
                        required
                        value={dateCreated}
                        type='date'
                        onChange={event => (setDateCreated(event.currentTarget.value))}
                    />
                </Form.Group>
                <Form.Group as={Col} md='4'>
                    <Form.Label>Money Spent</Form.Label>
                    <InputGroup hasValidation>
                        <Form.Control
                            required
                            value={moneySpent}
                            type='number'
                            onChange={
                                event => (
                                    setMoneySpent(parseInt(event.currentTarget.value))
                                )
                            }
                        />
                        <InputGroup.Text id="inputGroupPrepend">€</InputGroup.Text>
                    </InputGroup>

                </Form.Group>

            </Row>
            <Row className="mb-3">
                <Button type="submit" variant="danger"> Save Expense</Button>
            </Row>

        </Form></>
    );
};