package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;
import utils.Command;

public class EstadoUpdate implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario estado = UsuarioDAO.getInstance().getById(Long.parseLong(request.getParameter("id")));
		estado.setNome(request.getParameter("nome"));
		estado.setEmail(request.getParameter("email"));
		try {
			estado.valida();
			UsuarioDAO.getInstance().update(estado);
			request.setAttribute("mensagem", "Estado salvo com sucesso!!!");
			request.setAttribute("tipo", "success");
			request.setAttribute("estados", UsuarioDAO.getInstance().getAll());
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=UsuarioIndex");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("estado", estado);
			request.setAttribute("mensagem", "Falha ao salvar Estado! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=UsuarioCreate");
			dispatcher.forward(request, response);
		}
	}
	
}
