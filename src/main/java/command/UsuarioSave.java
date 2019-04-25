package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioSave implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha("123456");
		try {
			usuario.valida();
			UsuarioDAO.getInstance().save(usuario);
			request.setAttribute("mensagem", "Usuario salvo com sucesso!!!");
			request.setAttribute("tipo", "success");
			request.setAttribute("usuarioes", UsuarioDAO.getInstance().getAll());
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=UsuarioIndex");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("usuario", usuario);
			request.setAttribute("mensagem", "Falha ao salvar Usuario! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=UsuarioCreate");
			dispatcher.forward(request, response);
		}
	}
	
}
