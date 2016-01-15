package controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testesJunit.UsuarioDaoTest;
import model.Usuario;
import Dao.*;

public class GerenciaUsuario implements Logica  {
	
	private String url = "/gerenciar/";
	private String urlGer = "/gerenciar/";
		
	public String executa(HttpServletRequest request,HttpServletResponse response) {
		Usuario usuario = new Usuario();		
		boolean concluida = false;		
		
		
		// 1234 (CRUD)
		String op = request.getParameter("op");
		
		
		if(!op.isEmpty())
		switch (op){
		case "1": url = cadastraUsuario(request, response, url); // ok				
			break;
		case "2": url = listaUsuarios(request, response, url); //ok
				url = "/relatorio/usuario/lista.jsp"; //ok
			break;
		case "3": url = alteraUsuario(request, response, url); // f
			break;
		case "4": url = removeUsuario(request, response, url); // f
			break;
		default:
			return url;
		}
		
		return url;
		
	}
	
	private String removeUsuario(HttpServletRequest request,
			HttpServletResponse response, String url) {
		
		String id = new String();
		id = request.getParameter("id");		
		
		if(id == null){			
			listaUsuarios(request, response, url);
			url = url + "usuario/remove.jsp";
		}
		else{
			int cod = Integer.parseInt(id);
			System.out.println("codigo: " + cod);
			UsuarioDAO dao = new UsuarioDAO();
			boolean i = dao.remove(Integer.parseInt(id));
			if(i){
				request.setAttribute("msg", new String("Usuário '" + id + "' excluído!"));
				url = url + "concluido.jsp";
			}
			else{
				request.setAttribute("msg", new String("Não foi possível remover!"));
				url = url + "erro.jsp";
			}
		}		
		return url; 
	}

	private String alteraUsuario(HttpServletRequest request,
			HttpServletResponse response, String url) {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usr = criaUsuario(request, response);
		String id = request.getParameter("id");
		
		if(id == null & usr == null){	
			listaUsuarios(request, response, url);			
			return url + "usuario/atualiza.jsp";
		}
		else if (id != null & usr == null ){			
			Usuario usuario = dao.buscaUsuario(Integer.parseInt(id));
			request.setAttribute("usuario", usuario);
			listaUsuarios(request, response, url);
			return url + "usuario/atualiza.jsp";
		}
		else{
			usr.setId(Integer.parseInt(id));
			dao.altera(usr);
			request.setAttribute("msg", "Usuario '" + id +"' atualizado!");
			return url + "concluido.jsp";			
		}			
	
	}	
				
	public String listaUsuarios(HttpServletRequest request, HttpServletResponse response, String url){
				
		ArrayList<Usuario> usr = recupera(request, response);
		request.setAttribute("usuarios", usr);
		url = url + "usuario/lista.jsp";		
		return url;
	}
	
	public String cadastraUsuario(HttpServletRequest request, HttpServletResponse response, String url){
		Usuario usuario = criaUsuario(request, response);
		
		int i = insere(usuario);
		
		if(i == 1){			
			String s = "'" + usuario.getNome() + "' foi cadastrado no sistema!";
			request.setAttribute("msg", s);
			url = url + "concluido.jsp";
			
		}			
		else if (i == 2){
			String s = "Cadastro já existe!";
			request.setAttribute("msg", s);
			url = url + "concluido.jsp";
		}
		else{
			String s = "Não foi possível cadastrar!";
			request.setAttribute("msg", s);
			url = url  + "erro.jsp";
		}
		
		return url;
	}
	
	public Usuario criaUsuario(HttpServletRequest request, HttpServletResponse response){
		Usuario usr = new Usuario();
		
		usr.setCpf(request.getParameter("cpf"));
		usr.setNome(request.getParameter("nome"));
		usr.setTipo(1);
		usr.setSenha(request.getParameter("senha"));
		if(usr.getCpf() == null || usr.getNome() == null || usr.getSenha() == null)		
			return null;
		else if(usr.getCpf().isEmpty() || usr.getNome().isEmpty() || usr.getSenha().isEmpty())
			return null;
		return usr;
	}
	
	


	private ArrayList<Usuario> recupera(HttpServletRequest request, HttpServletResponse response){
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		UsuarioDAO dao = new UsuarioDAO();		
			
		/*String cpf = request.getParameter("cpf");
		if(!cpf.isEmpty()){			
			
			usuarios.add(dao.recupera(cpf));			
		}
		else*/
		
			usuarios = dao.todosUsuarios();
		
		return usuarios;
		
		
	}	


	private int insere(Usuario usuario){
	    UsuarioDAO dao = new UsuarioDAO();
	    return dao.insere(usuario);
	}
}
