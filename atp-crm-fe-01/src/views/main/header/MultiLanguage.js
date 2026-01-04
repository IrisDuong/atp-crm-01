import React,{useState} from 'react'
import {Dropdown, Typography} from 'antd'
import { MdOutlineLanguage } from "react-icons/md";
import VietnamIC from "../../../public/images/vn_ic.png";
import EnglishIC from "../../../public/images/en_ic.png";
const {Text} = Typography
function MultiLanguage() {
    
    /**
     * state, const
     */
    const languages = [
        {key:"vi", title : "Tiếng Việt",imgSrc : VietnamIC},
        {key:"en", title : "Tiếng Anh",imgSrc : EnglishIC}
    ]
    const [languageSelection,setLanguageSelection] = useState({
        key: languages[0].key,
        title: languages[0].title,
        imgSrc:  languages[0].imgSrc
    });
    const languageDropdowns = languages.map(lang=>({key:lang.key,label:lang.title}))

    /**
     * events
     */
    const handleChangeLanguage = ({key})=>{
        const selectedLang = languages.filter(lang=> lang.key === key)[0];
        setLanguageSelection(selectedLang);
    }
    return (
            <Dropdown menu={{items:languageDropdowns, onClick:handleChangeLanguage}}>
                <div className='language-selection'>
                    <img src={languageSelection.imgSrc}></img>
                    <Text>{languageSelection.title}</Text>
                </div>
            </Dropdown>
    )
}

export default MultiLanguage