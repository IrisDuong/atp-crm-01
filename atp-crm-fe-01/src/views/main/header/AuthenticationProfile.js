import React from 'react';
import {useSelector} from "react-redux" 
import { Space, Avatar ,Dropdown, Typography,message} from 'antd';
import { CiLogout } from "react-icons/ci";
import { MdContactSupport } from "react-icons/md";
import { IoIosArrowDown } from "react-icons/io";
import { FaUserGear } from "react-icons/fa6";
import { IoLogOutSharp } from "react-icons/io5";
import {doLogout} from "../../../service/auth/auth.service";
const {Text} = Typography
function AuthenticationProfile() {
    const {authenticationInfo} = useSelector(state=> state.authenStore);
    const authenUserDropdowns = [
        {
            key:"profile",
            label:"Hồ sơ",
            icon:<FaUserGear/>
        },
        {
            key:"support",
            label:"Trợ giúp",
            icon:<MdContactSupport/>
        },
        {
            key:"logout",
            label:"Đăng xuất",
            icon:<IoLogOutSharp/>
        }
    ]

    /**
     * events
     */
    
    const handleChangeActions = ({key}) =>{
         switch(key){
            case "logout":
                doLogout();
                break;
            case "support":
                console.log("Hỗ trợ");
                break;
         }
    }
  return (
    <div className="authen-profile-container">
        <div>
            <Text strong>{authenticationInfo && authenticationInfo.name ? authenticationInfo.name : "Undefined"}</Text>
            <Dropdown menu={{items:authenUserDropdowns,onClick:handleChangeActions}}>
                <IoIosArrowDown/>
            </Dropdown>
            <Avatar>{authenticationInfo && authenticationInfo.picture ? authenticationInfo.picture : authenticationInfo.name.substring(0, 1)}</Avatar>
        </div>
    </div>
  )
}

export default AuthenticationProfile