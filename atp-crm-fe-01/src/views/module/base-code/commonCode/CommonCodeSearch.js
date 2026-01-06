import React,{ useState} from 'react'
import {useDispatch} from "react-redux"
import {Form, Table, Space, Button, Input, Select, Flex, Typography, Tooltip} from 'antd';
import { RiSearch2Line } from "react-icons/ri";
import FloatingLabelInput from '../../../common/FloatingLabelInput';
import { searchListCommonCode } from '../../../../service/setting/base-data/commonCode.service';
import {storeListCommonCodes} from "../../../../store/base-data/commonCodeSlice"
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
          featureCodeNo: groupCodes[0].value,
          codeTypeNo: codeTypes[0].value,
          useStatusNo: useYns[0].value
      });
      
      
      /** events */
      const handleFormChange = feEl =>{
          const {feName,feValue} = feEl;
          setSearchParams(prevState=>{
            return {
              ...prevState,[feName]:feValue
            }
          })
      }

      const handleSearch = async () => {
        console.log("param search common code");
        console.log(searchParams);
          const result = await searchListCommonCode(searchParams);
          if(result)
            dispatch(storeListCommonCodes(result));
      }
      return (
        <>
          <Form layout="inline" className='search-form'>
              <FloatingLabelInput
                name="featureCodeNo"
                type="select"
                label="Features Group"
                defaultValue={searchParams.featureCodeNo}
                onChange={handleFormChange}
                data={groupCodes}
              />
              <FloatingLabelInput
                name="commonCodeName"
                type="input"
                label="Common Code Name"
                value={searchParams.commonCodeName}
                onChange={handleFormChange}
                placeholder="Input Common Name"
              />
              <FloatingLabelInput
                name="codeTypeNo"
                type="select"
                label="Type"
                defaultValue={searchParams.codeTypeNo}
                onChange={handleFormChange}
                data={codeTypes}
              />
              <FloatingLabelInput
                name="useStatusNo"
                type="select"
                label="Use Y/N"
                defaultValue={searchParams.useStatusNo}
                onChange={handleFormChange}
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