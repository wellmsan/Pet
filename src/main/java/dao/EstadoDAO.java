package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import model.Estado;
import utils.DaoAbstract;

public class EstadoDAO extends DaoAbstract<Estado> {
	
	private static EstadoDAO instance;
	
	public static synchronized EstadoDAO getInstance () {
		if(instance == null)
			instance = new EstadoDAO();
		return instance;
	}
	
	@Override
	protected EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PetPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
			setClazz(Estado.class);
			
			
			getInstance().save(new Estado("Bahia", "BA", "BR"));
			getInstance().save(new Estado("Sergipe", "SE", "BR"));
			getInstance().save(new Estado("Pernambuco", "PE", "BR"));
			getInstance().save(new Estado("Alagoas", "AL", "BR"));
			getInstance().save(new Estado("Paraíba", "PB", "BR"));
			getInstance().save(new Estado("Rio Grande do Norte", "RN", "BR"));
			getInstance().save(new Estado("Ceará", "CE", "BR"));
			getInstance().save(new Estado("Maranhão", "MA", "BR"));
			getInstance().save(new Estado("Piauí", "PI", "BR"));
		}
		return entityManager;
	}
	
	@Override
	public List<Estado> getAll() {
		Session session = (Session) getEntityManager().getDelegate();
		return session.createCriteria(Estado.class).addOrder(Order.asc("nome")).list();
	}
	
	@Override
	public List<Estado> getAllBy(Estado t) {
		Session session = (Session) getEntityManager().getDelegate();
		Example example = Example.create(t).enableLike().ignoreCase();		
		return session.createCriteria(Estado.class).add(example).addOrder(Order.asc("nome")).list();
	}

}
