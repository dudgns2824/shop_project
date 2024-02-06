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

import {useState} from "react";
import {axiosApi} from "../lib/axiosApi";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();

    const [userId, setUserId] = useState(false);
    const [userPassword, setUserPassword] = useState(false);

    const clickLoginButton = (userId, userPassword) => {

        const res = axiosApi.get('/api/v1/auth/user/login', {
            params:
                {
                    userId: userId,
                    password: userPassword
                }
        }).then(e => {
            navigate('/dashboard')
        })
            .catch(e => {
                console.log(e)
            });

        return ;
    }

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
                                        onChange={e => {setUserId(e.target.value)}}
                                        placeholder="아이디"
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
                                        onChange={e => {setUserPassword(e.target.value)}}
                                        placeholder="패스워드 입력"
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
                                <Button onClick={() => clickLoginButton(userId, userPassword)} className="my-4 sign-button" color="primary" type="button" size="lg">
                                    로그인
                                </Button>
                            </div>
                            <div className="text-center">
                                <Button color="default" type="button" className="sign-button" size="lg" href="../auth/register">
                                    회원 가입
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

export default Login;