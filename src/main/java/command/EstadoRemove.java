package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EstadoDAO;
import dao.UsuarioDAO;
import model.Estado;
import utils.Command;

public class EstadoRemove implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Estado> estados = EstadoDAO.getInstance().getAll();
		try {
			EstadoDAO.getInstance().remove(EstadoDAO.getInstance().getById(Long.parseLong(request.getParameter("id"))));
			request.setAttribute("estados", estados);
			response.sendRedirect("/Pet/controller?command=EstadoIndex");
		} catch (Exception e) {
			request.setAttribute("estados", estados);
			request.setAttribute("mensagem", "Falha ao excluir Estado! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=EstadoIndex");
			dispatcher.forward(request, response);
		}
	}
	
}
