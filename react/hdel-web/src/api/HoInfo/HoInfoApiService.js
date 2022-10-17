import axios from "axios";

const COMMON_API_BASE_URL = "http://localhost:18080/hoInfo";

class HoInfoApiService {

    getHoInfo() {
        return axios.get(COMMON_API_BASE_URL + "/getHoInfoAll");
    }

}

export default new HoInfoApiService();