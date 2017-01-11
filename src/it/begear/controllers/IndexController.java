package it.begear.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.begear.models.Dipendente;

@Controller
public class IndexController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView goToIndex() {
		return new ModelAndView("index", "formDip", new Dipendente("a","a"));
	}
	
}
