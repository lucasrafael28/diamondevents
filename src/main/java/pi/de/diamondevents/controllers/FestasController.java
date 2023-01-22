package pi.de.diamondevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FestasController {

	@RequestMapping("/festas/form")
	public String form() {
		return "formFesta";
	}
}