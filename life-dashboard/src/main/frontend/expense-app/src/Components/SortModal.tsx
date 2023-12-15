import { Button, Form, Modal } from "react-bootstrap";
import { FormEvent, useState } from "react";
export const SortModal = () => {
    const [show, setShow] = useState(false);
    const [monthSelected, setMonthSelected]  = useState(0);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        console.log(`month selected: ${monthSelected}`);
        handleClose();
    }

    return (
        <>
            <Button variant="primary" onClick={handleShow}>
                Sort
            </Button>

            <Modal
                show={show}
                onHide={handleClose}
                backdrop="static"
                keyboard={false}
            >
                <Modal.Header closeButton>
                    <Modal.Title>Modal title</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group>
                            <Form.Label>Month</Form.Label>
                            <Form.Select value={monthSelected} onChange={event => setMonthSelected(parseInt(event.currentTarget.value))}>
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
                        </Form.Group>
                        <Form.Group className="mt-2">
                            <Button variant="secondary" onClick={handleClose} className="me-1">
                                Close
                            </Button>
                            <Button type="submit" variant="primary">Understood</Button>
                        </Form.Group>
                    </Form>
                </Modal.Body>
            </Modal>
        </>
    );
}