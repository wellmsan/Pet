package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EstadoDAO;
import dao.FornecedorDAO;
import model.Fornecedor;
import utils.Command;

public class FornecedorEdit implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Fornecedor> fornecedores = FornecedorDAO.getInstance().getAll();
		RequestDispatcher dispatcher = null;
		request.setAttribute("estados", EstadoDAO.getInstance().getAll());
		try {
			Fornecedor fornecedor = FornecedorDAO.getInstance().getById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("fornecedor", fornecedor);
			dispatcher = request.getRequestDispatcher("/fornecedores/edit.jsp");
		} catch (Exception e) {
			request.setAttribute("fornecedores", fornecedores);
			request.setAttribute("mensagem", "Falha ao editar Usuário! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			dispatcher = request.getRequestDispatcher("/fornecedores/index.jsp");
			
		}
		dispatcher.forward(request, response);
	}
	
}
