import React,{ useState} from 'react'
import {useDispatch} from "react-redux"
import {Form, Table, Space, Button, Input, Select, Flex, Typography, Tooltip} from 'antd';
import { RiSearch2Line } from "react-icons/ri";
import FloatingLabelInput from '../../../common/FloatingLabelInput';
import { searchListCommonCode } from '../../../../service/setting/setting.service';
import {getListCommonCodes} from "../../../../store/base-data/commonCodeSlice"
function CommonCodeSearch() {
      const dispatch = useDispatch();
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
      const [searchParams, setSearchParams] = useState({
          commonCodeName: '',
          groupCodeNo: groupCodes[0].value,
          codeTypeNo: codeTypes[0].value,
          useYnNo: useYns[0].value
      });
      
      
      /** events */
      const handleChangeSelection = value =>{
          console.log(value);
      }

      const handleSearch = async () => {
          const result = await searchListCommonCode(searchParams);
          
      }
      return (
        <>
          <Form layout="inline" className='search-form'>
              <FloatingLabelInput
                name="groupCodeNo"
                type="select"
                label="Features Group"
                defaultValue={searchParams.groupCodeNo}
                onChange={handleChangeSelection}
                data={groupCodes}
              />
              <FloatingLabelInput
                name="commonCodeName"
                type="input"
                label="Common Code Name"
                defaultValue=""
                onChange={()=>{}}
                placeholder="Input Common Name"
              />
              <FloatingLabelInput
                name="codeTypeNo"
                type="select"
                label="Type"
                defaultValue={searchParams.codeTypeNo}
                onChange={handleChangeSelection}
                data={codeTypes}
              />
              <FloatingLabelInput
                name="useYnNo"
                type="select"
                label="Use Y/N"
                defaultValue={searchParams.useYnNo}
                onChange={handleChangeSelection}
                data={useYns}
              />
            <Form.Item>
              <Button
                icon={<RiSearch2Line />}
                className="search"
                onClick={handleSearch}
                >
                Search
              </Button>
            </Form.Item>
          </Form>
        </>
    )
}

export default CommonCodeSearch