package it.begear.database;

import java.util.List;

import it.begear.models.Notifica;

public interface NotificaDAO {
	
	public Notifica cercaNot(Notifica not);
	public void inserisciNot(Notifica not);
	public void eliminaNot(Notifica not);
	public void modificaNot(Notifica not);
	public List<Notifica> listaNot();
}
