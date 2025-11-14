import React from 'react'
import {Layout} from "antd";
import { Outlet } from 'react-router-dom';
import MainHeader from './header/MainHeader';
import MainMenu from './MainMenu';
const { Sider, Content } = Layout;
function Home() {
  return (
    <Layout className="home"
        style={{ minHeight: "100vh"}}>
          <MainHeader />
          <Layout>
            <Sider
              className='left'
              width={300}
            >
              <MainMenu />
            </Sider>
            <Content
              className='right'
            >
                <Outlet/>
            </Content>
          </Layout>
    </Layout>
  )
}

export default Home