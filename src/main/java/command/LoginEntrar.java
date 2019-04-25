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

public class LoginEntrar implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		
		List<Usuario> usu = UsuarioDAO.getInstance().getAllBy(usuario);
		
		if(usu.isEmpty()) {
			usuario.setSenha("");
			request.setAttribute("usuario", usuario);
			request.setAttribute("mensagem", "Usuário e/ou senha inválidos! Tente novamente!");
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./loginController?command=Login");
			dispatcher.forward(request, response);
		} else {
			SessionUtil.addUsuarioSession(request, response, usu.get(0));
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=FornecedorIndex");
			dispatcher.forward(request, response);
		}		
	}
	
}
