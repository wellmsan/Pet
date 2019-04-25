package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;
import utils.Command;

public class UsuarioUpdate implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = UsuarioDAO.getInstance().getById(Long.parseLong(request.getParameter("id")));
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		try {
			usuario.valida();
			UsuarioDAO.getInstance().update(usuario);
			request.setAttribute("mensagem", "Usuário salvo com sucesso!!!");
			request.setAttribute("tipo", "success");
			request.setAttribute("usuarios", UsuarioDAO.getInstance().getAll());
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=UsuarioIndex");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("usuario", usuario);
			request.setAttribute("mensagem", "Falha ao salvar Usuário! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=UsuarioCreate");
			dispatcher.forward(request, response);
		}
	}
	
}
