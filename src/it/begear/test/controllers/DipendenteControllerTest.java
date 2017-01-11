package it.begear.test.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import it.begear.controllers.DipendenteController;
import it.begear.database.DipendenteDAO;
import it.begear.models.Dipendente;
import it.begear.models.Documento;

public class DipendenteControllerTest {

	DipendenteController tester = new DipendenteController();
	Dipendente dNull = null;
	Dipendente d = null;

	@Mock
	DipendenteDAO dao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		d = new Dipendente();
		d.setNome("Mario");
		d.setCognome("Rossi");
		d.setUsername("mario.rossi");
		d.setPassword("pippo");
		d.setCf("MRR2938129387");
		d.setAdmin(0);
		d.setDocumenti(new HashSet<Documento>());
		tester.setDao(dao);
	}

	@Test
	public void modificaDipendenteConCFInesistenteTerminaConSuccesso() {
		when(dao.cercaDipPerCF(Matchers.anyObject())).thenReturn(null);
		ModelAndView mav = new ModelAndView("modDip");
		assertEquals(mav.getViewName(), tester.modificaDipendente(Matchers.anyString()).getViewName());
	}
	
	@Test
	public void modificaDipendenteConCFEsistenteTerminaConSuccesso() throws CloneNotSupportedException {
		Dipendente dipClone = (Dipendente) d.clone();
		when(dao.cercaDipPerCF(d)).thenReturn(dipClone);
		ModelAndView mav = new ModelAndView("modDip");
		assertEquals(mav.getViewName(), tester.modificaDipendente(Matchers.anyString()).getViewName());
		verify(dao, times(1)).cercaDipPerCF(Matchers.anyObject());
	}
	
	@Test(expected=NullPointerException.class)
	public void finalizzaModificaPropagaNullPointerSeDipNull() {
		doThrow(new NullPointerException()).when(dao).modificaDip(dNull);
		tester.finalizzaModifica(dNull, new ModelMap());
	}
	
	@Test
	public void finalizzaModificaTerminaConSuccessoConDipendenteNormale() {
		assertEquals("redirect:/load", tester.finalizzaModifica(d, new ModelMap()));
		verify(dao, times(1)).modificaDip(d);
	}
	
	@Test
	public void eliminaDipendenteConCFScorrettoTerminaConSuccesso() {
		doThrow(new HibernateException("Failed!")).when(dao).eliminaDip(d);
		assertEquals("redirect:/load", tester.eliminaDipendente(Matchers.anyString()));
		verify(dao, times(1)).eliminaDip(Matchers.anyObject());
	}

	@Test
	public void eliminaDipendenteConCFCorrettoTerminaConSuccesso() {
		assertEquals("redirect:/load", tester.eliminaDipendente(Matchers.anyString()));
		verify(dao, times(1)).eliminaDip(Matchers.anyObject());
	}

	@Test(expected = NullPointerException.class)
	public void inserisciDipNullLanciaNullPointerException() {
		doThrow(new NullPointerException()).when(dao).inserisciDip(dNull);
		tester.inserisciDipendente(dNull);
	}

	@Test
	public void testInserisciDipendenteValorizzato() {
		assertEquals("redirect:/load", tester.inserisciDipendente(d));
		verify(dao, times(1)).inserisciDip(d);
	}

	@Test
	public void listaDipNonPuoEssereNullSeDipSuDB() {
		List<Dipendente> dips = new ArrayList<>();
		dips.add(new Dipendente());
		when(dao.listaDip()).thenReturn(dips);

		ModelMap model = new ModelMap();
		tester.load(model);

		assertNotNull(model.get("listaDip"));
		verify(dao, times(1)).listaDip();
	}

	@Test
	public void listaDipDeveEssereUgualeAlValoreDB() {
		List<Dipendente> dips = new ArrayList<>();
		dips.add(new Dipendente());
		when(dao.listaDip()).thenReturn(dips);

		ModelMap model = new ModelMap();
		tester.load(model);

		assertEquals(model.get("listaDip"), dips);
		verify(dao, times(1)).listaDip();
	}

}
