import React from 'react'
import {Link} from "react-router-dom";
import { LuUserCog } from "react-icons/lu";
import { LiaUserFriendsSolid } from "react-icons/lia";
import {CiDeliveryTruck,CiSettings} from "react-icons/ci"
import {TbBuildingWarehouse} from "react-icons/tb"
import { IoBusinessOutline } from "react-icons/io5";
import {Row,Col,Menu} from "antd";
function MainMenu() {
    function getItem(label, key, icon, children, type) {
        return {
          key,
          icon,
          children,
          label,
          type,
        };
      }
    
    function navLinkRendering(path,label,icon,type){
        var navLinkClassName = "";
        if(path && type){
            if(path === "" && type === "group"){
                navLinkClassName = "";
            }else if(path === "" && type === "single"){
                navLinkClassName = "nav-link-disable";
            }
        }
        return (
            <>
                
                <div>
                    <span className="menu-icon">
                        {icon}
                    </span>
                    <span className="menu-label">
                        <Link to={path} className={navLinkClassName}>
                                    {label}
                        </Link>
                    </span>
                </div>
            </>
        )
    }
    
    var menuItems = [
        getItem(navLinkRendering("","Ware House",<TbBuildingWarehouse/>,"group"),"wareHouseMngt",null
                    ,[
                        getItem(navLinkRendering("/product","Product","","single"),"product"),
                        getItem(navLinkRendering("/productCategory","Product Category","","single"),"productCategory"),
                    ]
                    
        ),
        getItem(navLinkRendering("","Business Administration",<IoBusinessOutline/>,"group"),"ba",null
                    ,[
                       
                        getItem(navLinkRendering("/sale","Sales","","single"),"sale"),
                        getItem(navLinkRendering("/accountant","Accountant","","single"),"accountant"),
                        
                        getItem(navLinkRendering("","Partner",null,"group"),"partner",null,
                            [
                                getItem(navLinkRendering("/supplier","Supplier","","single"),"supplier"),
                                getItem(navLinkRendering("/delivery","Delivery","","single"),"delivery"),
                                getItem(navLinkRendering("/salesCollaborator","Cộng Tác Viên Bán Hàng","","single"),"coll")
                            ]
                        )
                    ]
                    
        ),
        getItem(navLinkRendering("/hr","HR",<LuUserCog/>,"single"),"hrMngt",null
                    ,null
                    
        ),
        getItem(navLinkRendering("","Setting",<CiSettings/>,"group"),"settingMngt",null
                    ,[
                        getItem(navLinkRendering("/baseData","Base Data","","single"),"baseData"),
                        getItem(navLinkRendering("/permission","Permission","","single"),"permission")
                    ]
                    
        ),
    ]
  return (
    
        <section className="main-nav">
            <Row>
                <Col span={24}>
                    <Menu
                        mode="inline"
                        theme="light"
                        items={menuItems}
                        style={{borderInlineEnd:"none"}}
                    ></Menu>
                </Col>
            </Row>
        </section>
  )
}

export default MainMenu