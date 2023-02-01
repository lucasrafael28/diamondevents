package pi.de.diamondevents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		builder
			.inMemoryAuthentication()
			.withUser("joao").password(encoder.encode("123")).roles("ADMIN", "USER")
			.and()
			.withUser("jose").password(encoder.encode("123")).roles("USER", "AVALIADOR");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}