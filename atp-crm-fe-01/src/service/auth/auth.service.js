import axios from "axios";
import {GATEWAY_URI,SSO_AUTHEN_URL} from "../../util/app.config"
export const getAuthentication = async () => {
    try {
        // const authenInfo = await axios.post(`${SSO_AUTHEN_URL}/authen/info`, {withCredentials: true});
        const response = await axios.post(`${GATEWAY_URI}/auth-mgt/authen/info`,{withCredentials: true});
        return response.data
    } catch (error) {
        return null;
    }
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