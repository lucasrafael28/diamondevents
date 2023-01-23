package pi.de.diamondevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pi.de.diamondevents.models.Festa;

@Controller
public class FestasController {

	@RequestMapping("/festas/form")
	public String form() {
		return "formFesta";
	}
	
	@PostMapping("/festas")
	public String adicionarFesta(Festa festa) {
		
		System.out.println(festa);
		
		return "festa-adicionada";
	}
}