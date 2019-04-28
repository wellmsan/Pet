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
No projeto foi utilizando o JPA e Hibernate. Trecho da classe Fornecedor.java utilizando annotations do JPA.

	@Entity(name="pessoa_juridica")
	public class Fornecedor extends Pessoa {
		@Column(length=200, nullable=false)
		private String razaoSocial;
		@Column(length=100, nullable=true)
		private String nomeFantasia;
		@Column(length=19, nullable=false) 
		private String cnpj;
		@Column(length=30, nullable=true)
		private String inscricaoEstadual;
		@Column(length=30, nullable=true)
		private String inscricaoMunicipal;
	}

Trecho do arquivo de configuração do JPA persistence.xml

	<persistence-unit name="PetPU" transaction-type="RESOURCE_LOCAL">
		<provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
		
		<class>model.Pessoa</class>
		<class>model.Fornecedor</class>
		<class>model.Pet</class>
		<class>model.Usuario</class>
		<class>model.Estado</class>
		
		<properties>
			<property name="javax.persistence.jdbc.url" value="jdbc:derby:memory:pet;create=true" />
			<property name="javax.persistence.jdbc.user" value="pet" />
			<property name="javax.persistence.jdbc.password" value="pet" />
			<property name="javax.persistence.jdbc.driver" value="org.apache.derby.jdbc.EmbeddedDriver" />
			
			<property name="hibernate.dialect" value="org.hibernate.dialect.DerbyTenSevenDialect" />
			<property name="hibernate.show_sql" value="true" />
			<property name="hibernate.format_sql" value="true" />
			<property name="hibernate.hbm2ddl.auto" value="update" />
		</properties>
	</persistence-unit>
	
Trecho das classes DAO que utilizam o Query By Example do hibernate para consultas sql sem necessidade de escrita destas.

	@Override
	public List<Usuario> getAllBy(Usuario t) {
		Session session = (Session) getEntityManager().getDelegate();
		Example example = Example.create(t).enableLike().ignoreCase();		
		return session.createCriteria(Usuario.class).add(example).addOrder(Order.asc("nome")).list();
	}
	
## Implemente os códigos para gerenciamento de sessão do sistema

Para o controle de sessão foi implementada a classe SessionUtil.java que contem métodos estáticos para controle.

	public class SessionUtil {
		public static Usuario usuarioLogado(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			if (usuario == null) {
				try {
					response.sendRedirect("/Pet/index.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
			return usuario; 
		}
		
		public static void addUsuarioSession(HttpServletRequest request, HttpServletResponse response, Usuario usuario) {
			HttpSession session = request.getSession(true);
			session.setAttribute("usuarioLogado", usuario);
		}

		public static void logout(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			usuarioLogado(request, response);
		}
	}

## Implemente os códigos para gerenciamento de login ao sistema
O controle de login foi construíndo utilizando implementações de Command. Classe LoginEntrar.java

	public class LoginEntrar implements Command{

		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Usuario usuario = new Usuario();
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));

			List<Usuario> usu = UsuarioDAO.getInstance().getAllBy(usuario);

			if(usu.isEmpty()) {
				usuario.setSenha("");
				request.setAttribute("usuario", usuario);
				request.setAttribute("mensagem", "Usuário e/ou senha inválidos! Tente novamente!");
				request.setAttribute("tipo", "danger");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./loginController?command=Login");
				dispatcher.forward(request, response);
			} else {
				SessionUtil.addUsuarioSession(request, response, usu.get(0));
				RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=FornecedorIndex");
				dispatcher.forward(request, response);
			}		
		}

	}

## Criação do Servlet para a administração e para a para a exibição na Web (Front Controller) em conjunto com o padrão Command
Foram criados dois Sevlets para controle das atividades do sistema. O Controller.java para controle das atividades administrativas do sistema e LoginControle.java para controle do login.

Servlet Controller.java

	@WebServlet("/controller")
	public class Controller extends HttpServlet {
		private static final long serialVersionUID = 1L;

		public Controller() {}

		protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(SessionUtil.usuarioLogado(request, response) != null) {
				Command command = null;
				try {
					command = (Command) Class.forName("command." + request.getParameter("command")).newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				command.execute(request, response);
			}
		}

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			processRequest(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			processRequest(request, response);
		}

	}

Servlet LoginController.java

	@WebServlet("/loginController")
	public class LoginController extends HttpServlet {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public LoginController() {}

		protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Command command = null;
			try {
				command = (Command) Class.forName("command." + request.getParameter("command")).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			command.execute(request, response);
		}

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			processRequest(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			processRequest(request, response);
		}

	}

## Criação dos JSPs necessários para a camada de visualização do Site e da administração.
