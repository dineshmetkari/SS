package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AuthenticationServiceApplication {

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}
}

//@Configuration
////@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
//class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//      // @formatter:off
//      http
//          .httpBasic()
//          .and()
//          .authorizeRequests()
//          .antMatchers("/register", "/login", "/").permitAll()
//          .anyRequest().authenticated()
//          .and()
//          .csrf().disable();
//      //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//      // @formatter:on
//  }
//
//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//      return super.authenticationManagerBean();
//  }
//}
