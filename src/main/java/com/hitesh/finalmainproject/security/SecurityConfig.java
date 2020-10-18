package com.hitesh.finalmainproject.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/","/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and().formLogin()
        .loginPage("/login")
    	.permitAll()
    	.defaultSuccessUrl("/contactus",true)
		.and()
		.rememberMe()
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
		.clearAuthentication(true)
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID","remember-me")
		.logoutSuccessUrl("/");
    }
    
}