# Pet
Projeto elaborado para avaliação da disciplina Projeto e Implementação Orientado a Objetos do MBA em Engenharia de Software - Estácio

## Objetivo do Trabalho
O aluno deverá ser capaz de desenvolver um sistema que realize as operações CRUD e controle sessão de usuário para Web seguindo os padrões MVC II, Front Controller e Command.

## Definição do domínio do negócio
## Modelo deverá possuir no mínimo
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
## Criação do Banco de Dados
## Criação das classes Beans
## Criação da camada de persistência do modelo MVC II
## Criar as classes DAO Genérico referentes à manipulação e consulta dos dados de cada entidade envolvida
## É facultado o uso de JPA/Hibernate
## Implemente os códigos para gerenciamento de sessão do sistema
## Implemente os códigos para gerenciamento de login ao sistema
## Criação do Servlet para a administração e para a para a exibição na Web (Front Controller) em conjunto com o padrão Command
## Criação dos JSPs necessários para a camada de visualização do Site e da administração.
