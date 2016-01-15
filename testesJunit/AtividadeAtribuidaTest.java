package testesJunit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.Atividade;
import model.AtividadeAtribuida;
import model.Usuario;

import org.junit.Test;

import Dao.AtividadeAtribuidaDAO;
import Dao.AtividadeDAO;
import Dao.UsuarioDAO;

public class AtividadeAtribuidaTest {

	@Test
	public void testAtribui() {
		Usuario usr = new UsuarioDAO().recupera("10203040333");
		Atividade atv = new Atividade();
		
		try {
			atv = new AtividadeDAO().recupera(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AtividadeAtribuidaDAO atbdao = new AtividadeAtribuidaDAO();
		AtividadeAtribuida atb = new AtividadeAtribuida();
		atb.setAtividade(atv);
		atb.setFilho(usr);
		atb.setDataAtribuicao("2003-10-15");
		
		assertTrue(atbdao.atribui(atb));
		
		
		
	}

}
