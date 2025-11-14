import React,{useState, useEffect} from 'react'
import { Table, Modal, Button, Flex, Space, List, Typography} from 'antd';
import { MdOutlineAdd } from "react-icons/md";
import { LuTrash2 } from "react-icons/lu";
import { useSelector, useDispatch } from 'react-redux';
import { closeModal } from '../../../../store/common/modalSlice';
import { modalStateKey } from '../../../../util/modal.util';

function GeneralCode(props) {
    const dispatch = useDispatch();
    const openState = useSelector(state=>state.modalStore.openState) 
    const cols =[
        {
            title: 'GENERAL CODE',
            dataIndex: 'generalCodeNo',
            key: 'generalCodeNo',
        },
        {
            title: 'NAME',
            dataIndex: 'generalCodeName',
            key: 'generalCodeName',
            render: (text, record) => {
                return (
                    <List
                        dataSource={record.generalCodeName}
                        renderItem={item=>(
                            <List.Item>
                                <Typography.Text mark>{item.langCode}</Typography.Text> {item.codeName}
                            </List.Item>
                        )}
                    >

                    </List>
                )
            }
        },
        {
            title: 'USE (Y/N)',
            dataIndex: 'useYn',
            key: 'useYn',
        }
    ]

    const generalCodeData = [
        {
            key: '1',
            generalCodeNo: '01',
            generalCodeName: [{langCode:"en",codeName:'In-Stocks'},{langCode:"vi",codeName:'Còn Hàng'}
            ],
            useYn: 'Y'
        },
        {
            key: '2',
            generalCodeNo: '02',
            generalCodeName: [{langCode:"en",codeName:'Wait To Export'},{langCode:"vi",codeName:'Chờ Xuất Hàng'}],
            useYn: 'Y'
        },
        {
            key: '3',
            generalCodeNo: '03',
            generalCodeName: [{langCode:"en",codeName:'Exported'},{langCode:"vi",codeName:'Đã Xuất Hàng'}],
            useYn: 'Y'
        },
        {
            key: '4',
            generalCodeNo: '04',
            generalCodeName: [{langCode:"en",codeName:'Broken'},{langCode:"vi",codeName:'Hàng Lỗi'}],
            useYn: 'Y'
        },
        {
            key: '5',
            generalCodeNo: '05',
            generalCodeName: [{langCode:"en",codeName:'Return'},{langCode:"vi",codeName:'Trả Hàng'}],
            useYn: 'Y'
        }
    ]
    /** events */
    const handleCancel = () => handleClose();
    const handleOk = () => handleClose();

    const handleClose = () => {
        dispatch(closeModal(modalStateKey.GENERAL_CODE_LIST));
    }
  return (
    <>
    <Modal
        title="General Code"
        open={openState[modalStateKey.GENERAL_CODE_LIST] && openState[modalStateKey.GENERAL_CODE_LIST]}
        onOk={handleOk}
        onCancel={handleCancel}
    >
        <div>
            <Table 
                columns={cols}
                dataSource={generalCodeData}
                pagination={false}
                bordered
                // size="middle"
                // scroll={{ x: 500 }}
            />
            <Space style={{ marginTop: 16 }}>
                <Button icon={<MdOutlineAdd></MdOutlineAdd>} className='c-ant-btn add'>Add</Button>
                <Button 
                    icon={<LuTrash2></LuTrash2>}
                    onClick={() => {
                        // Handle delete action
                        console.log('Delete Common Code');
                    }}
                >
                </Button>
            </Space>
        </div>
    </Modal>
    </>
  )
}

export default GeneralCode