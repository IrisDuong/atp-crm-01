import React,{useState} from 'react'
import { useSelector, useDispatch } from 'react-redux';
import {Drawer,Form, Table, Space, Button, Input, Select, Flex, Typography, Tooltip} from 'antd';
import { MdDownloadDone } from "react-icons/md";
import { RxReset } from "react-icons/rx";
import FloatingLabelInput from '../../../common/FloatingLabelInput';
import {closeModal} from "../../../../store/common/modalSlice"
import { modalStateKey } from '../../../../util/modal.util';
import {langCodeDef} from '../../../../util/app.config';
import { createCommonCode } from '../../../../service/setting/base-data/commonCode.service';
const {Title, Text} = Typography;

function CommonCodeCreation() {
    const dispatch = useDispatch();
    const openState = useSelector(state =>state.modalStore.openState)
    const {languages} = useSelector(state => state.languagesStore)
   const codeTypes = [
          { value: 'M', label: 'Multi' },
          { value: 'S', label: 'Single' }
      ];
      const groupCodes = [
          { value: 'sys', label: 'System' },
          { value: 'mem', label: 'Member' },
          { value: 'inv', label: 'Inventory' },
          { value: 'fin', label: 'Financial' },
          { value: 'prd', label: 'Product' },
      ]
      const useYns = [
          { value: 'Y', label: 'Use' },
          { value: 'N', label: 'Unuse' },
      ]
    const [creationForm,setCreationForm] = useState({
          commonCodeNo : 0,
          groupCodeNo: groupCodes[0].value,
          codeTypeNo: codeTypes[0].value,
          useYnNo: useYns[0].value,
          localeInputCodes : languages?.map(lang=>{
            return {langCode: lang.langCode,codeName: ""};
          })
    })
    /**
     * events
     */
    
    const onClose = ()=> dispatch(closeModal(modalStateKey.COMMON_CODE_CREATION))
    const handleChangeSelectBox = (name,value) =>{
    }
    const hanldeChangeInput = e =>{
      const {name,value} = e.target;
      let localeInputCodes  = creationForm.localeInputCodes
      localeInputCodes.splice(localeInputCodes.findIndex(e=> e.langCode === name),1,{langCode:name,codeName:value});
      // setCreationForm(prev=>{
      //   return{
      //     ...prev,localeInputCodes
      //   }
      // })
    }

    const handleSave = async ()=>{
      console.log(`creationForm`,creationForm);
      var result = await createCommonCode(creationForm);
      if(result){
        
      }
    }
  return (
    <>
    <Drawer
     title="Create Common Code"
     open={openState[modalStateKey.COMMON_CODE_CREATION]}
     onClose={onClose}
     size="large"
    >
      <Flex
          vertical={true}
          gap={35}
      >
          <Form
            name="basic"
            labelCol={{span: 8}}
            wrapperCol={{span: 2}}
            labelAlign="left"
            className='search-form'
            layout='horizontal'
            initialValues={{ layout: 'horizontal' }}
            colon={false}
            onFinish={handleSave}
          >
              <Form.Item label="Group Code">
                <Select
                  name="groupCodeNo"
                  defaultValue={creationForm.groupCodeNo}
                  onChange={(value)=>handleChangeSelectBox("groupCodeNo",value)}
                >
                  {
                    groupCodes?.map(item=>(<Select.Option key={item.value} value={item.value}>{item.label}</Select.Option>))
                  }
                </Select>
              </Form.Item>
              <Form.Item
              label="Common Name"
              rules={[
                {
                validator : (_,value)=>{
                  if(!value?.langCodeDef.VI){
                    return Promise.reject("Tên tiếng Việt là bắt buộc");
                  }
                   return Promise.resolve();
                },
              },
            ]}
              >
                  <Flex vertical gap={10} justify="space-between">
                    <Input
                      name={langCodeDef.EN}
                      placeholder='Input enlish name'
                      onChange={hanldeChangeInput}
                    ></Input>
                    <Input
                      name={langCodeDef.VI}
                      placeholder='Input Vietnamese name'
                      onChange={hanldeChangeInput}
                    ></Input>
                  </Flex>
                {/* <Flex gap={20}>
                  <Flex align='center'>
                    <Text>Common Name</Text>
                  </Flex>
                  <Flex vertical gap={10}>
                    <Input
                      placeholder='Input enlish name'
                    ></Input>
                    <Input
                      placeholder='Input Vietnamese name'
                    ></Input>
                  </Flex>
                </Flex> */}
              </Form.Item>
              <Form.Item label="Code Type">
                <Select
                  name="codeTypeNo"
                  defaultValue={creationForm.codeTypeNo}
                  onChange={(value)=>handleChangeSelectBox("codeTypeNo",value)}
                >
                  {
                    codeTypes?.map(item=>(<Select.Option key={item.value} value={item.value}>{item.label}</Select.Option>))
                  }
                </Select>
              </Form.Item>
              <Form.Item label="Use YN">
                <Select
                  name="useYnNo"
                  defaultValue={creationForm.useYnNo}
                  onChange={(value)=>handleChangeSelectBox("useYnNo",value)}
                >
                  {
                    useYns?.map(item=>(<Select.Option key={item.value} value={item.value}>{item.label}</Select.Option>))
                  }
                </Select>
              </Form.Item>
              <Form.Item label=" ">
                  <Space style={{ marginTop: 16 }}>
                    <Button icon={<MdDownloadDone />} className="save" htmlType="submit">Save</Button>
                    <Button icon={<RxReset/>}>Reset</Button>
                  </Space>
              </Form.Item>
          </Form>
      </Flex>
    </Drawer>
    </>
  )
}

export default CommonCodeCreation