import React,{useState, useEffect} from 'react';
import {useSelector,useDispatch} from "react-redux";
import { Table,Flex, Typography, Tooltip, Input, Button, Select, Space} from 'antd'
import { MdEdit } from "react-icons/md";
import { IoEye,IoRemoveCircleSharp  } from "react-icons/io5";
import { openModal } from '../../../../store/common/modalSlice';
import { modalStateKey } from '../../../../util/modal.util';

const CURRENT_LANG_CODE = "vi";

function CommonCodeTable() {
    
    const dispatch = useDispatch();
    const {listCommonCodes} = useSelector(state=> state.commonCodeStore);
    const [commonCodeTbData, setCommonCodeTbData] = useState([]);
    
    const [codeTypeSelection, setCodeTypeSelection] = useState([
        { value: 'M', label: 'Multi' },
        { value: 'S', label: 'Single' }
    ]);

    const [useYnSelection, setUseYnSelection] = useState([
        { value: 'Y', label: 'Yes' },   
        { value: 'N', label: 'No' }
    ]); 
    const cols =[
        {
            title: 'COMMON CODE',
            dataIndex: 'commonCodeNo',
            key: 'commonCodeNo',
        },
        {
            title: 'NAME',
            dataIndex: 'commonCodeName',
            key: 'commonCodeName',
            render: (text, record) => {
                if(record.editable) {
                    return <Input 
                        type="text" 
                        defaultValue={text} 
                        onChange={(e) => {
                            record.commonCodeName = e.target.value;
                        }} 
                    />;
                }
                return (
                    <span style={{ color: record.useYn === 'N' ? 'red' : 'black' }}>
                        {text}
                    </span>
                );
            }

        },,
        {
            title: 'CODE TYPE',
            dataIndex: 'codeTypeName',
            key: 'codeTypeName',
            render: (text, record) => {
                if(record.editable) {
                    return (
                        <Select 
                            defaultValue={text} 
                            onChange={(value) => {
                                record.codeType = value;
                            }}
                        >
                            {codeTypeSelection.map((item) => (
                                <Select.Option key={item.value} value={item.value}>
                                    {item.label}
                                </Select.Option>
                            ))}
                        </Select>
                    );
                }
                return <span>{text === 'M'? 'Multi':'Single'}</span>;
            }
                
        },
        {
            title: 'USE (Y/N)',
            dataIndex: 'useYnName',
            key: 'useYnName',
            render: (text, record) => {
                if(record.editable) {
                    return (
                        <Select 
                            defaultValue={text} 
                            onChange={(value) => {
                                record.useYn = value;
                            }}
                        >
                            {useYnSelection.map((item) => (
                                <Select.Option key={item.value} value={item.value}>
                                    {item.label}
                                </Select.Option>
                            ))}
                        </Select>
                    );
                }
                return (
                    <span style={{ color: text === 'N' ? 'red' : 'black' }}>
                        {text === 'Y' ? 'Yes' : 'No'}
                    </span>
                );
            }
        },
        {
            title: '',
            dataIndex: '',
            key: 'action',
            render: (record)=> {
                return (
                    <Space size="middle">
                        <Tooltip title="View detail" placement='bottom'>
                            <Button 
                                icon={<IoEye/>} 
                                className='tb-row-btn view'
                                onClick={() => {
                                    handleViewGeneralCodes(record);
                                }}
                            >
                            </Button>
                        </Tooltip>
                        <Tooltip title="Edit" placement='bottom'>
                            <Button 
                                icon={<MdEdit/>} 
                                className='tb-row-btn edit'
                                onClick={() => {
                                    handleChangeDisplayMode(record,true);
                                }}
                            >
                            </Button>
                        </Tooltip>
                        <Tooltip title="Delete" placement='bottom'>
                            <Button 
                                icon={<IoRemoveCircleSharp/>} 
                                className='tb-row-btn del'
                                onClick={() => {
                                    // Handle delete action
                                    console.log('Delete', record);
                                }}
                            >
                            </Button>
                        </Tooltip>
                    </Space>
                )
            }
        }
    ]
    
    
    /** events */
    const handleChangeDisplayMode = (record,mode) => {
        var editableList = commonCodeTbData.map(item=> {
            if(item.key == record.key && item.editable != mode) item.editable = mode;
            return item;
        })
       setCommonCodeTbData(editableList)
    }

    const handleViewGeneralCodes = record =>{
        dispatch(openModal(modalStateKey.GENERAL_CODE_LIST));
    }
    useEffect(()=>{
        if(listCommonCodes && listCommonCodes.length > 0){
            let _listCommonCodes = listCommonCodes.map(e=>{
                let localeInputCodes = e.localeInputCodes.filter(k=> k.langCode === CURRENT_LANG_CODE);
                return {
                    commonCodeNo: e.commonCodeNo,
                    featureCodeNo: e.featureCodeNo,
                    codeTypeNo: e.codeType.codeTypeNo,
                    codeTypeValue: e.codeType.codeTypeValue,
                    useStatusNo: e.useStatus.useStatusNo,
                    useStatusValue: e.useStatus.useStatusValue,
                    commonCodeName: localeInputCodes && localeInputCodes.length > 0 ? localeInputCodes[0].codeName:""
                }
            
            })
        setCommonCodeTbData(_listCommonCodes);
        }
    },[listCommonCodes])
    
        // const handleChangePagination = (page, pageSize) => {
        //     console.log('Page:', page, 'PageSize:', pageSize);
        //     // Implement pagination logic here if needed
        // }
  return (
    <>
        <Table 
            columns={cols} 
            dataSource={commonCodeTbData}
            pagination={{ pageSize: 5}}
            rowKey="key"
            bordered
        />
    </>
  )
}

export default CommonCodeTable