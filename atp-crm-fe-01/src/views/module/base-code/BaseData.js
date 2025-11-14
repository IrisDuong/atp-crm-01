import React, {useState} from 'react'
import { Tabs , Typography, Flex, Row, Col, Select} from 'antd';
import CommonCode from './commonCode/CommonCode';
import GeneralCode from './generalCode/GeneralCode'
import { groupCodesBox} from '../../../store/sample-data/base-data';
const { Title } = Typography;
function BaseData() {
    const [selectedGroupCode, setSelectedGroupCode] = React.useState(groupCodesBox[0].codeNo);

    const baseDataTabs = [
      { key: 'commonCode', label: 'Common Code', content: <CommonCode /> },
      { key: 'generalCode', label: 'General Code', content: <GeneralCode /> },
    ];
    const handleGroupCodeChange = (value) => {
        setSelectedGroupCode(value);
        // Handle the change in group code selection
    }
  return (
    
    <Flex gap="middle" vertical>
        <Tabs
          type="editable-card"
          items={baseDataTabs}
        >

        </Tabs>
        <Flex 
            vertical={true}
            gap={10}
            justify='space-between'
         >
            <CommonCode></CommonCode>
        </Flex>
    </Flex>
  )
}

export default BaseData