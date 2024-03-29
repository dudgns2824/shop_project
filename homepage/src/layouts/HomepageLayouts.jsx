import React from "react";
import {useLocation, Route, Routes, Navigate, Outlet} from "react-router-dom";
// reactstrap components
import {Container, Row, Col} from "reactstrap";

// core components
import AuthNavbar from "../components/Navbars/AuthNavbar.jsx";
import AuthFooter from "../components/Footers/AuthFooter.jsx";

import routes from "../router/AppRouter.jsx";
import Login from "../views/auth/Login";

const Homepage = (props) => {
    const mainContent = React.useRef(null);
    const location = useLocation();

    React.useEffect(() => {
        document.body.classList.add("fill-home-background");
        return () => {
            document.body.classList.remove("fill-home-background");
        };
    }, []);
    React.useEffect(() => {
        document.documentElement.scrollTop = 0;
        document.scrollingElement.scrollTop = 0;
        mainContent.current.scrollTop = 0;
    }, [location]);

    return (
        <>
            <div className="main-content" ref={mainContent}>
                <AuthNavbar/>
                <div className="header bg-primary py-7 py-lg-8">
                    <div className="separator separator-bottom separator-skew zindex-100">
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            preserveAspectRatio="none"
                            version="1.1"
                            viewBox="0 0 2560 100"
                            x="0"
                            y="0"
                        >
                            <polygon
                                className="fill-default"
                                points="2560 0 2560 0 0 0"
                            />
                        </svg>
                    </div>
                </div>
                <div class="mt--8 pb-8 container">
                    <div class="justify-content-center row">
                        <Outlet/>
                    </div>
                </div>
                <Container className="mt--8 pb-5">
                    <Row className="justify-content-center">
                        <Routes>
                            {/*{getRoutes(routes)}*/}
                            {/*<Route path="*" element={<Navigate to="/auth/login" replace/>}/>*/}
                        </Routes>
                    </Row>
                </Container>
            </div>
            <AuthFooter/>
        </>
    );
};
export default Homepage;