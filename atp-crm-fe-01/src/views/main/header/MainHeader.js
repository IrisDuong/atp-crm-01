import { Space, Avatar ,Dropdown, Badge, Menu, Typography} from 'antd'
import {
  MailOutlined,
  BellOutlined,
  GlobalOutlined,
  LogoutOutlined,
  UserOutlined
} from '@ant-design/icons';
import { Header } from 'antd/es/layout/layout'
import React from 'react'
const { Text } = Typography;
function MainHeader() {
  return (
    <Header
        id='main-header'
    >
        <div>
            ATP
        </div>
        <Space size="large" align='center'>
            
            {/* Nhóm các logo chức năng */}
            <Badge count={5} className="header-icon">
                <MailOutlined style={{ fontSize: '18px' }} />
            </Badge>
            
            <Badge count={12} className="header-icon">
                <BellOutlined style={{ fontSize: '18px' }} />
            </Badge>
            
            <Dropdown
                overlay={
                <Menu>
                    <Menu.Item key="vi">Tiếng Việt</Menu.Item>
                    <Menu.Item key="en">English</Menu.Item>
                </Menu>
                }
                trigger={['click']}
            >
                <GlobalOutlined className="header-icon" style={{ fontSize: '18px', cursor: 'pointer' }} />
            </Dropdown>
            <Space align="center">
                <Avatar style={{ backgroundColor: "#1890ff" }}>U</Avatar>
                <Text strong>John Doe</Text>
            </Space>
        </Space>
    </Header>
  )
}

export default MainHeader