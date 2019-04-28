# Pet
Projeto elaborado para avaliação da disciplina Projeto e Implementação Orientado a Objetos do MBA em Engenharia de Software - Estácio

## Objetivo do Trabalho
O aluno deverá ser capaz de desenvolver um sistema que realize as operações CRUD e controle sessão de usuário para Web seguindo os padrões MVC II, Front Controller e Command.

## Definição do domínio do negócio
## Modelo deverá possuir no mínimo:
### Uma herança de 3 níveis
A herança de 3 níveis foi aplicada na classe Fornecedor, que extende Pessoa e por sua vez extende ModelBase.
Trecho da classe ModelBase.java:

	@MappedSuperclass
	public abstract class ModelBase {
		...
	}

Trecho da classe Pessoa.java que extende ModelBase.java:

	@Entity(name="pessoa")
	public abstract class Pessoa extends ModelBase {
		...
	}
	
Trecho da classe Fornecedor.java que extende Pessoa.java:

	@Entity(name="pessoa_juridica")
	public class Fornecedor extends Pessoa {
		...

	}
### Uma associação
Foi utilizada uma associação unidirecional(simples) definindo o valor único da multiciplidade de muitos-pra-um representado pela annotation @ManyToOne seguedo pela annotation @JoinColum que especifica qual coluna deve ser utilizada na associação com a entidade.

	@Entity(name="pessoa")
	public abstract class Pessoa extends ModelBase {
		...
		@ManyToOne
		@JoinColumn(name = "estado_id")
		private Estado estado;

		public Estado getEstado() {
			return estado;
		}

		public void setEstado(Estado estado) {
			this.estado = estado;
		}

	}
	
## Criação do Banco de Dados
## Criação das classes Beans
## Criação da camada de persistência do modelo MVC II
## Criar as classes DAO Genérico referentes à manipulação e consulta dos dados de cada entidade envolvida
Classe DAO genérica e abstrata DaoAbstract<T> utilizado Generics.
	
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
	
Foram criadas classes DAO para cada entidade envolvida. Exemplo a classe UsuarioDAO.java extendendo a classe abstrata DaoAbstract.java utilizando Generics e padrão de projeto Singleton:

	public class UsuarioDAO extends DaoAbstract<Usuario> {

		private static UsuarioDAO instance;

		private UsuarioDAO() {}

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

## É facultado o uso de JPA/Hibernate
## Implemente os códigos para gerenciamento de sessão do sistema
## Implemente os códigos para gerenciamento de login ao sistema
## Criação do Servlet para a administração e para a para a exibição na Web (Front Controller) em conjunto com o padrão Command
## Criação dos JSPs necessários para a camada de visualização do Site e da administração.
