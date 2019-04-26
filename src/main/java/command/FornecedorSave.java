package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EstadoDAO;
import dao.FornecedorDAO;
import model.Fornecedor;
import utils.Command;

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
