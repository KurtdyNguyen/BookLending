package lend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	@Autowired
	private final JwtAuthentificationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers(
					"/index",
					"/register_form**",
					"/book",
					"/users",
					"/login"
				)
				.permitAll()
			.anyRequest()
				.authenticated()
			.and()
			.formLogin(
					form -> form
							.loginPage("/login")
                            .defaultSuccessUrl("/index")
			)
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.logout()
			.logoutUrl("/logout")
			.addLogoutHandler(logoutHandler)
			.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
			.permitAll()
		;
		return http.build();
	}
}
