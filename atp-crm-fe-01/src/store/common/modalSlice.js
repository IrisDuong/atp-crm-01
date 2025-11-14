import { createSlice } from "@reduxjs/toolkit";

const modalSlice = createSlice({
    name : "modal",
    initialState:{
        openState : {
        }
    },
    reducers : {
        openModal: (state,action)=>{
            state.openState[action.payload] = true
        },
        closeModal : (state,action)=>{
            delete state.openState[action.payload]
        }
    }
});
export const {openModal, closeModal} = modalSlice.actions;
export default modalSlice.reducer;