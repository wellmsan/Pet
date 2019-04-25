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

public class UsuarioIndex implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> usuarios = UsuarioDAO.getInstance().getAll();
		request.setAttribute("usuarios", usuarios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/usuarios/index.jsp");
		dispatcher.forward(request, response);	
	}
	
}
