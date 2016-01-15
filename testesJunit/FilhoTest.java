package testesJunit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.Filho;

import org.junit.Test;

import Dao.FilhoDAO;

public class FilhoTest {

	@Test
	public void testInsere() {
		Filho f = new Filho();
		FilhoDAO dao = new FilhoDAO();
		
		f.setCpf("12345678933");
		//f.setId(id);
		f.setNascimento("1995-05-05");
		f.setNome("Lucas");
		f.setResponsavelId(32);
		f.setSenha("123456");
		f.setTipo(2);
		
		
		try {
			assertEquals("Insere", 1, dao.insere(f));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertTrue("Remove", dao.remove(f.getId()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testAtualiza(){
		Filho fi = new Filho();
		FilhoDAO dao = new FilhoDAO();
		
		
		fi.setCpf("78945678933");
		fi.setResponsavelId(32);
		fi.setTipo(2);		
		//f.setId(id);
		fi.setNascimento("01-01-2005");
		fi.setNome("Silva");		
		fi.setSenha("654321");	
		
		try {
			dao.insere(fi);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			assertTrue("altera", dao.altera(fi));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
