package pi.de.diamondevents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pi.de.diamondevents.services.CustomDetailsService;

@Configuration
public class SecurityConfig {

	@Autowired
	private CustomDetailsService detailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {

		builder.userDetailsService(detailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}