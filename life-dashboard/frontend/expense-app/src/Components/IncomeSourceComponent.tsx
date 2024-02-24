import { FormEvent, useState } from "react";
import { Button, Form } from "react-bootstrap"
import { variables } from "../Utilities/Variables";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export const IncomeSourceComponent = () => {
    const [sourceName, setSourceName] = useState('');
    const [stability, setStability] = useState('STABLE');
    const [incomeType, setIncomeType] = useState('CODING');
    const navigate = useNavigate();
    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const incomeSource = createPayload();
        const results = await axios.post(variables.fetchIncomeSourcesURL, incomeSource);
        console.log(results.data);
        navigate('/incomes');

    }

    const createPayload = () => {
        return {
            "name": sourceName,
            "stabilityType": stability,
            "incomeType": incomeType
        };
    }
    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group>
                <Form.Label>Name</Form.Label>
                <Form.Control
                    value={sourceName}
                    onChange={event => { setSourceName(event.currentTarget.value) }}
                />
                <Form.Label>Stability:</Form.Label>
                <Form.Select value={stability} onChange={event => setStability(event.currentTarget.value)}>
                    <option value="STABLE">STABLE</option>
                    <option value="UNSTABLE">UNSTABLE</option>
                </Form.Select>
                <Form.Label>Income Type:</Form.Label>
                <Form.Select value={incomeType} onChange={event => setIncomeType(event.currentTarget.value)}>
                    <option value="CODING">CODING</option>
                    <option value="SALES">SALES</option>
                    <option value="INVESTMENTS">INVESTMENTS</option>
                </Form.Select>
            </Form.Group>
            <Form.Group className="mt-2">
                <Button type="submit" variant="warning"> Save Income Source</Button>
            </Form.Group>
        </Form>
    );
}