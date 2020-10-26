package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

@WebServlet("/salvarProduto")
public class ServletsProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public ServletsProduto() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(produto);
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtoss", daoProduto.listar());
				view.forward(request, response);
				return;
			} else if (acao.equalsIgnoreCase("editar")) {

				BeanProduto beanProduto = daoProduto.consultar(produto);

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", beanProduto);
				request.setAttribute("produtoss", daoProduto.listar());
				view.forward(request, response);
				return;
			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtoss", daoProduto.listar());
				view.forward(request, response);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtoss", daoProduto.listar());
				view.forward(request, response);
				return;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			BeanProduto produto = new BeanProduto();
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produto.setNome(nome);
			produto.setQuantidade(Double.parseDouble(quantidade));
			produto.setValor(Double.parseDouble(valor));
			try {

				String msg = null;
				boolean podeInserir = true;

				if (id == null || id.isEmpty() && !daoProduto.validarNome(nome)) {// QUANDO
																					// FDOR
																					// PRODUTO
																					// NOVO
					msg = "Produto j� existe com o mesmo nome!";
					podeInserir = false;

				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				}

				if (id == null || id.isEmpty() && daoProduto.validarNome(nome)
						&& podeInserir) {

					daoProduto.salvar(produto);

				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoProduto.atualizar(produto);
				}

				if (!podeInserir) {
					request.setAttribute("produto", produto);
				}

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtoss", daoProduto.listar());
				view.forward(request, response);
				return;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
