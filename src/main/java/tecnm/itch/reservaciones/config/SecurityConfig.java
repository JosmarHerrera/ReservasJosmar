package tecnm.itch.reservaciones.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource())).csrf(csrf -> csrf.disable())

				// --- NUEVAS LÍNEAS PARA MODO API ---
				// Deshabilitar el login por formulario que causa la redirección 302
				.formLogin(form -> form.disable()).httpBasic(basic -> basic.disable())

				// Configurar la gestión de sesiones como STATELESS (sin estado)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// --- FIN DE NUEVAS LÍNEAS ---

				.authorizeHttpRequests(
						authz -> authz.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().permitAll());

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);

		// ✅ Permitir local y Railway (AGREGADO)
		config.addAllowedOriginPattern("http://localhost:3000");
		config.addAllowedOriginPattern("https://frontjosmar-production-fb4a.up.railway.app");

		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
