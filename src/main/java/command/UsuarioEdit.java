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

public class UsuarioEdit implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> usuarios = UsuarioDAO.getInstance().getAll();
		try {
			Usuario usuario = UsuarioDAO.getInstance().getById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/usuarios/edit.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("usuarios", usuarios);
			request.setAttribute("mensagem", "Falha ao editar Usuário! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/usuarios/index.jsp");
			dispatcher.forward(request, response);
		}	
	}
	
}
