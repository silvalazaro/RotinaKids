package testesJunit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import junit.framework.Assert;
import model.Atividade;

import org.junit.Test;

import Dao.AtividadeDAO;

public class AtividadeTest {

	@Test
	public void testInsere() {
		Atividade atv = new Atividade();
		AtividadeDAO dao = new AtividadeDAO();
		
		atv.setDescricao("Cozinhar muitissimo");
		atv.setGrupoEtarioId(1);		
		atv.setMoedas(35);
		atv.setNome("Cozinhar");
		
		assertEquals("Cadastro ", 1, dao.insere(atv));
		
		try {
			dao.remove(dao.buscaID(atv.getNome()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
	}

	@Test
	public void testAltera(){
		Atividade atv = new Atividade();
		AtividadeDAO dao = new AtividadeDAO();
		
		atv.setDescricao("Cozinhar muitissimo");
		atv.setGrupoEtarioId(1);		
		atv.setMoedas(35);
		atv.setNome("Lavar louca");
		try {
			atv.setId(dao.buscaID(atv.getNome()));
			assertTrue(dao.altera(atv));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
