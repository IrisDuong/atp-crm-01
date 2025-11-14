import { createSlice } from "@reduxjs/toolkit";
import {langCodeDef} from "../../util/app.config"
const languagesSlice = createSlice({
    name:"languagesSlice",
    initialState:{
        languages : [
            {
                langCode : langCodeDef.VI,
                langLabel : "Việt Name"
            },
            {
                langCode : langCodeDef.EN,
                langLabel : "Tiếng Anh"
            }
        ],
        currentLangCode : langCodeDef.VI
    },
    reducers:{
        changeLanguage : (state,action)=>{
            const {currentLangCode} = action.payload;
            state.currentLangCode = currentLangCode;
        }
    }
})

export const {changeLanguage} = languagesSlice.actions;
export default languagesSlice.reducer;