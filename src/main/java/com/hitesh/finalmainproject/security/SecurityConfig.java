package com.hitesh.finalmainproject.security;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hitesh.finalmainproject.models.AdminDetails;
import com.hitesh.finalmainproject.service.AdminService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired AdminService adminServive;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	
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
    
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
    	
    	List<AdminDetails> admins = adminServive.getAll();
    	
    	List<SimpleGrantedAuthority> ls = new LinkedList<>();
    	ls.add(new SimpleGrantedAuthority("ADMIN"));
    	
    	Collection<UserDetails> cls = new LinkedList<>();
    	
    	admins.stream().forEach(admin ->cls.add(User
    			.builder()
    			.username(admin.getUsername())
    			.password(passwordEncoder.encode(admin.getPassword()))
    			.roles("ADMIN")
    			.authorities(ls)
    			.build()));
    	
    	return new InMemoryUserDetailsManager(cls);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	
    	web.ignoring().antMatchers("/resources/**","/static/**","/css/**","/images/**");
    }
    
}