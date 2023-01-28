package pi.de.diamondevents.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pi.de.diamondevents.models.Convidado;
import pi.de.diamondevents.models.Festa;
import pi.de.diamondevents.repositories.ConvidadoRepository;
import pi.de.diamondevents.repositories.FestaRepository;

@Controller
@RequestMapping("/festas")
public class FestasController {

	@Autowired
	private FestaRepository fr;
	@Autowired
	private ConvidadoRepository cr;

	@RequestMapping("/form")
	public String form(Festa festa) {
		return "festas/formFesta";
	}

	@PostMapping
	public String adicionarFesta(@Valid Festa festa, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return "redirect:/festas/form";
		}

		System.out.println(festa);
		fr.save(festa);
		attributes.addFlashAttribute("mensagem", "Festa criada!");

		return "redirect:/festas";
	}

	@GetMapping
	public ModelAndView listarFesta() {
		List<Festa> festas = fr.findAll();
		ModelAndView mv = new ModelAndView("festas/lista");
		mv.addObject("festas", festas);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalharFesta(@PathVariable Long id, Convidado convidado) {
		ModelAndView md = new ModelAndView();
		Optional<Festa> opt = fr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/festas");
			return md;
		}

		md.setViewName("festas/detalhes");
		Festa festa = opt.get();
		md.addObject("festa", festa);

		List<Convidado> convidados = cr.findByFesta(festa);
		md.addObject("convidados", convidados);

		return md;
	}

	@PostMapping("/{idFesta}")
	public String salvarConvidado(@PathVariable Long idFesta, @Valid Convidado convidado, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return "redirect:/festas/{idFesta}";
		}

		System.out.println("Id da festa: " + idFesta);
		System.out.println(convidado);

		Optional<Festa> opt = fr.findById(idFesta);
		if (opt.isEmpty()) {
			return "redirect:/festas";
		}

		Festa festa = opt.get();
		convidado.setFesta(festa);

		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado criado!");

		return "redirect:/festas/{idFesta}";
	}

	@GetMapping("/{id}/remover")
	public String apagarFesta(@PathVariable Long id, RedirectAttributes attributes) {

		Optional<Festa> opt = fr.findById(id);

		if (!opt.isEmpty()) {
			Festa festa = opt.get();

			List<Convidado> convidados = cr.findByFesta(festa);

			cr.deleteAll(convidados);
			fr.delete(festa);
			attributes.addFlashAttribute("mensagem", "Festa removida!");
		}

		return "redirect:/festas";
	}

	@GetMapping("/{idFesta}/convidados/{idConvidado}/remover")
	public String apagarConvidado(@PathVariable Long idFesta, @PathVariable Long idConvidado,
			RedirectAttributes attributes) {

		Optional<Convidado> opt = cr.findById(idConvidado);

		if (!opt.isEmpty()) {
			Convidado convidado = opt.get();
			cr.delete(convidado);
			attributes.addFlashAttribute("mensagem", "Convidado removido!");
		}

		return "redirect:/festas/{idFesta}";
	}

	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarFesta(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Festa> opt = fr.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/festas");
			return md;
		}

		Festa festa = opt.get();
		md.setViewName("festas/formFesta");
		md.addObject("festa", festa);

		return md;
	}

	@GetMapping("/{idFesta}/convidados/{idConvidado}/selecionar")
	public ModelAndView selecionarConvidado(@PathVariable Long idFesta, @PathVariable Long idConvidado) {
		ModelAndView md = new ModelAndView();

		Optional<Festa> optFesta = fr.findById(idFesta);
		Optional<Convidado> optConvidado = cr.findById(idConvidado);

		if (optFesta.isEmpty() || optConvidado.isEmpty()) {
			md.setViewName("redirect:/festas");
			return md;
		}

		Festa festa = optFesta.get();
		Convidado convidado = optConvidado.get();

		if (festa.getId() != convidado.getFesta().getId()) {
			md.setViewName("redirect:/festas");
			return md;
		}

		md.setViewName("festas/detalhes");
		md.addObject("convidado", convidado);
		md.addObject("festa", festa);
		md.addObject("convidados", cr.findByFesta(festa));

		return md;
	}
}