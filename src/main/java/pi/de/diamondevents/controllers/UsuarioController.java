package pi.de.diamondevents.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pi.de.diamondevents.models.Role;
import pi.de.diamondevents.models.Usuario;
import pi.de.diamondevents.repositories.RoleRepository;
import pi.de.diamondevents.repositories.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository ur;
	@Autowired
	private RoleRepository rr;
	
	@GetMapping("/cadastro")
	public String form() {
		return "usuarios/form";
	}

	@PostMapping("/cadastro")
	public String salvar(Usuario usuario) {
		
		ArrayList<Role> roles = new ArrayList<Role>();
		Role role = rr.findByNome("ROLE_USUARIO"); 
		roles.add(role);
		
		usuario.setRoles(roles);

		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		ur.save(usuario);

		return "redirect:/login";
	}
}