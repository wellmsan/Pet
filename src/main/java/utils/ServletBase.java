package utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ServletBase extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	protected abstract void index() throws ServletException, IOException;
	protected abstract void editar() throws ServletException, IOException;
	protected abstract void salvar() throws ServletException, IOException;
	protected abstract void atualizar() throws ServletException, IOException;
	protected abstract void excluir() throws ServletException, IOException;
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		String acao = request.getParameter("acao");
		if (acao != null && !"".equals(acao)) {
			switch (acao) {
			case "editar":
				editar();
				break;
			case "excluir":
				excluir();
				break;
			}
		} else {
			index();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;

		if(request.getParameter("id") != null)
			atualizar();
		else
			salvar();
		
	}
	
}
