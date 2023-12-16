import React, { ChangeEvent, FormEvent, useEffect, useState } from "react";
import { samples } from "../Utilities/Samples";
import { Button, Table, Form, Col, Container, Row } from "react-bootstrap";
import axios from "axios";
import { variables } from "../Utilities/Variables";
import { Bin, Calendar, Pencil, Plus } from "../Icons/CommonIcons";
import { useNavigate } from "react-router-dom";
import { IncomeSource, IncomeSourcePromiese } from "../Interfaces/IncomeInterfaces";

export const Incomes = () => {
    const [incomes, setIncomes] = useState(samples.SAMPLE_INCOMES);
    const navigate = useNavigate();
    const [monthSelected, setMonthSelected] = useState(0);
    const [incomeSourceSelected, setIncomeSourceSelected] = useState(0);
    const [incomeSources, setIncomeSources] = useState<IncomeSource[]>([]);
    const fetchIncomes = async () => {
        const data = await axios.get(variables.fetchIncomesURL);
        setIncomes(data.data);
    };
    const deleteIncomeById = async (incomeId: number) => {
        await axios.delete(`${variables.fetchIncomesURL}/${incomeId}`);
        navigate(0)
    }
    useEffect(
        () =>{
            fetchIncomeSources();
        }, []
    );

    const fetchIncomeSources = async () => {
        const response = await axios.get<IncomeSourcePromiese>(variables.fetchIncomeSourcesURL);
        //@ts-ignore
        setIncomeSources(response.data.sources);
        console.log("Sources fetched");
    }
    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();

    }

    const handleChangeMonth = (event: ChangeEvent<HTMLSelectElement>) => {
        event.preventDefault();
        setMonthSelected(parseInt(event.currentTarget.value));

    }

    const handleChangeIncomeSource = (event: ChangeEvent<HTMLSelectElement>) => {
        event.preventDefault();
        setIncomeSourceSelected(parseInt(event.currentTarget.value));

    }

    useEffect(
        () => {
            fetchIncomes();
        }, []
    )
    return (
        <Container>
            <h2 className='text-center text-bg-success'>Incomes</h2>
            <Row md={12} className="mb-1">
                <Col md={3}>
                    <Form.Select value={monthSelected} onChange={handleChangeMonth}>
                        <option value={0}>Select Month</option>
                        <option value={1}>January</option>
                        <option value={2}>February</option>
                        <option value={3}>March</option>
                        <option value={4}>April</option>
                        <option value={5}>May</option>
                        <option value={6}>June</option>
                        <option value={7}>July</option>
                        <option value={8}>August</option>
                        <option value={9}>September</option>
                        <option value={10}>Octomber</option>
                        <option value={11}>November</option>
                        <option value={12}>December</option>
                    </Form.Select>
                </Col>
                <Col md={3}>
                    <Form.Select value={incomeSourceSelected} onChange={handleChangeIncomeSource}>
                        <option value={0}>Select Income Source</option>
                        {
                            incomeSources.map(
                                source => {
                                    return (
                                        <option value={source.incomeSourceId}>{source.name}</option>
                                    );
                                }
                            )
                        }
                    </Form.Select>
                </Col>
                <Col md={2}></Col>
                <Col md={4}>
                    <Button variant="success" onClick={() => navigate('/income-sources/-1')}> Add Income Source</Button>
                </Col>
            </Row>
            <Row>
                <Table variant="dark" className="mt-1">
                    <thead>
                        <tr>
                            <th>Description</th>
                            <th>Money</th>
                            <th>Income Source</th>
                            <th>Date <Calendar /> </th>
                            <th colSpan={2}>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            incomes.map(
                                income => {
                                    return (
                                        <tr key={income.incomeId}>
                                            <td>{income.description}</td>
                                            <td>{income.moneyReceived}</td>
                                            <td>{income.incomeSourceName}</td>
                                            <td>{income.dateCreated.split('T')[0]}</td>
                                            <td>
                                                <Button href={`../income/${income.incomeId}`} variant='outline-success'>
                                                    <Pencil />
                                                </Button>
                                                <Button
                                                    onClick={() => {
                                                        deleteIncomeById(parseInt(income.incomeId));
                                                    }}
                                                    variant='outline-danger'
                                                    className="ms-2"
                                                >
                                                    <Bin />
                                                </Button>
                                            </td>
                                        </tr>
                                    )
                                }
                            )
                        }
                    </tbody>
                </Table>
            </Row>
            <Button variant='success' onClick={() => { navigate('/income/-1') }}>
                <span className='me-1'>Add Income</span> <Plus />
            </Button>



        </Container>
    );
};