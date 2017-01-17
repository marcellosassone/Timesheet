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

/**
 * Created by Marcello Sassone detto il borbonico on 11/01/2017
 * @author Marcello Sassone, il borbonico
 *
 */
public class DipendenteControllerTest {

	/**
	 * Istanza di controller utilizzata nei metodi di test
	 */
	DipendenteController tester = new DipendenteController();
	/**
	 * Rappresentazione di un dipendente null
	 */
	Dipendente dNull = null;
	/** 
	 * Rappresentazione di un dipendente standard pienamente valorizzato
	 */
	Dipendente d = null;

	/**
	 * Utilizzo dell'annotazione Mock di Mockito per la creazione automatica del mock di un DipendenteDAOImpl
	 */
	@Mock
	DipendenteDAO dao;

	/**
	 	* Metodo per l'inizializzazione del daoMock e del dipendente pienamente valorizzato
	 */
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
		// setto il dao del mio controller di test in modo che sia la copia mock
		tester.setDao(dao);
	}

	/**
	 * Metodo per la verifica del raggiungimento del termine del metodo di modifica se il dipendente da modificare non esiste
	 */
	@Test
	public void modificaDipendenteConCFInesistenteTerminaConSuccesso() {
		// when = quando verrà invocato il metodo "cercaDipPerCF" del dao, esso restituirà "null" a prescindere
		when(dao.cercaDipPerCF(Matchers.anyObject())).thenReturn(null);
		ModelAndView mav = new ModelAndView("modDip");
		assertEquals(mav.getViewName(), tester.modificaDipendente(Matchers.anyString()).getViewName());
	}
	
	/**
	 * Metodo per la verifica del raggiungimento del termine del metodo di modifica se il dipendente da modificare esiste
	 * @throws CloneNotSupportedException
	 */
	@Test
	public void modificaDipendenteConCFEsistenteTerminaConSuccesso() throws CloneNotSupportedException {
		Dipendente dipClone = (Dipendente) d.clone();
		when(dao.cercaDipPerCF(d)).thenReturn(dipClone);
		ModelAndView mav = new ModelAndView("modDip");
		assertEquals(mav.getViewName(), tester.modificaDipendente(Matchers.anyString()).getViewName());
		// verify =  verifica che il metodo "cercaDipPerCF" del dao venga richiamato esattamente 1 volta nel corso del metodo di
		// test, con un qualsiasi parametro in ingresso (non rilevante)
		verify(dao, times(1)).cercaDipPerCF(Matchers.anyObject());
	}
	
	/**
	 * Metodo per la verifica della propagazione della NullPointerException lanciata dal dao in caso di dipendente null
	 */
	@Test(expected=NullPointerException.class)
	public void finalizzaModificaPropagaNullPointerSeDipNull() {
		doThrow(new NullPointerException()).when(dao).modificaDip(dNull);
		tester.finalizzaModifica(dNull, new ModelMap());
	}
	
	/**
	 * Metodo per la verifica del raggiungimento del termine del metodo in caso di finalizzazione modifica dipendente valorizzato
	 */
	@Test
	public void finalizzaModificaTerminaConSuccessoConDipendenteNormale() {
		assertEquals("redirect:/load", tester.finalizzaModifica(d, new ModelMap()));
		verify(dao, times(1)).modificaDip(d);
	}
	
	/**
	 * Metodo per la verifica del raggiungimento del termine del metodo in caso di eccezione lanciata dal dao durante l'eliminazione
	 */
	@Test
	public void eliminaDipendenteConCFScorrettoTerminaConSuccesso() {
		doThrow(new HibernateException("Failed!")).when(dao).eliminaDip(d);
		assertEquals("redirect:/load", tester.eliminaDipendente(Matchers.anyString()));
		verify(dao, times(1)).eliminaDip(Matchers.anyObject());
	}

	/**
	 * Metodo per la verifica del raggiungimento del termine del metodo in caso di eliminazione dipendente esistente
	 */
	@Test
	public void eliminaDipendenteConCFCorrettoTerminaConSuccesso() {
		assertEquals("redirect:/load", tester.eliminaDipendente(Matchers.anyString()));
		verify(dao, times(1)).eliminaDip(Matchers.anyObject());
	}

	/**
	 * Metodo per la verifica della propagazione della NullPointerException lanciata dal dao in caso di inserimento di dipendente nullo
	 */
	@Test(expected = NullPointerException.class)
	public void inserisciDipNullLanciaNullPointerException() {
		//doThrow = simula il lancio di un'eccezione di tipo NullPointerException quando verrà invocato il metodo "inserisciDip" del dao
		doThrow(new NullPointerException()).when(dao).inserisciDip(dNull);
		tester.inserisciDipendente(dNull);
	}

	/**
	 * Metodo per la verifica del corretto inserimento di un dipendente valorizzato
	 */
	@Test
	public void testInserisciDipendenteValorizzato() {
		assertEquals("redirect:/load", tester.inserisciDipendente(d));
		verify(dao, times(1)).inserisciDip(d);
	}

	/**
	 * Metodo per la verifica del valore della lista dipendenti recuperata dal dao, in caso siano presenti dei dipendenti sul db
	 */
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

	/**
	 * Metodo per la verifica dell'integrità della lista dipendenti recuperata tramite il dao
	 */
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
