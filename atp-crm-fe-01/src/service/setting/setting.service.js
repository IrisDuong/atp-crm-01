import AXIOS_INSTANCE from "../../util/rest.api";
const { MICRO_SERVICE_PATH } = require("../../util/app.config");
const { SETTING } = MICRO_SERVICE_PATH;

const COMMON_CODE_ENDPOINT_PREFIX = `${SETTING}/base-data/common-code`;

export const searchListCommonCode = async (param) => {
    try {
        const response = await AXIOS_INSTANCE.post(`${COMMON_CODE_ENDPOINT_PREFIX}/search`, param);
        return response;
    } catch (error) {
        return null;
    }
}

export const createCommonCode = async param =>{
    try {
        await AXIOS_INSTANCE.post(`${COMMON_CODE_ENDPOINT_PREFIX}/create`,param);
        return true;
    } catch (error) {
        return false;
    }
}