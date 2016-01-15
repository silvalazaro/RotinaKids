package Dao;

import static Dao.AtividadeDAO.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Atividade;
import model.AtividadeAtribuida;
import model.Filho;
import model.IAtividadeAtribuida;
import model.Usuario;

public class AtividadeAtribuidaDAO {

	static Connection conexao;

	public AtividadeAtribuidaDAO() {
		this.conexao = DBConector.connect();
	}

	public boolean atribui(AtividadeAtribuida atribuicao) {

		try {
			Statement statement = conexao
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			String sql = "INSERT INTO ATIVIDADE_ATRIBUIDA (data_atividade_atividadeAtribuida, cod_filho, "
					+ "status_atividade_atividadeAtribuida, cod_atividade ) VALUES ('"
					+ atribuicao.getDataAtribuicao()+ "',"
					+ atribuicao.getFilho().getId()+ ","
					+ atribuicao.getSituacao()+ ", "
					+ atribuicao.getAtividade().getId()+ ");";
			
			System.out.println(sql);
			int i =	statement.executeUpdate(sql);
			
			if( i != 0 )
				return true;
			return false;
		}

		catch (SQLException e) {
			return false;
		}		
	}

	
	private ArrayList<AtividadeAtribuida> todasAtribuicoes(){
		ArrayList<AtividadeAtribuida> atribuicoes = new ArrayList<AtividadeAtribuida>();
		
		try {
			String sql = "select distinct a.pk_atividade, a.nome_atividade, a.descricao_atividade, a.grupoEtario_atividade, a.qtdMoedas_atividade, "
					+ "	u.pk_usuario, u.nome_usuario, u.cpf, u.tipo, u.senha, d.pk_atividadeAtribuida, "
					+ "	d.data_atividade_atividadeAtribuida, d.status_atividade_atividadeAtribuida "
					+ " from atividade as a, usuario as u, atividade_atribuida as d "
					+ " where a.pk_atividade = d.cod_atividade and u.pk_usuario = d.cod_filho ";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet res = stmt.executeQuery();
			
			while(res.next()){
				AtividadeAtribuida atb = new AtividadeAtribuida();
				Atividade atv = new Atividade();
				Usuario usr = new Usuario();
				
				// atividade
				atv.setDescricao(res.getString("a.descricao_atividade"));
				atv.setGrupoEtarioId(res.getInt("a.grupoEtario_atividade"));
				atv.setMoedas(res.getInt("a.qtdMoedas_atividade"));
				atv.setNome(res.getString("a.nome_atividade"));
				atv.setId(res.getInt("a.pk_atividade"));			
				
				// usr
				usr.setCpf(res.getString("u.cpf"));
				usr.setId(res.getInt("u.pk_usuario"));
				usr.setNome(res.getString("u.nome_usuario"));
				usr.setSenha(res.getString("u.senha"));
				usr.setTipo(res.getInt("u.tipo"));
				
				// atribuicao
				atb.setId(res.getInt("pk_atividadeAtribuida"));
				atb.setDataAtribuicao(res.getString("data_atividade_atividadeAtribuida"));
				atb.setSituacao(res.getInt("status_atividade_atividadeAtribuida"));
				
				atb.setFilho(usr);
				atb.setAtividade(atv);
				
				atribuicoes.add(atb);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return atribuicoes;		
	}
	
	public String retornaDataDoDia() {

		Date hoje = new Date();
		SimpleDateFormat df;
		df = new SimpleDateFormat("dd/MM/yyyy");
		String data = df.format(hoje);
		return data;
	}


}