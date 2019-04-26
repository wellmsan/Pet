package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EstadoDAO;
import utils.Command;

public class FornecedorCreate implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("estados", EstadoDAO.getInstance().getAll());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/fornecedores/create.jsp");
		dispatcher.forward(request, response);		
	}
	
}
