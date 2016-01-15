package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.*;

public class AtividadeDAO {

	static Connection conexao;

	public AtividadeDAO() {
		this.conexao = DBConector.connect();

	}

	public boolean altera(Atividade atividade){
		try {
			Statement stmt = conexao.createStatement();
			String sql = "UPDATE atividade SET nome_atividade = '" + atividade.getNome() 
					+ "' , descricao_atividade = '"+ atividade.getDescricao() +"', grupoEtario_atividade = " + atividade.getGrupoEtarioId()
					+ ", qtdMoedas_atividade = "+ atividade.getMoedas() +" WHERE pk_atividade = " + atividade.getId(); 
			
			int i = stmt.executeUpdate(sql);
			if (i != 0 )
				return true;
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public int insere(Atividade atividade){
		try {
			Statement statement = conexao
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			String sqlCad = "INSERT INTO ATIVIDADE (nome_atividade, descricao_atividade,grupoEtario_atividade,qtdMoedas_atividade) "
					+ "VALUES ('"
					+ atividade.getNome()
					+ "','"
					+ atividade.getDescricao()
					+ "','"
					+ atividade.getGrupoEtarioId()
					+ "','"
					+ atividade.getMoedas() + "');";
			System.out.println(sqlCad);
			int i = statement.executeUpdate(sqlCad);

			if (i != 0)
				return 1;
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			if(isCadastrado(atividade))
				return 2;
			return 0;
		}
	}

	public boolean remove(int id) throws SQLException {

		try {
			Statement statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			String sql = "DELETE FROM atividade WHERE pk_atividade = " + id;
			
			int i = statement.executeUpdate(sql);
			if(i != 0)
				return true;
			return false;			

		} catch (SQLException e) {
			return false;
		}
	}

	public Atividade recupera(int id) throws SQLException {
		Atividade atv = new Atividade();
		try {
			String sql = "select * from atividade where pk_atividade = " + id;
			PreparedStatement pstmt = conexao.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				atv.setDescricao(rs.getString("descricao_atividade"));
				atv.setGrupoEtarioId(rs.getInt("grupoEtario_atividade"));
				atv.setId(rs.getInt("pk_atividade"));
				atv.setMoedas(rs.getInt("qtdMoedas_atividade"));
				atv.setNome(rs.getString("nome_atividade"));
				
			}

		} catch (SQLException e) {
			System.out.println("Erro = " + e.getMessage());

		}
		return atv;
	}

	public boolean isCadastrado(Atividade atividade){
		int i;
		try {
			i = buscaID(atividade.getNome());			
			if(i != 0)
				return true;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int buscaID(String nome) throws SQLException {
		
		try {
			PreparedStatement pstmt = conexao.prepareStatement("select pk_atividade from atividade where nome_atividade ='"
							+ nome + "'");
			
			ResultSet rs = pstmt.executeQuery();
			int id = 0;
			
			while (rs.next()) {
				id = rs.getInt("pk_atividade");
			}
			return id;

		} catch (SQLException e) {
			System.out.println("Erro = " + e.getMessage());
			return 0;

		}
					
	}

	public ArrayList<Atividade> todasAtividades() throws SQLException {
		ArrayList<Atividade> atividades = new ArrayList();
		
		try {
			String sql = "select * from atividade ORDER BY nome_atividade ASC";
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(sql);
			String nome = null, descricao = null;
			int qtdMoedas = 0, pk_atividade = 0, grupoEtario = 10;

			while (rs.next()) {
				Atividade atv = new Atividade();
				
				atv.setId(rs.getInt("pk_atividade"));
				atv.setNome(rs.getString("nome_atividade"));				
				atv.setDescricao(rs.getString("descricao_atividade"));
				atv.setGrupoEtarioId(rs.getInt("grupoEtario_atividade"));
				atv.setMoedas(rs.getInt("qtdMoedas_atividade"));			
				
				atividades.add(atv);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return atividades;

	}

	public ArrayList<Atividade> todasAtividades(int grupoEtario) throws SQLException {
		ArrayList<Atividade> atividades = new ArrayList();
		ArrayList<Atividade> todasAtividades= todasAtividades();
		
		for(int i = 0; i < todasAtividades.size(); i++){
			Atividade atv = todasAtividades.get(i);
			
			if(atv.getGrupoEtarioId() == grupoEtario)
				atividades.add(atv);
		}		
		return atividades;
	}
	
	public boolean validarDados(Atividade atividade) throws SQLException {
		try {
			PreparedStatement pstmt = conexao
					.prepareStatement("select * from atividade where nome_atividade ='"
							+ atividade.getNome()
							+ "' and grupoEtario_atividade ='"
							+ atividade.getGrupoEtarioId() + "'");
			ResultSet rs = pstmt.executeQuery();
			String nome = null;
			while (rs.next()) {
				nome = rs.getString("nome_atividade");
			}

			if (nome != null) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
}
