package edu.mum.onlineshopping.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Value("${onlineshopping.person-auth-query}")
	private String personAuthQuery;
	@Value("${onlineshopping.person-author-query}")
	private String personAuthorQuery;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home", "/index", "/modules/**",
                				"/css/**", "/images/**", "/product/**", "/user/login",
                				"/cart/", "/cart/add/product/*", "/me/account/signup",
                				"/cart/update", "/cart/remove/product/*", "/cart/add/product/*",
                				"/api/**").permitAll()
                .antMatchers("/cart/submit").hasAnyRole("USER", "ADMIN")
                .antMatchers("/me/order", "me/account/update").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin().loginPage("/user/login")
            	.permitAll()
            	.usernameParameter("email")
            	.passwordParameter("password")
            	.defaultSuccessUrl("/")
            	.and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            	.invalidateHttpSession(true)
             .clearAuthentication(true)
            	.logoutSuccessUrl("/")
                .permitAll();
        http.csrf().disable();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(personAuthQuery)
								.passwordEncoder(passwordEncoder())
								.authoritiesByUsernameQuery(personAuthorQuery)
								.dataSource(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}