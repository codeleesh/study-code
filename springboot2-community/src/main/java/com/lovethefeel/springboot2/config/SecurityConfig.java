package com.lovethefeel.springboot2.config;

import com.lovethefeel.springboot2.config.oauth2.CustomOAuth2Provider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.lovethefeel.springboot2.domain.enums.SocialType.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http
                .authorizeRequests()
                .antMatchers("/", "/oauth2/**", "/login/**", "/css/**", "/images/**", "/js/**", "/console/**").permitAll()
                .antMatchers("/facebook").hasAuthority(FACEBOOK.getRoleType())
                .antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
                .antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailure")
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and()
                .formLogin()
                .successForwardUrl("/board/list")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .addFilterBefore(filter, CsrfFilter.class)
                .csrf().disable();
    }

    /**
     * 카카오 클라이언트 ID를 불러옴
     * @Configuration으로 등록되어 있는 클래스에서 @Bean으로 등록된 메소드의 파라미터로 지정된 객체들은 오토와이어링(autowiring)할 수 있음
     * OAuth2ClientProperties에는 구글과 페이스북의 정보가 들어 있고 카카오는 따로 등록했기 때문에 @Value 어노테이션을 사용하여 수동으로 불러옴
     * getRegistration 메소드를 사용해 구글과 페이스북의 인증 정보를 빌드
     */
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties oAuth2ClientProperties
            , @Value("${custom.oauth2.kakao.client-id}") String kakaoClientId) {
        List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
                .map(client -> getRegistration(oAuth2ClientProperties, client))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
                .clientId(kakaoClientId)
                .clientSecret("test") //필요없는 값인데 null이면 실행이 안되도록 설정되어 있음
                .jwkSetUri("test") //필요없는 값인데 null이면 실행이 안되도록 설정되어 있음
                .build());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
        if ("google".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .scope("email", "profile")
                    .build();
        }
        if ("facebook".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("facebook");
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .userInfoUri("https://graph.facebook.com/me?fields=id,name,email,link")
                    .scope("email")
                    .build();
        }
        return null;
    }
}
