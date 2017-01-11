package it.begear.database;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import it.begear.models.Notifica;
import it.begear.util.HibernateUtil;

public class NotificaDAOImpl implements NotificaDAO{

	private SessionFactory sf = HibernateUtil.getInstance().getSf();
	
	private Logger log = Logger.getLogger(NotificaDAOImpl.class);
	
	@Override
	public Notifica cercaNot(Notifica not) {
		Session session = null;
		Transaction tx = null;
		Notifica n = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			n = (Notifica) session.get(Notifica.class, not.getId());
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error("Cerca Notifica" + e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		return n;
	}

	@Override
	public void inserisciNot(Notifica not) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.save(not);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error("Inserisci notifica" + e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}		
	}

	@Override
	public void eliminaNot(Notifica not) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.delete(not);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error("Elimina Notifica" + e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}		
	}

	@Override
	public void modificaNot(Notifica not) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.update(not);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error("Modifica Notifiche" + e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notifica> listaNot() {
		Session session = null;
		Transaction tx = null;
		List<Notifica> notifiche = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("FROM Notifica");
			notifiche = qry.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error("Lista Notifica" + e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		return notifiche;
	}

	
}
