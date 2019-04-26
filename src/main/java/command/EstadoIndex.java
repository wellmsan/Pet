package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EstadoDAO;
import model.Estado;
import utils.Command;

public class EstadoIndex implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Estado> estados = EstadoDAO.getInstance().getAll();
		request.setAttribute("estados", estados);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/estados/index.jsp");
		dispatcher.forward(request, response);	
	}
	
}
