import React, { useEffect, useState } from "react";
import { samples } from "../Utilities/Samples";
import { Collapse, Button, Card, Table, Form, Row, Col, InputGroup } from "react-bootstrap";
import axios from "axios";
import { variables } from "../Utilities/Variables";
import { Bin, Calendar, Pencil, Plus } from "../Icons/CommonIcons";
import { useNavigate } from "react-router-dom";
import styles from './Income.module.css';
import { IncomeComponent } from "./IncomeComponent";

export const Incomes = () => {
    const [open, setOpen] = useState(false);
    const [incomes, setIncomes] = useState(samples.SAMPLE_INCOMES);
    const navigate = useNavigate();
    const [incomeSource, setIncomeSource] = useState('')
    const [incomeSources, setIncomeSources] = useState([]);
    const fetchIncomes = async () => {
        const data = await axios.get(variables.fetchIncomesURL);
        setIncomes(data.data);
    };
    const deleteIncomeById = async (incomeId: number) => {
        await axios.delete(`${variables.fetchIncomesURL}/${incomeId}`);
        navigate(0)
    }

    useEffect(
        () => {
            fetchIncomes();
        }, []
    )
    return (
        <div>
            <h2 className='text-center text-bg-success'>Incomes</h2>

            <Table variant="dark">
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
            <Button variant='success' onClick={() => { navigate('/income/-1') }}>
                <span className='me-1'>Add Income</span> <Plus />
            </Button>



        </div>
    );
};