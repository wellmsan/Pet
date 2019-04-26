package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EstadoDAO;
import model.Estado;
import utils.Command;

public class EstadoSave implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Estado estado = new Estado();
		estado.setNome(request.getParameter("nome"));
		estado.setUf(request.getParameter("uf").toUpperCase());
		estado.setPais(request.getParameter("pais").toUpperCase());
		try {
			estado.valida();
			EstadoDAO.getInstance().save(estado);
			request.setAttribute("mensagem", "Estado salvo com sucesso!!!");
			request.setAttribute("tipo", "success");
			request.setAttribute("estadoes", EstadoDAO.getInstance().getAll());
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=EstadoIndex");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("estado", estado);
			request.setAttribute("mensagem", "Falha ao salvar Estado! " + e.getMessage());
			request.setAttribute("tipo", "danger");
			RequestDispatcher dispatcher = request.getRequestDispatcher("./controller?command=EstadoCreate");
			dispatcher.forward(request, response);
		}
	}
	
}
