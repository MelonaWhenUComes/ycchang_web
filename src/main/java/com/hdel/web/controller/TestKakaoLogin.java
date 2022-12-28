package com.hdel.web.controller;

//import com.fasterxml.jackson.core.JsonParser;
import com.hdel.web.dto.api.NaverNews;
import com.hdel.web.service.common.ApiHttpRequest;
import org.jooq.tools.json.JSONObject;
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

    @GetMapping("/getAccessKey")
    public ResponseEntity<String> getAccessKey() {
        String accessKeyUrl = "";

        accessKeyUrl += "https://kauth.kakao.com/oauth/authorize?";
        accessKeyUrl += "client_id=" + kakaoRestApiKey;
        accessKeyUrl += "&redirect_uri=http://localhost:18080/kakaoApi/getToken";
        accessKeyUrl += "&response_type=code";

        return new ResponseEntity<String> (accessKeyUrl, HttpStatus.OK);
    }

    @GetMapping("/getToken")
    public String getToken(@RequestParam(value="code")String token) {
        //사용자 정보 가져오기
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
/*
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();

            userInfo.put("nickname", nickname);
            userInfo.put("email", email);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "token : " + token ;
    }

    @GetMapping("/kakaoLogin")
    public String login() {
        StringBuffer url = new StringBuffer();

        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + kakaoRestApiKey);
        //url.append("&redirect_uri=http://localhost:18080/kakaoLogin/callback");
        url.append("&redirect_uri=http://localhost:18080/testKaKao/oauth_kakao");
        url.append("&response_type=code");

        return "redirect:" + url;
    }


    @RequestMapping(value = "/callback", produces = "application/json", method = {RequestMethod.GET,
            RequestMethod.POST})
    public void kakaoLoginCallback(@RequestParam("code") String code,
                           HttpSession session) throws IOException {

        String accessToken = getKakaoAccessToken(code);
        session.setAttribute("access_token", accessToken); // 로그아웃할 때 사용된다
    }

    //https://codebibimppap.tistory.com/m/11
    public String getKakaoAccessToken(String code) {
        // 카카오에 보낼 api
        /*WebClient webClient = WebClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        // 카카오 서버에 요청 보내기 & 응답 받기
        JSONObject response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", SecretKey.KAKAO_API_KEY)
                        .queryParam("redirect_uri", Kakao.DOMAIN_URI + "/api/kakao/callback")
                        .queryParam("code", code)
                        .build())
                .retrieve().bodyToMono(JSONObject.class).block();
        return (String) response.get("access_token");
        */
        return "";
    }

}



