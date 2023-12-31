package com.middterm.project.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private AuthenticationProvider authenticationProvider;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // disable csrf
        http.csrf(AbstractHttpConfigurer::disable);
        // add custom filter
        // authenticationProvider provide :
        // 1.UserDetailsService to load user by username
        // 2.PasswordEncoder to check if password is correct compare to the password in database
        http.authenticationProvider(authenticationProvider);
        // authorize requests
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/cart/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
        );
        http.formLogin(form -> form
                // set login url
                .loginPage("/account/login")
                // form parameter name for email
                .usernameParameter("email")
                // form parameter name for password
                .passwordParameter("password")
                // add cookie after login success and redirect to home page after login success
                // we don't use default login page of http config because it will redirect to specific url before the cookie is added
                .successHandler(authenticationSuccessHandler)
                .failureHandler((request, response, exception) -> {
                    request.getSession().setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
                    response.sendRedirect("/account/login?error=true");
                })
        );

        // set session creation policy to IF_REQUIRED
        // however, if the user is logged in, then Spring Security will use the existing session to store their authentication information
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
        );

        http.rememberMe(rememberMe -> {
            // set remember me parameter name
            rememberMe.rememberMeParameter("remember-me");
            // set token valid for 30 days
            rememberMe.tokenValiditySeconds(2592000);
            // set key to encode remember me cookie
            rememberMe.key("remembermekey");
        });


        return http.build();
    }

}

