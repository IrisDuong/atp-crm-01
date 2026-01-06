import { createSlice } from "@reduxjs/toolkit";

const commonCodeSlice = createSlice({
    name : "commonCode",
    initialState:{
        listCommonCodes : []
    },
    reducers:{
        storeListCommonCodes : (state,action)=>{
            state.listCommonCodes = action.payload;
        }
    }
})

export const {storeListCommonCodes} = commonCodeSlice.actions;
export default commonCodeSlice.reducer;