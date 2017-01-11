package it.begear.database;

import java.util.List;
import java.util.Set;

import it.begear.models.Dipendente;
import it.begear.models.Documento;

public interface DocumentoDAO {
	
	public Documento cercaDoc(Documento doc);
	public void inserisciDoc(Documento doc);
	public void eliminaDoc(Documento doc);
	public void modificaDoc(Documento doc);
	public List<Documento> listaDoc();
	public Set<Documento> listaPrivata(Dipendente dip);
}
