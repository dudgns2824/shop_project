import {
    Button,
    Card,
    CardHeader,
    CardBody,
    FormGroup,
    Form,
    Input,
    InputGroupText,
    InputGroup,
    Row,
    Col,
} from "reactstrap";

import { Modal } from 'flowbite-react';
import {useState} from "react";


export default function Login() {
    const [openModal, setOpenModal] = useState(false);

    return (
        <>
            <Col lg="5" md="8">
                <Card className="bg-secondary shadow border-0">
                    <CardHeader className="bg-transparent pb-3">
                        <div className="text-muted text-center mt-2 mb-3">
                            <small>Sign in with</small>
                        </div>
                        <div className="btn-wrapper text-center">
                            <Button
                                className="btn-neutral btn-icon"
                                color="default"
                                href="#pablo"
                                onClick={(e) => e.preventDefault()}
                            >
                <span className="btn-inner--icon">
                  <img
                      alt="..."
                      src={
                          require("../assets/img/icons/common/google.svg")
                              .default
                      }
                  />
                </span>
                                <span className="btn-inner--text">Google</span>
                            </Button>
                        </div>
                    </CardHeader>
                    <CardBody className="px-lg-5 py-lg-5">
                        <Form role="form">
                            <FormGroup className="mb-3">
                                <InputGroup className="input-group-alternative">
                                    <input-group-addon addonType="prepend">
                                        <InputGroupText>
                                            <i className="ni ni-email-83"/>
                                        </InputGroupText>
                                    </input-group-addon>
                                    <Input
                                        placeholder="Email"
                                        type="email"
                                        autoComplete="new-email"
                                    />
                                </InputGroup>
                            </FormGroup>
                            <FormGroup>
                                <InputGroup className="input-group-alternative">
                                    <input-group-addon addonType="prepend">
                                        <InputGroupText>
                                            <i className="ni ni-lock-circle-open"/>
                                        </InputGroupText>
                                    </input-group-addon>
                                    <Input
                                        placeholder="Password"
                                        type="password"
                                        autoComplete="new-password"
                                    />
                                </InputGroup>
                            </FormGroup>
                            <div className="custom-control custom-control-alternative custom-checkbox">
                                <input
                                    className="custom-control-input"
                                    id=" customCheckLogin"
                                    type="checkbox"
                                />
                                <label
                                    className="custom-control-label"
                                    htmlFor=" customCheckLogin"
                                >
                                    <span className="text-muted">Remember me</span>
                                </label>
                            </div>
                            <div className="text-center">
                                <Button className="my-4" color="primary" type="button" size="lg">
                                    Sign in
                                </Button>
                            </div>
                            <div className="text-center">
                                <Button color="default" type="button" className="signButton" size="lg" href="../auth/register">
                                    Sign up
                                </Button>
                            </div>
                        </Form>
                    </CardBody>
                </Card>
                <Row className="mt-3">
                    <Col xs="6">
                        <a
                            className="text-light"
                            href="#pablo"
                            onClick={(e) => e.preventDefault()}
                        >
                            <small>Forgot password?</small>
                        </a>
                    </Col>
                    <Col className="text-right" xs="6">
                        <a
                            className="text-light"
                            href="#pablo"
                            onClick={(e) => e.preventDefault()}
                        >
                            <small>Create new account</small>
                        </a>
                    </Col>
                </Row>
            </Col>
        </>
    );
}