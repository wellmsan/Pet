package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FornecedorDAO;
import model.Fornecedor;
import utils.Command;

public class FornecedorIndex implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Fornecedor> fornecedores = FornecedorDAO.getInstance().getAll();
		request.setAttribute("fornecedores", fornecedores);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/fornecedores/index.jsp");
		dispatcher.forward(request, response);		
	}
	
}
