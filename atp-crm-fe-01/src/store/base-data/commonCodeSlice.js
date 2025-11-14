import { createSlice } from "@reduxjs/toolkit";

const commonCodeSlice = createSlice({
    name : "commonCode",
    initialState:{
        listCommonCodes : []
    },
    reducers:{
        getListCommonCodes : (state,action)=>{
            state.listCommonCodes = action.payload;
        }
    }
})

export const {getListCommonCodes} = commonCodeSlice.actions;
export default commonCodeSlice.reducer;