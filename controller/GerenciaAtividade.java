package controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Logica;
import controller.ResponsavelController;
import model.Atividade;
import Dao.*;

public class GerenciaAtividade implements Logica  {
	
	private String url = "/gerenciar/";

		
	public String executa(HttpServletRequest request,HttpServletResponse response) {
		
		Atividade atividade = new Atividade();		
		boolean concluida = false;		
		
		
		// 1234 (CRUD)
		String op = request.getParameter("op");
		
		
		if(!op.isEmpty())
		switch (op){
		case "1": url = cadastraAtividade(request, response, url); // ok				
			break;
		case "2": url = listaAtividade(request, response, url); //ok
				url = "/relatorio/atividade/lista.jsp"; //ok
			break;
		case "3": url = alteraAtividade(request, response, url); // f
			break;
		case "4": url = removeAtividade(request, response, url); // f
			break;
		default:
			return url;
		}
		
		return url;
		
	}
	
	private String removeAtividade(HttpServletRequest request,
			HttpServletResponse response, String url) {
		
		String id = new String();
		id = request.getParameter("id");		
		
		if(id == null){			
			listaAtividade(request, response, url);
			url = url + "atividade/remove.jsp";
		}
		else{
			int cod = Integer.parseInt(id);		
			AtividadeDAO dao = new AtividadeDAO();
			boolean i = false;
			try {
				i = dao.remove(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i){
				request.setAttribute("msg", new String("Excluido com sucesso!"));
				url = url + "concluido.jsp";
			}
			else{
				request.setAttribute("msg", new String("Erro ao tentar excluir!"));
				url = url + "erro.jsp";
			}
		}		
		return url; 
	}

	private String alteraAtividade(HttpServletRequest request,
			HttpServletResponse response, String url) {
		AtividadeDAO dao = new AtividadeDAO();
		
		Atividade atividade = criaAtividade(request, response);				
		if(atividade != null)
			if(dao.altera(atividade)){
				request.setAttribute("msg", new String("Atualizado com sucesso"));
				url = url + "concluido.jsp"; 
			}
			else{
				request.setAttribute("msg", new String("Falha na atualizacao"));
				url = url + "concluido.jsp";
			}
		else
			url = url + "atualiza.jsp";
		return url;
	}	
				
	public String listaAtividade(HttpServletRequest request, HttpServletResponse response, String url){
				
		ArrayList<Atividade> atv = recupera(request, response);
		request.setAttribute("atividades", atv);
		url = url + "atividade/lista.jsp";		
		return url;
	}
	
	public String cadastraAtividade(HttpServletRequest request, HttpServletResponse response, String url){
		
		Atividade atividade = criaAtividade(request, response);
		
		int i = insere(atividade);
		
		if(i == 1){			
			String s = "Cadastro realizado com sucesso!";
			request.setAttribute("msg", s);
			url = url + "concluido.jsp";
			
		}			
		else if (i == 2){
			String s = "Cadastro já existe!";
			request.setAttribute("msg", s);
			url = url + "concluido.jsp";
		}
		else{
			String s = "Ocorreu um erro desconhecido ao tentar cadastrar!";
			request.setAttribute("msg", s);
			url = url  + "erro.jsp";
		}
		
		return url;
	}
	
	public Atividade criaAtividade(HttpServletRequest request, HttpServletResponse response){
		Atividade atividade = new Atividade();
		
		atividade.setNome(request.getParameter("nome"));
		atividade.setDescricao(request.getParameter("descricao"));
		atividade.setGrupoEtarioId(Integer.parseInt(request.getParameter("grupoEtarioId")));
		atividade.setMoedas(Integer.parseInt(request.getParameter("moedas")));
			
		
		return atividade;
	}
	
	


	private ArrayList<Atividade> recupera(HttpServletRequest request, HttpServletResponse response){
		ArrayList<Atividade> atividades = new ArrayList<Atividade>();
		AtividadeDAO dao = new AtividadeDAO();		
			
		/*String cpf = request.getParameter("cpf");
		if(!cpf.isEmpty()){			
			
			usuarios.add(dao.recupera(cpf));			
		}
		else*/
		
			try {
				atividades = dao.todasAtividades();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return atividades;
		
		
	}	


	private int insere(Atividade atividade){
	    AtividadeDAO dao = new AtividadeDAO();
	    return dao.insere(atividade);
	}
}
