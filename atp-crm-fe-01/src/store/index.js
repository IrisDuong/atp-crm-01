import { configureStore } from "@reduxjs/toolkit";
import modalReducer from "./common/modalSlice";
import commonCodeReducer from "./base-data/commonCodeSlice";
import languagesReducer from "./common/languagesSlice";

export const store = configureStore({
    reducer : {
        modalStore: modalReducer,
        commonCodeStore: commonCodeReducer,
        languagesStore: languagesReducer
    }
})