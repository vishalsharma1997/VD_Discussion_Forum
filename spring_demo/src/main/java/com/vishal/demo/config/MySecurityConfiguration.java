package com.vishal.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vishal.demo.repositories.UserRepository;
import com.vishal.demo.services.MyUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(new MyUserDetailsServiceImpl(userRepository))
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .authorizeRequests()
                    .antMatchers("/user/save")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers("/user/**")
                    .hasRole("USER")
                .and()
                    .authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
                    .deleteCookies("XSRF-TOKEN")
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
                    .rememberMe().rememberMeParameter("remember-me")
                .and()
                    .httpBasic()
                    .authenticationEntryPoint(new AuthenticationEntryPoint() {
						
						@Override
						public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
							if(request.getHeader("X-Requested-With") == null){
                                response.sendRedirect("/pageNotFound");
                            }
						}
					})
                .and()
                    .csrf().disable();
//                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }
}
