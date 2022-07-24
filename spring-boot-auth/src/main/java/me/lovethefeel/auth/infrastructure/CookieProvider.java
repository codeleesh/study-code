package me.lovethefeel.auth.infrastructure;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;


@Component
public class CookieProvider {

    public Cookie createCookie(String payload) {

        final Cookie cookie = new Cookie("email", payload);
        cookie.setMaxAge(60*60*24); //쿠키 유효 기간: 하루로 설정(60초 * 60분 * 24시간)
        cookie.setPath("/"); //모든 경로에서 접근 가능하도록 설정
        return cookie;
    }
}
