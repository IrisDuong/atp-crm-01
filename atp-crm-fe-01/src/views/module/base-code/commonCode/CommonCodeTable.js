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
    const [commonCodeData, setCommonCodeData] = useState([]);
    
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
        var editableList = commonCodeData.map(item=> {
            if(item.key == record.key && item.editable != mode) item.editable = mode;
            return item;
        })
       setCommonCodeData(editableList)
    }

    const handleViewGeneralCodes = record =>{
        dispatch(openModal(modalStateKey.GENERAL_CODE_LIST));
    }
    useEffect(()=>{
        // const listCommonCodes = Array.from({length:15}).map((_,i)=>{
        //     return {
        //                 key:`${i}`,
        //                 commonCodeName: "",
        //                 commonCodeNo: `${i} - commonCodeNo`,
        //                 codeTypeName: `${i} - codeTypeName`,
        //                 useYnName: `${i} - useYnName`,
        //                 groupCodeNo: `INV`,
        //                 codeTypeNo:  `M`,
        //                 useYnNo:  `N`,
        //                 editable : false,
        //                 localeInputCodes:[
        //                     {
        //                         "langCode":"en",
        //                         "codeName":`${i} - commonCodeName`
        //                     },
        //                     {
        //                         "langCode":"en",
        //                         "codeName":`${i} - mã`
        //                     }
        //                 ]
        //     }
        // });
        if(listCommonCodes && listCommonCodes.length > 0){
            const newCommonCodes = listCommonCodes && listCommonCodes.map(item=>{
                const codeNames = item.localeInputCodes.filter(locale=> locale.langCode === CURRENT_LANG_CODE);
                return {
                        key:  item.key,
                        commonCodeName: codeNames && codeNames.length > 0 ? codeNames[0].codeName : "",
                        commonCodeNo: item.commonCodeNo,
                        codeTypeName: item.codeTypeName,
                        useYnName: item.useYnName,
                        groupCodeNo: item.groupCodeNo,
                        codeTypeNo: item.codeTypeNo,
                        useYnNo: item.useYnNo,
                        editable : false
            }})
            setCommonCodeData(newCommonCodes);
        }
    },[])
    
        // const handleChangePagination = (page, pageSize) => {
        //     console.log('Page:', page, 'PageSize:', pageSize);
        //     // Implement pagination logic here if needed
        // }
  return (
    <>
        <Table 
            columns={cols} 
            dataSource={commonCodeData}
            pagination={{ pageSize: 5}}
            rowKey="key"
            bordered
        />
    </>
  )
}

export default CommonCodeTable