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
import utils.SessionUtil;

public class Registrar implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setEmail(request.getParameter("email"));
		
		//Verificar se Usuário já existe
		List<Usuario> usuarios = UsuarioDAO.getInstance().getAllBy(usuario);
		if(usuarios.isEmpty()) {
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			try {			
				UsuarioDAO.getInstance().save(usuario);
				SessionUtil.addUsuarioSession(request, response, usuario);
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
		} else {
			request.setAttribute("usuario", usuario);
			request.setAttribute("mensagem", "Este email já está sendo utilizado por outro usuário!");
			request.setAttribute("tipo", "warning");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./loginController?command=Registrar");
			dispatcher.forward(request, response);
		}
	}
	
}
