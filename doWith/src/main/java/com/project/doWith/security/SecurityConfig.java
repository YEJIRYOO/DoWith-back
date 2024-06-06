package com.project.doWith.security;

import com.project.doWith.security.login.JwtAuthenticationFilter;
import com.project.doWith.security.login.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // ID, Password 문자열을 Base64로 인코딩하여 전달하는 구조
                .httpBasic(AbstractHttpConfigurer::disable)
                // 쿠키 기반이 아닌 JWT 기반이므로 사용하지 않음
                .csrf(AbstractHttpConfigurer::disable)
                // Spring Security 세션 정책 : 세션을 생성 및 사용하지 않음
                .sessionManagement((sessionManagement)->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(requests -> requests
                        // 회원가입과 로그인은 모두 승인
                        .requestMatchers("/login", "/register", "/main/**").permitAll()
                        //admin으로 시작하는 요청은 ADMIN 권한이 있는 유저에게만 허용
                        .requestMatchers("/admin").hasRole("ADMIN")
                        //user 로 시작하는 요청은 USER 권한이 있는 유저에게만 허용
                        .requestMatchers("/**").hasRole("USER")
                        .anyRequest().denyAll()
                );
        return http.build();

        /*
        http
                // JWT 인증 필터 적용, 인증 처리 기본필터 username~
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        //권한 문제가 발생 시 이부분 호출
                        response.setStatus(403);
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write("권한이 없는 사용자입니다.");
                    }
                })

        return http.build();

         */
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //{noop}asd34d*^ 과 같이 password 앞에 encoding 방식 붙은 채로 저장-> 엄호화 방식 지정 저장 가능
    }
}