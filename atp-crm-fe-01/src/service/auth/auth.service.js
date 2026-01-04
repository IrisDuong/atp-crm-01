import axios from "axios";
import AXIOS_INSTANCE from "../../util/axios.config"
import {BE_URI,MICRO_SERVICE_PATH} from "../../util/app.config"
const {AUTH_SERVER} = MICRO_SERVICE_PATH;
export const getAuthentication = async () => {
    try {
        const response = await AXIOS_INSTANCE.get(`/oauth2-authen/verify`,{withCredentials: true});
        return response.data;
    } catch (error) {
        return null;
    }
}
export const doAuthentication =  () => {
    window.location.href = `${BE_URI}${AUTH_SERVER}/oauth2/authorization/google`
}

export const doLogout = () =>{
    window.location.href = `${BE_URI}/oauth2-authen/oidcLogout/handle`
}
export const getStorage = (keyName) => {
    const result = sessionStorage.getItem(keyName);
    if(result){
        return JSON.parse(result);
    }
    return null;
}

export const setStorage = (keyName, value) => {
    sessionStorage.setItem(keyName, JSON.stringify(value));
}