package projetorotinakids;

import Dao.*;
import model.*;

public class Teste {
	public static void main(String[] args) {
		System.out.println("teste");
		cadUsr(1);
		
	}
	
	public static void cadUsr(int i){
		
		if(i == 1){
		
			Usuario u = new Usuario();
			UsuarioDAO dao = new UsuarioDAO();
			
			u.setCpf("10203040333");		
			u.setNome("Joao S Santos");
			u.setSenha("joao123");
			u.setTipo(1);
			
			System.out.println(dao.insere(u));
			
		}	
		
		
	}
}



