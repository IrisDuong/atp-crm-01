import React,{useState, useEffect} from 'react';
import {useSelector,useDispatch} from "react-redux";
import {Space, Button, Flex, Typography} from 'antd'
import { MdAddCircle } from "react-icons/md";
// import { TfiSave } from "react-icons/tfi";
import {IoRemoveCircleSharp  } from "react-icons/io5";
import CommonCodeSearch from './CommonCodeSearch';
import CommonCodeTable from './CommonCodeTable';
import CommonCodeCreation from './CommonCodeCreation';
import GeneralCode from '../generalCode/GeneralCode';
import {openModal} from "../../../../store/common/modalSlice";
import { modalStateKey } from '../../../../util/modal.util';

const { Title } = Typography;

function CommonCode() {
    const dispatch = useDispatch();
    const [btnSavingDisabled, setbtnSavingDisabled] = useState(false);
    return (
        <>
            <Flex justify="space-between" align="center" className="mb-4">
                <CommonCodeSearch></CommonCodeSearch>
            </Flex>
            <Flex
                vertical={true}
                gap={25}
            // align="center" className="mb-4"
            >
                <CommonCodeTable/>
            </Flex>
            <Flex justify="space-between" align="center" className="mb-4">
                <Space style={{ marginTop: 16 }}>
                    <Button icon={<MdAddCircle/>} className='new' onClick={()=>dispatch(openModal(modalStateKey.COMMON_CODE_CREATION))}>New</Button>
                    <Button disabled={btnSavingDisabled} icon={<IoRemoveCircleSharp/>} className='delete'>Delete</Button>
                </Space>
            </Flex>
            <GeneralCode></GeneralCode>
            <CommonCodeCreation></CommonCodeCreation>
        </>
  )
}

export default CommonCode