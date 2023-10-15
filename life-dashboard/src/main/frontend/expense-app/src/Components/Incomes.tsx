import React, {useEffect, useState} from "react";
import {samples} from "../Utilities/Samples";
import {Accordion, Badge, ListGroup, Button} from "react-bootstrap";
import axios from "axios";
import {variables} from "../Utilities/Variables";
const Plus = () => {
    return (
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
             fill="currentColor" className="bi bi-plus-lg"
             viewBox="0 0 16 16">
            <path fill-rule="evenodd"
                  d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/>
        </svg>
    )
};

const Bin = () => {
    return (
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-trash"
             viewBox="0 0 16 16">
            <path
                d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
            <path
                d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
        </svg>
    );
};


const Pencil = () => {
    return (
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-pencil"
             viewBox="0 0 16 16">
            <path
                d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
        </svg>
    );
};
export const Incomes = () => {
    const [incomes, setIncomes] = useState(samples.SAMPLE_INCOMES);
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
                                                    {income.moneyReceived} €
                                                </Badge>
                                            </div>
                                            <div className='text-right'>
                                                <Button variant='outline-success'>
                                                    <Pencil />
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
                    <Button variant='success'>
                        <Plus />
                    </Button>
                </ListGroup.Item>
            </ListGroup>
        </div>
    );
};