package pi.de.diamondevents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pi.de.diamondevents.models.Festa;
import pi.de.diamondevents.repositories.FestaRepository;

@Controller
public class FestasController {

	@Autowired
	private FestaRepository fr;
	
	@RequestMapping("/festas/form")
	public String form() {
		return "formFesta";
	}
	
	@PostMapping("/festas")
	public String adicionarFesta(Festa festa) {
		
		System.out.println(festa);
		fr.save(festa);
		
		return "festa-adicionada";
	}
}