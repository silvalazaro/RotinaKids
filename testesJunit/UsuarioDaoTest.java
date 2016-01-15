package testesJunit;

import static org.junit.Assert.*;
import model.Usuario;

import org.junit.Test;

import Dao.UsuarioDAO;

public class UsuarioDaoTest {

	@Test
	public void testInsere() {
		
		Usuario u = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();
		
		u.setCpf("10203040333");		
		u.setNome("Joao S Santos");
		u.setSenha("joao123");
		u.setTipo(1);
		assertEquals("Insere duplica",2 , dao.insere(u));
		
		u.setCpf("12345678911");		
		u.setNome("Maria S Santos");
		u.setSenha("maria123");
		u.setTipo(1);
		assertEquals("Insere ", 1 , dao.insere(u));
		
		dao.remove(dao.buscaID(u.getCpf()));
	
		
	}
	
	@Test
	public void testAltera() {
		Usuario uu = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();
		
		uu.setCpf("10203040333");		
		uu.setNome("Joao Silva Silva");
		uu.setSenha("joao123");
		uu.setTipo(1);
		
		assertTrue(dao.altera(uu));
		
	}
	

	@Test
	public void testRemove() {
		Usuario uu = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();
		
		uu.setCpf("10203040334");		
		uu.setNome("Joao Silva Silva");
		uu.setSenha("joao123");
		uu.setTipo(1);
		
		assertEquals("", 1, dao.insere(uu));
		assertTrue(dao.remove(uu.getCpf()));
	}
	@Test
	public void testRecupera(){
		Usuario uu = new Usuario();
		UsuarioDAO dao = new UsuarioDAO();		
		uu.setCpf("10203040333");		
		uu.setNome("Joao Silva Silva");
		uu.setId(1);		
		uu.setTipo(1);
		
		System.out.println(uu.equals(dao.recupera(uu.getCpf())));
		
	}
	/*
	
	@Test
	public void testTodosUsuarios() {
		
	}

	@Test
	public void testTodosUsuariosInt() {
		
	}

	@Test
	public void testBuscaUsuarioString() {
	
	}

	@Test
	public void testBuscaUsuarioInt() {
	
	}

	@Test
	public void testBuscaID() {
	
	}*/

}
