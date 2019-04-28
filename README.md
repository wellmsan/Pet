# Pet
Projeto elaborado para avaliação da disciplina Projeto e Implementação Orientado a Objetos do MBA em Engenharia de Software - Estácio

## Objetivo do Trabalho
O aluno deverá ser capaz de desenvolver um sistema que realize as operações CRUD e controle sessão de usuário para Web seguindo os padrões MVC II, Front Controller e Command.

## Definição do domínio do negócio
Software Web para cadastro de fornecedores de clientes Pet Shop e Clínicas Veterinárias.

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
O banco de dados desse projeto é criado de forma automática pelo hibernate de acordo as annotation utilizadas nas classes Bean. Com o intuito de facilitar a execução e avaliação do projeto foi utilizado o banco de dados Derby em memória. Com já dito anteriormente, ao executar o projeto o banco é construído automaticamente.

Trecho do arquivo pom.xml que representa as dependências do projeto para o banco de dados Derby
	<!-- https://mvnrepository.com/artifact/org.apache.derby/derby -->
	<dependency>
		<groupId>org.apache.derby</groupId>
		<artifactId>derby</artifactId>
		<version>10.12.1.1</version>
	</dependency>

	<!-- https://mvnrepository.com/artifact/org.apache.derby/derbyclient -->
	<dependency>
		<groupId>org.apache.derby</groupId>
		<artifactId>derbyclient</artifactId>
		<version>10.12.1.1</version>
	</dependency>	

Trecho do arquivo persistence.xml que realiza a configuração do banco de dados Derby.

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
	
	
## Criação das classes Beans

Tracho da classe Estado.java:

	@Entity(name="estado")
	public class Estado extends ModelBase{
		@Column(length=80, nullable=false)
		private String nome;
		@Column(length=2, nullable=false)
		private String uf;
		@Column(length=2, nullable=false)
		private String pais;
		
		public Estado() {}

		public Estado(String nome, String uf, String pais) {
			super();
			this.nome = nome;
			this.uf = uf;
			this.pais = pais;
		}
		
		//Métodos Gets e Sets
		...
	}
	
Trecho da classe Fornecedor.java:
	
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

		public Fornecedor() {}
		
		//Métodos Gets e Sets
		...	
	}
	
Trecho da classe Pessoa.java:

	@Entity(name="pessoa")
	public abstract class Pessoa extends ModelBase {
		@Column(length=14, nullable=false)
		private String telefone;
		@Column(length=255, nullable=false)
		private String email;
		@Column(length=60, nullable=false)
		private String endereco;
		@Column(length=60, nullable=true)
		private String complementoEndereco;
		@Column(length=10, nullable=false)
		private String numeroEndereco;
		@Column(length=25, nullable=false)
		private String cidade;
		@ManyToOne
		@JoinColumn(name = "estado_id")
		private Estado estado;

		//Métodos Gets e Sets
		...

	}

Trecho da classe Usuario.java:
	
	@Entity(name="usuario")
	public class Usuario extends ModelBase {
		@Column(length=50, nullable=false)
		private String nome;
		@Column(length=255, nullable=false)
		private String email;
		@Column(length=50, nullable=false)
		private String senha;

		public Usuario() {}
		
		//Métodos Gets e Sets
		...	

	}
	
Trecho da classe Pet.java:
	
	@Entity(name="pets")
	public abstract class Pet extends ModelBase {
		@Column(length=50, nullable=false)
		private String nome;
		@Column(length=20, nullable=true)
		private Date nascimento;
		@ManyToOne
		@JoinColumn(name="usuario_id")
		private Usuario tutor;
		
		public Pet(){}

		//Métodos Gets e Sets
		...

	}

## Criação da camada de persistência do modelo MVC II
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
	
F

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
	
Classe para ação de Logn Login.java implementando Commad:

	public class Login implements Command{

		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);		
		}

	}
	
