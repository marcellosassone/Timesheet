package it.begear.database;

import java.util.List;

import it.begear.models.Dipendente;

public interface DipendenteDAO {
	public Dipendente cercaDipPerCF(Dipendente d);
	public Dipendente cercaDip(Dipendente d);
	public void inserisciDip(Dipendente d);
	public void eliminaDip(Dipendente d);
	public void modificaDip(Dipendente d);
	public List<Dipendente> listaDip();
}
