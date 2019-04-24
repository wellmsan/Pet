package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FornecedorDAO;
import model.Fornecedor;

public class FornecedorEdit implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Fornecedor> fornecedores = FornecedorDAO.getInstance().getAll();
		try {
			Fornecedor fornecedor = FornecedorDAO.getInstance().getById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("fornecedor", fornecedor);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/fornecedores/edit.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("fornecedores", fornecedores);
			request.setAttribute("mensagem", "Falha ao editar Usu�rio! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/fornecedores/index.jsp");
			dispatcher.forward(request, response);
		}		
	}
	
}
