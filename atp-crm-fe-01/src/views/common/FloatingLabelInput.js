import React, { useState } from 'react';
import { Input, Form, Select } from 'antd';

const FloatingLabelInput = ({type,name, label, defaultValue, onChange ,placeholder,data}) => {

    const result =  ()=>{
        switch(type){
          case "select":
            return (
              <Form.Item>
                <label for={name} class="outlined-label">{label}</label>
                <Select
                    onChange={onChange}
                    defaultValue={defaultValue}
                    name={name}
                >
                    {data.map((item) => (
                        <Select.Option key={item.value} value={item.value}>
                            {item.label}
                        </Select.Option>
                    ))}
                </Select>
              </Form.Item>
            );
            break;
          case "input":
            return (
              <Form.Item>
                <label for={name} class="outlined-label">{label}</label>
                <Input
                  name={name}
                  placeholder={placeholder}
                  value={defaultValue}
                  onChange={onChange}
                />
              </Form.Item>
            );
            break;
        }
    }
    return result();
}

export default FloatingLabelInput;