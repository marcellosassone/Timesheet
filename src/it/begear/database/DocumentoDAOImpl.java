package it.begear.database;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.begear.models.Dipendente;
import it.begear.models.Documento;
import it.begear.util.HibernateUtil;

public class DocumentoDAOImpl implements DocumentoDAO{

	private SessionFactory sf = HibernateUtil.getInstance().getSf();
	
	private Logger log = Logger.getLogger(DocumentoDAOImpl.class);
	
	@Override
	public Documento cercaDoc(Documento doc) {
		Session session = null;
		Transaction tx = null;
		Documento d = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			d = (Documento) session.get(Documento.class, doc.getId());
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		return d;
	}

	@Override
	public void inserisciDoc(Documento doc) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.save(doc);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error("Upload documento fallito!", e);
		} finally {
			if (session != null)
				session.close();
		}		
	}

	@Override
	public void eliminaDoc(Documento doc) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.delete(doc);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}		
	}

	@Override
	public void modificaDoc(Documento doc) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.update(doc);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Documento> listaDoc() {
		Session session = null;
		Transaction tx = null;
		List<Documento> documenti = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("FROM Documento");
			documenti = qry.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		return documenti;
	}

	@Override
	public Set<Documento> listaPrivata(Dipendente dip) {
		Session session = null;
		Transaction tx = null;
		Dipendente d = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			d = (Dipendente) session.load(Dipendente.class, dip.getCf());
			Hibernate.initialize(d.getDocumenti());
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		return d.getDocumenti();
	}

	
}
