import {useParams} from "react-router-dom";
import {FormEvent, useEffect, useState} from "react";
import {samples} from "../Utilities/Samples";
import {Col, Form, Row, InputGroup, Button} from "react-bootstrap";
import axios, {AxiosResponse} from "axios";
import {variables} from "../Utilities/Variables";



export const IncomeComponent = () => {
    const {incomeId}  = useParams();
    const [moneyReceived, setMoneyReceived] = useState(1);
    const [description, setDescription]  = useState(null);
    const today = new Date();
    const [dateCreate, setDateCreated] = useState(today);
    const [incomeSources, setIncomeSources] = useState([])
    useEffect(
        () => {
            // @ts-ignore
            if(incomeId !== -1 ) {
                // @ts-ignore
                const {incomeId, description, moneyReceived, dateCreated} = fetchIncomeById();
                setDateCreated(dateCreated);
                setMoneyReceived(moneyReceived);
            }
            fetchIncomeSources();
        }, []

    )
    const fetchIncomeSources = async () => {
        const response = await axios.get<AxiosResponse>(variables.fetchIncomeSourcesURL);
        //@ts-ignore
        setIncomeSources(response.data.sources);
        console.log("Sources fetched");
    }
    const fetchIncomeById = () => {
        return samples.SAMPLE_INCOMES;
    }

    async function createIncome(income: { dateCreated: any; description: any; moneyReceived: number; incomeSource: any}) {
        console.log(income);
        const postIncome = await axios.post(variables.fetchIncomesURL, income);
        console.log(postIncome.data);
    }

    const handleSubmit  = (event : FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (dateCreate == null) setDateCreated(today);
        const income = {
            moneyReceived: moneyReceived,
            description: description,
            dateCreated: dateCreate,
            incomeSource: {
                'incomeSourceId': 10001
            }
        }
        if(moneyReceived > 0) {
            setMessage(`The money are ${moneyReceived}€`);
            createIncome(income);
        }else {
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
                           <InputGroup.Text id="inputGroupPrepend">€</InputGroup.Text>
                           <Form.Control
                               type="number"
                               placeholder="Money Received"
                               aria-describedby="inputGroupPrepend"
                               required
                               onChange={(event) => {setMoneyReceived(parseInt(event.target.value))}}
                           />

                       </InputGroup>
                   </Form.Group>
                   <Form.Group as={Col} md={'4'} >
                       <Form.Label>Date Received</Form.Label>
                           <Form.Control
                               type="date"
                               placeholder="Date Received"
                               defaultValue={today.toISOString().split('T')[0]}
                               onChange={event => {setDateCreated(new Date(event.target.value.toString()))}}
                               required
                           />

                   </Form.Group>
               </Row>
               <Row className='mb-3'>

                   <Form.Group as={Col} md={'4'} >
                       <InputGroup>
                           <Form.Select >
                               <option selected>Select Income Source Type</option>
                               {incomeSources.map(
                                   source => {
                                       //@ts-ignore
                                       return ( <option>{source.incomeType}</option> )
                                   }
                               )}
                           </Form.Select>
                           <Button variant={'success'}>+</Button>
                       </InputGroup>

                   </Form.Group>
               </Row>
               <Button type={'submit'}>Save</Button>
               { message}
           </Form>
        </>
    );
};