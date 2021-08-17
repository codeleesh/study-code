package com.lovethefeel.springboot2.resolver;

import com.lovethefeel.springboot2.annotation.SocialUser;
import com.lovethefeel.springboot2.domain.User;
import com.lovethefeel.springboot2.domain.enums.SocialType;
import com.lovethefeel.springboot2.repository.UserRepository;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.lovethefeel.springboot2.domain.enums.SocialType.FACEBOOK;
import static com.lovethefeel.springboot2.domain.enums.SocialType.GOOGLE;
import static com.lovethefeel.springboot2.domain.enums.SocialType.KAKAO;

/**
 * 특정 전략을 인터페이스로 만들고 이를 여러 전략 객체로 구현
 * 컨트롤러 메소드에서 특정 조건에 해당하는 파라미터가 있으면 생성한 로직을 처리한 후 해당 파라미터에 바인딩해주는 전략 인터페이스
 * 모든 메소드를 일일이 찾아보면서 파라미터에 바인딩하는 방법보다 훨씬 빠르고 만들기도 편리
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private UserRepository userRepository;

    public UserArgumentResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 파라미터를 지원할지 여부를 반환 true를 반환하면 resolveArgument 메소드가 수행
     * @param parameter
     * @return boolean
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(SocialUser.class) != null && parameter.getParameterType().equals(User.class);
    }

    /**
     * 파라미터의 인자값에 대한 정보를 바탕으로 실제 객체를 생성하여 해당 파라미터 객체에 바인딩
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        User user = (User) session.getAttribute("user");
        return getUser(user, session);
    }

    /**
     * 인증된 User 객체를 만드는 메인 메소드
     * @param user
     * @param session
     * @return
     */
    private User getUser(User user, HttpSession session) {
        if(user == null) {
            try {
                OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                Map<String, Object> map = authentication.getPrincipal().getAttributes();
                User convertUser = convertUser(authentication.getAuthorizedClientRegistrationId(), map);

                user = userRepository.findByEmail(convertUser.getEmail());
                if (user == null) { user = userRepository.save(convertUser); }

                setRoleIfNotSame(user, authentication, map);
                session.setAttribute("user", user);
            } catch (ClassCastException e) {
                return user;
            }
        }
        return user;
    }

    /**
     * 사용자의 인증된 소셜 미디어 타입에 따라 빌더를 사용하여 User 객체를 만들어 주는 가교 역할
     * 카카오의 경우에는 별도의 메소드를 사용
     * @param authority
     * @param map
     * @return
     */
    private User convertUser(String authority, Map<String, Object> map) {
        if(FACEBOOK.isEquals(authority)) return getModernUser(FACEBOOK, map);
        else if(GOOGLE.isEquals(authority)) return getModernUser(GOOGLE, map);
        else if(KAKAO.isEquals(authority)) return getKaKaoUser(map);
        return null;
    }

    /**
     * 페이스북이나 구글과 같이 공통되는 명명규칙을 가진 그룹을 User 객체로 매핑
     * @param socialType
     * @param map
     * @return
     */
    private User getModernUser(SocialType socialType, Map<String, Object> map) {
        return User.builder()
                .name(String.valueOf(map.get("name")))
                .email(String.valueOf(map.get("email")))
                .principal(String.valueOf(map.get("id")))
                .socialType(socialType)
                .createdDate(LocalDateTime.now())
                .build();
    }

    /**
     * (키의 네이밍값이 타 소셜 미디어와 다른) 카카오 회원을 위한 메소드
     * getModernUser() 메소드와 동일하게 User 객체로 매핑
     * @param map
     * @return
     */
    private User getKaKaoUser(Map<String, Object> map) {
        Map<String, String> propertyMap = (HashMap<String, String>) map.get("properties");
        return User.builder()
                .name(propertyMap.get("nickname"))
                .email(String.valueOf(map.get("kaccount_email")))
                .principal(String.valueOf(map.get("id")))
                .socialType(KAKAO)
                .createdDate(LocalDateTime.now())
                .build();
    }

    /**
     * 인증된 authentication이 권한을 갖고 있는지 체크하는 용도로 쓰임
     * 만약 저장된 User 권한이 없으면 SecurityContexHolder를 사용하여 해당 소셜 미디어 타입으로 권한을 저장
     * @param user
     * @param authentication
     * @param map
     */
    private void setRoleIfNotSame(User user, OAuth2AuthenticationToken authentication, Map<String, Object> map) {
        if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority(user.getSocialType().getRoleType()))) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(map, "N/A", AuthorityUtils.createAuthorityList(user.getSocialType().getRoleType())));
        }
    }
}

