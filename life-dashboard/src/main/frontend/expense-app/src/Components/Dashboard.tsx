import {useState} from "react";
import {samples} from "../Utilities/Samples";
import {variables} from "../Utilities/Variables";
import axios from "axios";
import {Button, Row, Col} from "react-bootstrap";
export const Dashboard = () => {

    const [expenses,setExpenses] = useState(samples.SAMPLE_EXPENSES);

    const getExpenses = async () => {
        const url = variables.fetchExpensesURL;
        await  axios.get(
            url
        )
    }
    return (
        <>
            <div className='container'>
                <Row>
                    <h2 className='text-center'>
                        Dashboard
                    </h2>
                </Row>
                <Row className='text-center'>
                    <Col>
                        <div>
                            <Button variant='danger'> Add Expense </Button>
                        </div>
                    </Col>
                    <Col>
                        <div>
                            <Button variant='success'>Add Income</Button>
                        </div>
                    </Col>
                </Row>


            </div>
        </>
    );
};