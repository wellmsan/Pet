package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UsuarioServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Usuario> usuarios = UsuarioDAO.getInstance().getAll();
		String acao = request.getParameter("acao");
		if (acao != null && !"".equals(acao)) {
			switch (acao) {
			case "editar":
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
				break;
			case "excluir":
				try {
					UsuarioDAO.getInstance().remove(UsuarioDAO.getInstance().getById(Long.parseLong(request.getParameter("id"))));
					request.setAttribute("usuarios", usuarios);
					//RequestDispatcher dispatcher = request.getRequestDispatcher("/usuarios/index.jsp");
					//dispatcher.forward(request, response);
					response.sendRedirect("/Pet/usuarios");
				} catch (Exception e) {
					request.setAttribute("usuarios", usuarios);
					request.setAttribute("mensagem", "Falha ao excluir Usuário! " + e.getMessage());
					request.setAttribute("tipo", "danger");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/usuarios/index.jsp");
					dispatcher.forward(request, response);
				}
				break;
			}
		} else {
			request.setAttribute("usuarios", usuarios);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/usuarios/index.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usuario = new Usuario();
		if(request.getParameter("id") != null)
			usuario.setId(Long.parseLong(request.getParameter("id")));
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha("123456");

		try {
			usuario.valida();
			
			if(usuario.getId() == null)
				UsuarioDAO.getInstance().save(usuario);
			else 
				UsuarioDAO.getInstance().update(usuario);

			request.setAttribute("mensagem", "Usuário salvo com sucesso!!!");
			request.setAttribute("tipo", "success");

			request.setAttribute("usuarios", UsuarioDAO.getInstance().getAll());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/usuarios/index.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			request.setAttribute("usuario", usuario);
			request.setAttribute("mensagem", "Falha ao salvar Usuário! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/usuarios/create.jsp");
			dispatcher.forward(request, response);
		}
	}

}
