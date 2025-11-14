import axios from "axios";
import { GATEWAY_URI } from "./app.config";

const AXIOS_INSTANCE = axios.create({
    baseURL: GATEWAY_URI,
});

export default AXIOS_INSTANCE;