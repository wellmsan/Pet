package utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioDAO;
import model.Usuario;

public class SessionUtil {

	public static Usuario usuarioLogado(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if (usuario == null) {
			try {
				response.sendRedirect("/Pet/index.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		return usuario; 
	}
	
	public static void addUsuarioSession(HttpServletRequest request, HttpServletResponse response, Usuario usuario) {
		HttpSession session = request.getSession(true);
		session.setAttribute("usuarioLogado", usuario);
	}
	
	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		usuarioLogado(request, response);
	}
}
