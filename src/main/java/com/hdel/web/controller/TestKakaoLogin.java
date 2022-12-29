package com.hdel.web.controller;

//import com.fasterxml.jackson.core.JsonParser;
import com.hdel.web.dto.api.NaverNews;
import com.hdel.web.service.api.GovApiService;
import com.hdel.web.service.common.ApiHttpRequest;
import com.hdel.web.service.common.ConverterUtil;
import org.jooq.tools.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
@RestController
@CrossOrigin
@RequestMapping("/kakaoApi")
public class TestKakaoLogin {
    private String kakaoRestApiKey = "2ebc16cf71ceca131836536e7d42a964";
    public static final Logger logger = LoggerFactory.getLogger(TestKakaoLogin.class);

    @GetMapping("/getAuthCode")
    public ResponseEntity<String> getAuthCode() {
        String accessKeyUrl = "";

        accessKeyUrl += "https://kauth.kakao.com/oauth/authorize?";
        accessKeyUrl += "client_id=" + kakaoRestApiKey;
        accessKeyUrl += "&redirect_uri=http://localhost:18080/kakaoApi/getToken";
        accessKeyUrl += "&response_type=code";

        return new ResponseEntity<String> (accessKeyUrl, HttpStatus.OK);
    }

    @GetMapping("/getToken")
    public String getToken(@RequestParam(value="code")String code) throws UnsupportedEncodingException {
        //사용자 정보 가져오기
        String reqURL = "https://kauth.kakao.com/oauth/token";
        String result = "";
        String userInfoRes = "";

        HttpURLConnection conn = null;
        try {
            URL url = new URL(reqURL);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + kakaoRestApiKey);
            sb.append("&redirect_uri=http://localhost:18080/kakaoApi/getToken");
            sb.append("&code=" + code);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            logger.warn("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            logger.warn("response body : " + result);

            ConverterUtil converterUtil = new ConverterUtil();
            HashMap<String, Object> map = converterUtil.jsonString2Map(result);

            String access_token = String.valueOf(map.get("access_token"));
            logger.warn("access_token : " + access_token);

            /** 사용자 정보 가져오기 **/
            HashMap<String, Object> infoMap = new HashMap<>();
            infoMap = converterUtil.jsonString2Map(getUserInfo(access_token));

            logger.warn("Info Map : " + infoMap);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return "result : " + userInfoRes ;
    }

    /*** 개인정보 Get **/
    public String getUserInfo(String access_token) {
        String result = "";
        HttpURLConnection conn = null;
        try {
            String userInfoURL = "https://kapi.kakao.com/v2/user/me";
            URL url = new URL(userInfoURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_token);

            int userRespCode = conn.getResponseCode();
            logger.warn("userRespCode : " + userRespCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
            return result;
        }
    }

}



