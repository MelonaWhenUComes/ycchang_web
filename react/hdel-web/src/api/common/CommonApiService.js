import axios from "axios";

const COMMON_API_BASE_URL = "http://localhost:18080/common";

class CommonApiService {
    getUsers() {
        return axios.get(COMMON_API_BASE_URL);
    }
}

export default CommonApiService;