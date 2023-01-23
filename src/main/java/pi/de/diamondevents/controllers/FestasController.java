package pi.de.diamondevents.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pi.de.diamondevents.models.Festa;
import pi.de.diamondevents.repositories.FestaRepository;

@Controller
@RequestMapping("/festas")
public class FestasController {

	@Autowired
	private FestaRepository fr;

	@RequestMapping("/form")
	public String form() {
		return "festas/formFesta";
	}

	@PostMapping
	public String adicionarFesta(Festa festa) {

		System.out.println(festa);
		fr.save(festa);

		return "festas/festa-adicionada";
	}

	@GetMapping
	public ModelAndView listarFesta() {
		List<Festa> festas = fr.findAll();
		ModelAndView mv = new ModelAndView("festas/lista");
		mv.addObject("festas", festas);
		return mv;
	}
}