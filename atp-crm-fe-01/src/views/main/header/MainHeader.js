
import { Space, Badge, Menu} from 'antd'
import { IoMailUnreadOutline } from "react-icons/io5";
import { RiNotification3Line } from "react-icons/ri";
import { Header } from 'antd/es/layout/layout';
import AuthenticationProfile from "./AuthenticationProfile";
import MultiLanguage from './MultiLanguage';
// import {} from "../../../store/auth/authSlice";
function MainHeader() {
  return (
    <Header  className='main-header'>
        <div>
            ATP
        </div>
        <Space align='right' style={{alignItems:"center"}}>
            <div className="header-icon-container">
                <div>
                    <span>
                        <Badge count={5} size="small">
                            <IoMailUnreadOutline className="header-icon"/>
                        </Badge>
                    </span>
                    <span>
                        <Badge count={5} size="small">
                            <RiNotification3Line className="header-icon"/>
                        </Badge>
                    </span>
                    <span>
                    </span>
                </div>
            </div>
            <MultiLanguage/>
            <AuthenticationProfile/>
        </Space>
    </Header>
  )
}

export default MainHeader