package pi.de.diamondevents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import pi.de.diamondevents.models.Usuario;
import pi.de.diamondevents.repositories.UsuarioRepository;

@Component
public class CustomDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByMatricula(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return usuario;
	}
}