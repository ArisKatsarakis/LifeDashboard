import React, {useEffect, useState} from "react";
import {samples} from "../Utilities/Samples";
import {Accordion, Badge, ListGroup, Button} from "react-bootstrap";
import axios from "axios";
import {variables} from "../Utilities/Variables";
import {Bin, Calendar, Pencil, Plus} from "../Icons/CommonIcons";
import {useNavigate} from "react-router-dom";

export const Incomes = () => {
    const [incomes, setIncomes] = useState(samples.SAMPLE_INCOMES);
    const navigate = useNavigate();
    const fetchIncomes = async () => {
        const data = await axios.get(variables.fetchIncomesURL);
        setIncomes(data.data);
    };
    useEffect(
        () => {
            fetchIncomes();
        }, []
    )
    return (
        <div>
            <h2 className='text-center text-bg-success'>Incomes</h2>
            <ListGroup>
                {
                    incomes.map(
                        income =>
                            <ListGroup>
                                <ListGroup.Item>
                                    <Accordion key={income.incomeId}>
                                        <Accordion.Header>
                                            <div className='text-left'>
                                                {income.incomeId}
                                                <Badge bg='primary ms-1'>
                                                    <span>{income.moneyReceived}€</span>
                                                </Badge>
                                                <span className={'me-1 ms-1'}>
                                                    <Calendar/>
                                                    {income.dateCreated}
                                                </span>
                                            </div>
                                            <div className=''>
                                                <Button href={`../income/${income.incomeId}`} variant='outline-success'>
                                                    <Pencil/>
                                                </Button>
                                                <Button variant='outline-danger'>
                                                    <Bin/>
                                                </Button>
                                            </div>

                                        </Accordion.Header>
                                        <Accordion.Body>
                                            <span>
                                               Description: {income.description}
                                            </span>
                                        </Accordion.Body>
                                    </Accordion>

                                </ListGroup.Item>
                            </ListGroup>
                    )
                }
                <ListGroup.Item className='container'>
                    <Button variant='success' onClick={ () => {navigate('/income/-1')}}>
                        <span className='me-1'>Add Income</span> <Plus/>
                    </Button>
                </ListGroup.Item>
            </ListGroup>
        </div>
    );
};