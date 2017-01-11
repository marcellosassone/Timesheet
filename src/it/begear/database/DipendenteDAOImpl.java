package it.begear.database;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import it.begear.models.Dipendente;
import it.begear.util.HibernateUtil;

public class DipendenteDAOImpl implements DipendenteDAO {
	private SessionFactory sf = HibernateUtil.getInstance().getSf();

	private Logger log = Logger.getLogger(DipendenteDAOImpl.class);
	@Override
	public Dipendente cercaDip(Dipendente d) {
		Session session = null;
		Transaction tx = null;
		Dipendente dip = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Dipendente.class);
			Criterion crit1 = Restrictions.eq("username", d.getUsername());
			Criterion crit2 = Restrictions.eq("password", d.getPassword());
			LogicalExpression lx = Restrictions.and(crit1, crit2);
			cr.add(lx);
			dip = (Dipendente) cr.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		return dip;
	}

	@Override
	public void inserisciDip(Dipendente d) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.save(d);
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
	public void eliminaDip(Dipendente d) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.delete(d);
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
	public void modificaDip(Dipendente d) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.update(d);
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
	public List<Dipendente> listaDip() {
		Session session = null;
		Transaction tx = null;
		List<Dipendente> dipendenti = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Query qry = session.createQuery("FROM Dipendente");
			dipendenti = qry.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log.error(e.getStackTrace());
		} finally {
			if (session != null)
				session.close();
		}

		return dipendenti;
	}

	@Override
	public Dipendente cercaDipPerCF(Dipendente d) {
		Session session = null;
		Transaction tx = null;
		Dipendente dip = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Dipendente.class);
			Criterion crit1 = Restrictions.eq("cf", d.getCf());
			cr.add(crit1);
			dip = (Dipendente) cr.uniqueResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			StringBuilder stackTrace = new StringBuilder();
			for(StackTraceElement ste : e.getStackTrace()) {
				stackTrace.append(ste.toString());
			}
			log.error(stackTrace);
		} finally {
			if (session != null)
				session.close();
		}

		return dip;
	}

}
