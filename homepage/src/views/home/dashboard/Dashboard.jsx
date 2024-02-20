import {
    Col,
    Row,
    Card,
    CardHeader,
    Container
} from "reactstrap";

const Dashboard = () => {
    return (
        <>
            <Container>
                <Row>
                    <Col lg="4" md="4">
                        <Card className="bg-default shadow border-0 dashboard-background">
                            <CardHeader className="bg-transparent pb-3">
                                <h3 className="font-white">대시보드 현황</h3>
                            </CardHeader>
                        </Card>
                    </Col>
                    <Col lg="4" md="4">
                        <Card className="bg-default shadow border-0 dashboard-background">
                            <CardHeader className="bg-transparent pb-3">
                                <h3 className="font-white">대시보드 002</h3>
                            </CardHeader>
                        </Card>
                    </Col>
                    <Col lg="4" md="4">
                        <Card className="bg-default shadow border-0 dashboard-background">
                            <CardHeader className="bg-transparent pb-3">
                                <h3 className="font-white">대시보드 003</h3>
                            </CardHeader>
                        </Card>
                    </Col>
                </Row>
                <Row className="row-padding-top">
                    <Col lg="3" md="3">
                        <Card className="bg-default shadow border-0 dashboard-background">
                            <CardHeader className="bg-transparent pb-3">
                                <h3 className="font-white">대시보드 현황</h3>
                            </CardHeader>
                        </Card>
                    </Col>
                    <Col lg="6" md="6">
                        <Card className="bg-default shadow border-0 dashboard-background">
                            <CardHeader className="bg-transparent pb-3">
                                <h3 className="font-white">대시보드 002</h3>
                            </CardHeader>
                        </Card>
                    </Col>
                    <Col lg="3" md="3">
                        <Card className="bg-default shadow border-0 dashboard-background">
                            <CardHeader className="bg-transparent pb-3">
                                <h3 className="font-white">대시보드 003</h3>
                            </CardHeader>
                        </Card>
                    </Col>
                </Row>
                <Row className="row-padding-top">
                    <Col lg="8" md="8">
                        <Card className="bg-default shadow border-0 dashboard-background">
                            <CardHeader className="bg-transparent pb-3">
                                <h3 className="font-white">대시보드 현황</h3>
                            </CardHeader>
                        </Card>
                    </Col>
                    <Col lg="4" md="4">
                        <Card className="bg-default shadow border-0 dashboard-background">
                            <CardHeader className="bg-transparent pb-3">
                                <h3 className="font-white">대시보드 002</h3>
                            </CardHeader>
                        </Card>
                    </Col>
                </Row>
            </Container>

        </>
    );
}

export default Dashboard;