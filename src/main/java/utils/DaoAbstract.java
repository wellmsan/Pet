package utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

public abstract class DaoAbstract<T> {
	
	protected Class<T> clazz;
	protected EntityManager entityManager;
	
	public void setClazz (Class<T> clazz){
		this.clazz = clazz;
	}
	
	protected abstract EntityManager getEntityManager();
	
	public T getById(Long id) {
		return (T) getEntityManager().find(this.clazz, id);
	}

	public List<T> getAll() {
		return getEntityManager().createQuery("FROM " + this.clazz.getName()).getResultList();
	}
	
	public List<T> getAllBy(T t) {
		Session session = (Session) getEntityManager().getDelegate();
		Example example = Example.create(t).excludeZeroes();		
		return session.createCriteria(this.clazz).add(example).list();
	}

	public void save(T t) {
		EntityTransaction transaction = null;
		try {
			transaction = getEntityManager().getTransaction();
			transaction.begin();
			getEntityManager().persist(t);
			getEntityManager().flush();
			transaction.commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			transaction.rollback();
		} 

	}
	
	public void update(T t) {
		EntityTransaction transaction = null;
		try {
			transaction = getEntityManager().getTransaction();
			transaction.begin();
			getEntityManager().merge(t);
			getEntityManager().flush();
			transaction.commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			transaction.rollback();
		} 

	}

	public void remove(T t) {
		EntityTransaction transaction = null;
		try {
			ModelBase base = (ModelBase) t;
			t = (T) getEntityManager().find(this.clazz, base.getId());
			transaction = getEntityManager().getTransaction();
			transaction.begin();
			getEntityManager().remove(t);
			getEntityManager().flush();
			transaction.commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			transaction.rollback();
		} 
	}


}
