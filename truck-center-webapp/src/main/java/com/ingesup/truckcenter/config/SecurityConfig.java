package com.ingesup.truckcenter.config;

import com.ingesup.truckcenter.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Configuration
	@Order (2)
	public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserServiceImpl userServiceImpl;

		@Override
		public UserDetailsService userDetailsServiceBean() throws Exception {
			return userServiceImpl;
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsServiceBean())
					.passwordEncoder(passwordEncoderBean());
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/**", "/login", "/logout").permitAll()
					.antMatchers("/admin").hasRole("ADMIN")
					.anyRequest().authenticated()
					.and()
					.exceptionHandling().accessDeniedPage("/access-denied")
					.and()
					.csrf().disable()
					.formLogin()
					.and()
					.logout().logoutUrl("/logout").logoutSuccessUrl("/");
		}

		@Bean
		public PasswordEncoder passwordEncoderBean() {
			return new BCryptPasswordEncoder();
		}
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
					.withUser("admin")
					.password("admin")
					.roles("CLIENT");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.antMatcher("/rest/**")
					.authorizeRequests()
					.anyRequest().hasAnyRole("CLIENT")
					.and()
					.httpBasic().authenticationEntryPoint(authenticationEntryPoint());
		}

		private AuthenticationEntryPoint authenticationEntryPoint() {
			BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
			entryPoint.setRealmName("Truck Center Authentication Service");

			return entryPoint;
		}
	}
}
