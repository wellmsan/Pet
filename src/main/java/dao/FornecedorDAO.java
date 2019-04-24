package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import model.Fornecedor;
import utils.DaoAbstract;

public class FornecedorDAO extends DaoAbstract<Fornecedor> {
	
	private static FornecedorDAO instance;
	
	public static synchronized FornecedorDAO getInstance () {
		if(instance == null)
			instance = new FornecedorDAO();
		return instance;
	}
	
	@Override
	protected EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PetPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
			setClazz(Fornecedor.class);
		}
		return entityManager;
	}
	
	@Override
	public List<Fornecedor> getAll() {
		Session session = (Session) getEntityManager().getDelegate();
		return session.createCriteria(Fornecedor.class).addOrder(Order.asc("razaoSocial")).list();
	}
	
	@Override
	public List<Fornecedor> getAllBy(Fornecedor t) {
		Session session = (Session) getEntityManager().getDelegate();
		Example example = Example.create(t).enableLike().ignoreCase();		
		return session.createCriteria(Fornecedor.class).add(example).addOrder(Order.asc("razaoSocial")).list();
	}

}
