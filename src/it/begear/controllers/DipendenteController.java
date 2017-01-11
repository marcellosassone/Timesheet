package it.begear.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.begear.database.DipendenteDAO;
import it.begear.models.Dipendente;

@Controller
public class DipendenteController {

	@Autowired
	private DipendenteDAO dao;	
	
	@RequestMapping(value="/updateDip/{cf}", method=RequestMethod.GET)
	public ModelAndView modificaDipendente(@PathVariable String cf) {
		Dipendente d = new Dipendente();
		d.setCf(cf);
		Dipendente dip = dao.cercaDipPerCF(d);
		return new ModelAndView("modDip", "formDip", dip);
	}
	
	@RequestMapping(value="/finalizzaModDip", method=RequestMethod.POST)
	public String finalizzaModifica(Dipendente dip, ModelMap model) {
		dao.modificaDip(dip);
		return "redirect:/load";

	}
	
	@RequestMapping(value="/deleteDip/{cf}")
	public String eliminaDipendente(@PathVariable String cf) {
		Dipendente d = new Dipendente();
		d.setCf(cf);
		dao.eliminaDip(d);
		return "redirect:/load";
	}
	
	@RequestMapping(value="/inserisciDip", method=RequestMethod.POST)
	public String inserisciDipendente(Dipendente d) {
		dao.inserisciDip(d);
		return "redirect:/load";
	}
	
	@RequestMapping(value="/load", method=RequestMethod.GET)
	public ModelAndView load(ModelMap model) {
		model.addAttribute("listaDip", dao.listaDip());
		return new ModelAndView("gestioneDip", "formDip", new Dipendente());
	}

	public void setDao(DipendenteDAO dao) {
		this.dao = dao;
	}
}
