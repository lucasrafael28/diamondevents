package pi.de.diamondevents.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/cadastro").permitAll()
				.antMatchers(HttpMethod.GET, "/cadastro").permitAll()
				.antMatchers(HttpMethod.POST, "/cadastro").permitAll()
				.antMatchers("/user/**").hasRole("USUARIO")
				.antMatchers("/ava/**").hasRole("AVALIADOR")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
			.logout()
				.logoutSuccessUrl("/login?logout")
				.permitAll();
	}
}