//package cz.siemens.inventory.configuration;
//
//import cz.siemens.inventory.controllers.ApiUris;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////	@Autowired
////	private AuthenticationEntryPoint authenticationEntryPoint;
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().anyRequest().authenticated()
//				.and().httpBasic().and().logout();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		//todo NOT A GOOD IDEA HOWEVER I DON'T KNOW HOW PASSWORDS ARE STORED
//		return new PasswordEncoder() {
//			@Override
//			public String encode(CharSequence charSequence) {
//				return charSequence.toString();
//			}
//
//			@Override
//			public boolean matches(CharSequence charSequence, String s) {
//				return s.equals(charSequence.toString());
//			}
//		};
//	}
//}
