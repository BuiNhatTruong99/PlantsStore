package com.datamining;

import java.text.Collator;
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
//        auth.userDetailsService(username -> {
//
//            try {
//                Account acc = accountService.findByTk(username);
//                String password = pe.encode(acc.getPassword());
//                Integer[] roles = acc.getAuthorities().stream()
//                        .map(rl -> rl.getRole().getId())
//                        .collect(Collectors.toList()).toArray(new Integer[0])
//                        ;
//
//                return User.withUsername(username).password(password).roles(String.valueOf(roles)).build();
//            }catch (Exception e)
//            {
//                throw new UsernameNotFoundException(username + "not found");
//            }
//        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

//		http.authorizeHttpRequests()
//		.antMatchers("/admin/**").hasAuthority("1")
//		.antMatchers("/admin/**").hasAnyRole("1")
//		.anyRequest().permitAll();


        http.formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/login/success",false)
                .failureUrl("/login/error");

        http.exceptionHandling()
                .accessDeniedPage("/login/unauthoried");
    }
}