


import axios from "axios";

const NAVER_API_BASE_URL = "http://localhost:18080/openApi/naver";
const NAVER_API_CLIENT_ID = "KvsDv5DxEwAf2L8DXwdN";
const NAVER_API_CLIENT_SECRET = "WVY_kT9KeP";
const NAVER_API_NEWS_URI = "https://openapi.naver.com/v1/search/news.json";
class NaverApiService {

    getNaverNews(query) {
        return axios.get(NAVER_API_BASE_URL + "/getNews"+"/" + query);
    }

}

export default new NaverApiService();