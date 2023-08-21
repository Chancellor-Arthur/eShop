package ru.svitkin.eshopserver.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.user.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	private final UserService userService;
	private final AuthenticationConfiguration configuration;
	private final JwtRequestFilter jwtRequestFilter;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationEntryPoint authenticationEntryPoint;
	private final AccessDeniedHandler accessDeniedHandler;

	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(List.of(CorsConfiguration.ALL));
		configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
		configuration.setAllowedHeaders(List.of(CorsConfiguration.ALL));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Autowired
	void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
						.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(requests -> requests
						.requestMatchers("/auth/**", "/devices/**", "/swagger/**", "/actuator/**", "/error").permitAll()
						.requestMatchers("/admin/**", "/users/**").hasRole("ADMIN").anyRequest().authenticated())
				.sessionManagement(managementConfigurer -> managementConfigurer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
						.authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler))
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(LogoutConfigurer::permitAll);

		return http.build();
	}
}
