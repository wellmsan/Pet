package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import model.Usuario;
import utils.DaoAbstract;

public class UsuarioDAO extends DaoAbstract<Usuario> {
	
	private static UsuarioDAO instance;
	
	public static synchronized UsuarioDAO getInstance () {
		if(instance == null)
			instance = new UsuarioDAO();
		return instance;
	}
	
	@Override
	protected EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PetPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
			setClazz(Usuario.class);
		}
		return entityManager;
	}
	
	@Override
	public List<Usuario> getAll() {
		Session session = (Session) getEntityManager().getDelegate();
		return session.createCriteria(Usuario.class).addOrder(Order.asc("nome")).list();
	}
	
	@Override
	public List<Usuario> getAllBy(Usuario t) {
		Session session = (Session) getEntityManager().getDelegate();
		Example example = Example.create(t).enableLike().ignoreCase();		
		return session.createCriteria(Usuario.class).add(example).addOrder(Order.asc("nome")).list();
	}

}
