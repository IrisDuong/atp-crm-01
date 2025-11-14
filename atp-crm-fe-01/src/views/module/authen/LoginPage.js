import React from 'react';
import { Card, Divider, Button, Row, Col,Layout, Typography } from 'antd';
import { GoogleOutlined } from '@ant-design/icons';
import { IoLogoGoogleplus } from "react-icons/io";
import { FcGoogle } from "react-icons/fc";
import {QRCodeCanvas} from 'qrcode.react'; // Make sure to install this: npm install qrcode.react
import ATP_LOGO from "../../../public/images/atp_logo-01.png";
const { Header, Content } = Layout;
const LoginPage = () => {
  return (
    <div className="login-container" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh', backgroundColor: '#f0f2f5' }}>
      <Row>
        <Col span={10}>
          <Card 
              className='login-intro'>
              <Header
                backgroundColor="#ffffff !important"
              >
                <img src={ATP_LOGO} alt="ATP Logo"/>
              </Header>
              <Content style={{ padding: '20px' }}>
                <Typography.Title level={4} style={{ textAlign: 'center' }}>
                  Xây Giá Trị - Vui Cuộc Sống
                </Typography.Title>
              </Content>
          </Card>
        </Col>
        <Col span={14}>
          <div className='login-area'>
            {/* QR Code Section */}
            <Card
              className='qr-container'
              cover={<QRCodeCanvas value="https://your-app.com/login-qr-data" size={156} level="H" />}
            >
              <Card.Meta title="" description="Sử dụng tài khoản Google trên ứng dụng di động của bạn để quét mã" />
            </Card>
            <Divider plain>Hoặc</Divider>
            <Button
              icon={<FcGoogle />}
              size="large"
              onClick={() => {
              }}
            >
              Đăng nhập với Gmail
            </Button>
          </div>
        </Col>
      </Row>
    </div>
  );
};

export default LoginPage;