package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;
import utils.Command;

public class UsuarioRemove implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> usuarios = UsuarioDAO.getInstance().getAll();
		try {
			UsuarioDAO.getInstance().remove(UsuarioDAO.getInstance().getById(Long.parseLong(request.getParameter("id"))));
			request.setAttribute("usuarios", usuarios);
			response.sendRedirect("/Pet/controller?command=UsuarioIndex");
		} catch (Exception e) {
			request.setAttribute("usuarios", usuarios);
			request.setAttribute("mensagem", "Falha ao excluir Usuário! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=UsuarioIndex");
			dispatcher.forward(request, response);
		}
	}
	
}
