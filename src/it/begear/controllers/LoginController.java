package it.begear.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.begear.database.DipendenteDAO;
import it.begear.models.Dipendente;

@Controller
public class LoginController {

	@Autowired
	private DipendenteDAO dao;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView verifyLogin(Dipendente dip, ModelMap model, HttpServletRequest req) {
		Dipendente d = dao.cercaDip(dip);
		if(d == null) {
			model.addAttribute("errore", "Nome utente e/o password errati.");
			return new ModelAndView("index", "formDip", new Dipendente("a", "a"));
		}
		
		if(d.getAdmin() == 0) {
			req.getSession().setAttribute("username", d.getUsername());
			req.getSession().setAttribute("cf_user", d.getCf());
			return new ModelAndView("redirect:/user/loadDoc");
		}
		
		req.getSession().setAttribute("username", d.getUsername());
		req.getSession().setAttribute("cf_user", d.getCf());
		req.getSession().setAttribute("admin", "admin");
		model.addAttribute("listaDip", dao.listaDip());
		return new ModelAndView("gestioneDip", "formDip", new Dipendente());
	}
	
}
