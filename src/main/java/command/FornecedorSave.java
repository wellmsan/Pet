package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FornecedorDAO;
import model.Fornecedor;

public class FornecedorSave implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fornecedor fornecedor = new Fornecedor();
		//TODO Implementar campos
		try {
			fornecedor.valida();
			FornecedorDAO.getInstance().save(fornecedor);
			request.setAttribute("mensagem", "Fornecedor salvo com sucesso!!!");
			request.setAttribute("tipo", "success");
			request.setAttribute("fornecedores", FornecedorDAO.getInstance().getAll());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/fornecedores/index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("fornecedor", fornecedor);
			request.setAttribute("mensagem", "Falha ao salvar Fornecedor! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/fornecedores/create.jsp");
			dispatcher.forward(request, response);
		}
	}
	
}
