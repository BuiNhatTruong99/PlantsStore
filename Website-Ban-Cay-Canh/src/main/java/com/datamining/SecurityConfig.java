package com.datamining;

import java.text.Collator;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.datamining.entity.Account;
import com.datamining.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    BCryptPasswordEncoder pe;

    @Autowired
    AccountService accountService;

    @Bean
    public static BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
				try {
					Account acc = accountService.findByTk(username);
					String password = pe.encode(acc.getPassword());
					String[] roles = acc.getAuthorities().stream()
							.map(rl -> rl.getRole().getName())
							.collect(Collectors.toList()).toArray(new String[0])
							;
//					String value = Arrays.toString(roles);
//					System.out.println(value);
					
					return User.withUsername(username).password(password).roles(roles).build();
				}catch (Exception e)
				{
					throw new UsernameNotFoundException(username + "not found");
				}
		});
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/admin").hasAnyRole("STAFF","Director")
			.antMatchers("/admin/**").hasAnyRole("STAFF","Director")
			.antMatchers("/api/**").permitAll()
			.anyRequest().permitAll();

		
		http.formLogin()
				.loginPage("/login/form")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/login/success",false)
				.failureUrl("/login/error");
		
		http.logout()
		.logoutUrl("/security/logoff")
		.logoutSuccessUrl("/logoff/success");
		
		http.exceptionHandling()
		.accessDeniedPage("/login/unauthoried");

		http.oauth2Login()
				.loginPage("/login/form")
				.defaultSuccessUrl("/oauth2/login/success",true)
				.failureUrl("/login/error")
				.authorizationEndpoint()
				.baseUri("/oauth2/authorization");
	}
}
