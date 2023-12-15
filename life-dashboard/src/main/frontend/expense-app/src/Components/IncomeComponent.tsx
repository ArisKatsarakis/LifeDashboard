import { useNavigate, useParams } from "react-router-dom";
import { FormEvent, useEffect, useState } from "react";
import { samples } from "../Utilities/Samples";
import { Col, Form, Row, InputGroup, Button } from "react-bootstrap";
import axios, { AxiosResponse } from "axios";
import { systemLinks, variables } from "../Utilities/Variables";
import { Income } from "../Interfaces/IncomeInterfaces";



export const IncomeComponent = () => {
    const { incomeId} = useParams();
    const [moneyReceived, setMoneyReceived] = useState(0);
    const [description, setDescription] = useState('');
    const today = new Date();
    const [dateCreate, setDateCreated] = useState('');
    const [incomeSource, setIncomeSource] = useState('')
    const [incomeSources, setIncomeSources] = useState([]);
    const [selectedSourceId, setSelectedSourceId] = useState(-1);
    const navigate = useNavigate();
    useEffect(
        () => {


            // @ts-ignore
            if (incomeId !== '-1') {
                fetchIncomeById();
            }
            fetchIncomeSources()

        }, [incomeId]

    )
    const fetchIncomeSources = async () => {
        const response = await axios.get<AxiosResponse>(variables.fetchIncomeSourcesURL);
        //@ts-ignore
        setIncomeSources(response.data.sources);
        console.log("Sources fetched");
    }


    const updateIncome = async (income : Income) => {
        const updateResponse = await axios.put(`${variables.updateIncomeURL}${incomeId}`, income);
    } 
    const fetchIncomeById = async () => {
        const income = await axios.get(`${variables.fetchIncomeDTOURL}${incomeId}`);
        console.log(income.data)
        setSelectedSourceId(income.data.incomeSourceId);
        setDateCreated(income.data.dateCreated);
        setMoneyReceived(income.data.moneyReceived);
        setDescription(income.data.description? income.data.description : '');
        fetchIncomeSources();
    }

    async function createIncome(income: Income) {
        console.log(income);
        const postIncome = await axios.post(`http://localhost:8080/api/v1/${incomeSource}/incomes`, income);
        console.log(postIncome.data);
    }



    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const income : Income = {
            incomeId : '',
            moneyReceived: moneyReceived,
            description: description,
            dateCreated: dateCreate,
            incomeSourceId: parseInt(incomeSource ? incomeSource : selectedSourceId.toString()),
        }
        if (moneyReceived > 0 && incomeId === '-1') {
            setMessage(`The money are ${moneyReceived}€`);
            await createIncome(income);
            navigate(systemLinks.incomes);
        } else if (moneyReceived > 0 && incomeId !== '-1') {
            await updateIncome(income);
            navigate(systemLinks.incomes);
        } else {
            setMessage(" No money was set");
        }


    };
    const [message, setMessage] = useState('');


    return (
        <>
            <Form noValidate onSubmit={handleSubmit}>
                <Row className='mb-3'>
                    <Form.Group as={Col} md={'2'} >
                        <Form.Label>ID:</Form.Label>
                        <Form.Control
                            type={'text'}
                            value={incomeId}
                            disabled={true}

                        />
                    </Form.Group>
                    <Form.Group as={Col} md={'4'} >
                        <Form.Label>Money Received</Form.Label>
                        <InputGroup hasValidation>
                            <Form.Control
                                type="number"
                                placeholder="Money Received"
                                aria-describedby="inputGroupPrepend"
                                required
                                value={moneyReceived}
                                onChange={(event) => { setMoneyReceived(parseInt(event.target.value)) }}
                            />
                            <InputGroup.Text id="inputGroupPrepend">€</InputGroup.Text>

                        </InputGroup>
                    </Form.Group>
                    <Form.Group as={Col} md={'4'} >
                        <Form.Label>Date Received</Form.Label>
                        <Form.Control
                            type="date"
                            placeholder="Date Received"
                            defaultValue={today.toISOString().split('T')[0]}
                            onChange={event => { setDateCreated(event.currentTarget.value) }}
                            required
                        />

                    </Form.Group>
                </Row>
                <Row className='mb-3'>

                    <Form.Group as={Col} md={'8'} >
                    <Form.Label > Source of Income</Form.Label>
                        <InputGroup>
                            <Form.Select
                                id='incomeSource'
                                onChange={(event) => { setIncomeSource(event.currentTarget.value.toString()) }}
                            >
                                <option key={0}>Select Income Source Type</option>
                                {incomeSources.map(
                                    source => {
                                        //@ts-ignore
                                        const bool = (selectedSourceId === source.incomeSourceId) ? 'selected' : '';
                                        //@ts-ignore
                                        return <option  value={source.incomeSourceId} key={source.incomeSourceId} selected={bool} >{source.incomeType}</option>
                                    }
                                )}
                            </Form.Select>
                            <Button variant={'success'}>+</Button>
                        </InputGroup>

                        <Form.Label>Income Description</Form.Label>
                        <InputGroup hasValidation>
                            <Form.Control
                                type="text"
                                placeholder="Description"
                                aria-describedby="Description"
                                value={description.toString()}
                                required
                                onChange={(event) => { setDescription(event.target.value.toString()) }}
                            />

                        </InputGroup>

                    </Form.Group>
                </Row>
                <Button type={'submit'}>Save</Button>
                {message}
            </Form>
        </>
    );
};