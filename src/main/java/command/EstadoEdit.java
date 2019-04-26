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
import model.Usuario;
import utils.Command;

public class EstadoEdit implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Estado> estados = EstadoDAO.getInstance().getAll();
		try {
			Estado estado = EstadoDAO.getInstance().getById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("estado", estado);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/estados/edit.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("estados", estados);
			request.setAttribute("mensagem", "Falha ao editar Estado! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/estados/index.jsp");
			dispatcher.forward(request, response);
		}	
	}
	
}
