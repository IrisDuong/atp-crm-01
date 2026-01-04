import axios from "axios";
import { BE_URI } from "./app.config";

const AXIOS_INSTANCE = axios.create({
    baseURL: BE_URI,
});

export default AXIOS_INSTANCE;