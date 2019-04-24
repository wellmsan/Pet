package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FornecedorDAO;
import model.Fornecedor;

public class FornecedorRemove implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Fornecedor> fornecedores = FornecedorDAO.getInstance().getAll();
		try {
			FornecedorDAO.getInstance().remove(FornecedorDAO.getInstance().getById(Long.parseLong(request.getParameter("id"))));
			request.setAttribute("fornecedores", fornecedores);
			response.sendRedirect("/Pet/controller?command=FornecedorIndex");
		} catch (Exception e) {
			request.setAttribute("fornecedores", fornecedores);
			request.setAttribute("mensagem", "Falha ao excluir Fornecedor! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/fornecedores/index.jsp");
			dispatcher.forward(request, response);
		}
	}
	
}