Classe para ação de Salvar Fornecedor FornecedorSave.java implementando Command.

	public class FornecedorSave implements Command{

		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setRazaoSocial(request.getParameter("razaoSocial"));
			fornecedor.setNomeFantasia(request.getParameter("nomeFantasia"));
			fornecedor.setCnpj(request.getParameter("cnpj"));
			fornecedor.setInscricaoEstadual(request.getParameter("inscricaoEstadual"));
			fornecedor.setInscricaoMunicipal(request.getParameter("inscricaoMunicipal"));
			fornecedor.setTelefone(request.getParameter("telefone"));
			fornecedor.setEmail(request.getParameter("email"));
			fornecedor.setEndereco(request.getParameter("endereco"));
			fornecedor.setComplementoEndereco(request.getParameter("complementoEndereco"));
			fornecedor.setNumeroEndereco(request.getParameter("numeroEndereco"));
			fornecedor.setCidade(request.getParameter("cidade"));
			fornecedor.setEstado(EstadoDAO.getInstance().getById(Long.parseLong(request.getParameter("estado"))));
			try {
				fornecedor.valida();
				FornecedorDAO.getInstance().save(fornecedor);
				request.setAttribute("mensagem", "Fornecedor salvo com sucesso!!!");
				request.setAttribute("tipo", "success");
				request.setAttribute("fornecedores", FornecedorDAO.getInstance().getAll());
				RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=FornecedorIndex");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				request.setAttribute("fornecedor", fornecedor);
				request.setAttribute("mensagem", "Falha ao salvar Fornecedor! " + e.getMessage());
				request.setAttribute("tipo", "danger");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=FornecedorCreate");
				dispatcher.forward(request, response);
			}
		}

	}

## Criação dos JSPs necessários para a camada de visualização do Site e da administração.

Arquivo index.jsp que representa a tela de Login do sistema:

	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:set var="req" value="${pageContext.request}" />
	<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

	<!DOCTYPE html>
	<html>

	<head>

	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	    <title>Pet | Estácio</title>

	    <link href="${baseURL}/assets/inspinia/css/bootstrap.min.css" rel="stylesheet">
	    <link href="${baseURL}/assets/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">

	    <link href="${baseURL}/assets/inspinia/css/animate.css" rel="stylesheet">
	    <link href="${baseURL}/assets/inspinia/css/style.css" rel="stylesheet">

	</head>

	<body class="gray-bg">

	    <div class="middle-box text-center loginscreen animated fadeInDown">
		<div>
		    <div>

			<h1 class="logo-name">Pet+</h1>

		    </div>
		    <h3>Bem vindo ao Pet+</h3>
		    <p></p>
		    <p>Login in</p>
		    <c:if test="${mensagem != '' && mensagem != null}">
					<div class="alert alert-${tipo} alert-dismissable">
					<button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
					${mensagem}
				    </div>
				</c:if>
		    <form class="m-t" role="form" method="POST" action="./loginController?command=LoginEntrar">
			<div class="form-group">
			    <input type="email" class="form-control" placeholder="Email" name="email" required="">
			</div>
			<div class="form-group">
			    <input type="password" class="form-control" placeholder="Senha" name="senha" required="">
			</div>
			<button type="submit" class="btn btn-primary block full-width m-b">Entrar</button>

			<!--  a href="#"><small>Forgot password?</small></a -->
			<p class="text-muted text-center"><small>Ainda não tem uma conta?</small></p>
			<a class="btn btn-sm btn-white btn-block" href="./loginController?command=CriarConta">Criar Conta</a>
		    </form>
		    <p class="m-t"> <small>Copyright &copy; 2019 <strong>Welber Santana</strong></small> </p>
		    <p>MBA Engenharia de Software <strong>Estácio</strong>.</p>
		</div>
	    </div>

	    <!-- Mainly scripts -->
	    <script src="${baseURL}/assets/inspinia/js/jquery-3.1.1.min.js"></script>
	    <script src="${baseURL}/assets/inspinia/js/popper.min.js"></script>
	    <script src="${baseURL}/assets/inspinia/js/bootstrap.js"></script>

	</body>

	</html>
	
## Como executar
Este projeto foi construído utilizando maven ao construir todas as depedências serão "baixadas". O projeto conta com a versão ambarcada do Tomcat7, não há necessidade de configurar outro servidor.

	mvn clean install tomcat7:run-war
	
Para executar o projeto, importe-o no eclipse e crie uma nova configuração de execução (Botão direto no projeto >>  Run As >> Maven Build...), em Goals cole o trecho abaixo:
	
	clean install tomcat7:run-war
		
	
## Screenshots

<img src="https://drive.google.com/open?id=1HlVwnCYD5QFoePem7kuxuBfaeSYhQaVT" />
![Bilby Stampede]()

## Autor

- [Welber Santana](https://github.com/wellmsan)
