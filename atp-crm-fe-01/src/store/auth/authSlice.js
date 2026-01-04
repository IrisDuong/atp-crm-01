import { createSlice } from "@reduxjs/toolkit";
const authenSlice = createSlice({
    name: "authen",
    initialState:{
        authenticationInfo : null
    },
    reducers:{
        setAuthenticationInfo: (state,action)=>{
            state.authenticationInfo = action.payload;
        }
    }
})

export const {setAuthenticationInfo} = authenSlice.actions;
export default authenSlice.reducer;