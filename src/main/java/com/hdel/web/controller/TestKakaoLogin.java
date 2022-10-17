package com.hdel.web.controller;

import org.jooq.tools.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/testKaKao")
public class TestKakaoLogin {
    private String kakaoRestApiKey = "2ebc16cf71ceca131836536e7d42a964";

    @GetMapping("/kakaoLogin")
    public String login() {
        StringBuffer url = new StringBuffer();

        url.append("https://kauth.kakao.com/oauth/authorize?");
        url.append("client_id=" + kakaoRestApiKey);
        url.append("&redirect_uri=http://localhost:18080/kakaoLogin/callback");
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



