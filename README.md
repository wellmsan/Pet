# Pet
Projeto elaborado para avaliação da disciplina Projeto e Implementação Orientado a Objetos do MBA em Engenharia de Software - Estácio

## Objetivo do Trabalho
O aluno deverá ser capaz de desenvolver um sistema que realize as operações CRUD e controle sessão de usuário para Web seguindo os padrões MVC II, Front Controller e Command.

## Definição do domínio do negócio
## Modelo deverá possuir no mínimo
### Uma herança de 3 níveis

	@MappedSuperclass
	public abstract class ModelBase {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	}
	
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

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public String getComplementoEndereco() {
			return complementoEndereco;
		}

		public void setComplementoEndereco(String complementoEndereco) {
			this.complementoEndereco = complementoEndereco;
		}

		public String getNumeroEndereco() {
			return numeroEndereco;
		}

		public void setNumeroEndereco(String numeroEndereco) {
			this.numeroEndereco = numeroEndereco;
		}

		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		public Estado getEstado() {
			return estado;
		}

		public void setEstado(Estado estado) {
			this.estado = estado;
		}

		public void valida() throws ValidacaoException {
			if(email == null || "".equals(email.trim())) {
				throw new ValidacaoException("Preencha o campo E-mail!");
			}
			if(telefone == null || "".equals(telefone.trim())) {
				throw new ValidacaoException("Preencha o campo Telefone!");
			}
			if(cidade == null || "".equals(cidade.trim())) {
				throw new ValidacaoException("Preencha o campo Cidade!");
			}
			if(estado == null) {
				throw new ValidacaoException("Preencha o campo Estado!");
			}
		}


	}

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

		public String getRazaoSocial() {
			return razaoSocial;
		}

		public void setRazaoSocial(String razaoSocial) {
			this.razaoSocial = razaoSocial;
		}

		public String getNomeFantasia() {
			return nomeFantasia;
		}

		public void setNomeFantasia(String nomeFantasia) {
			this.nomeFantasia = nomeFantasia;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

		public String getInscricaoEstadual() {
			return inscricaoEstadual;
		}

		public void setInscricaoEstadual(String inscricaoEstadual) {
			this.inscricaoEstadual = inscricaoEstadual;
		}

		public String getInscricaoMunicipal() {
			return inscricaoMunicipal;
		}

		public void setInscricaoMunicipal(String inscricaoMunicipal) {
			this.inscricaoMunicipal = inscricaoMunicipal;
		}

		public void valida() throws ValidacaoException {
			if(razaoSocial == null || "".equals(razaoSocial.trim())) {
				throw new ValidacaoException("Preencha o campo Razão Social!");
			}
			if(cnpj == null || "".equals(cnpj.trim())) {
				throw new ValidacaoException("Preencha o campo CNPJ!");
			}
			super.valida();
		}

	}
### Uma associação
## Criação do Banco de Dados
## Criação das classes Beans
## Criação da camada de persistência do modelo MVC II
## Criar as classes DAO Genérico referentes à manipulação e consulta dos dados de cada entidade envolvida
## É facultado o uso de JPA/Hibernate
## Implemente os códigos para gerenciamento de sessão do sistema
## Implemente os códigos para gerenciamento de login ao sistema
## Criação do Servlet para a administração e para a para a exibição na Web (Front Controller) em conjunto com o padrão Command
## Criação dos JSPs necessários para a camada de visualização do Site e da administração.
