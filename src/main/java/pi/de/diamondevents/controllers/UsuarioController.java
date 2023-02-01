package pi.de.diamondevents.controllers;

import java.util.ArrayList;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pi.de.diamondevents.models.Usuario;
import pi.de.diamondevents.repositories.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/cadastro")
	public String form() {
		System.out.println("Chamou método do formulárioi");
		return "usuarios/form";
	}

	@PostMapping("/cadastro")
	public String salvar(Usuario usuario) {

		ArrayList<Role> roles = new ArrayList<Role>();
		Role role = new Role();

		role.setId(2L);
		roles.add(role);
		usuario.setRoles(roles);

		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		usuarioRepository.save(usuario);

		return "redirect:/login";
	}
}