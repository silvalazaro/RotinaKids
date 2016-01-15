package Dao;

import static Dao.LoginDAO.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Filho;
import model.IFilho;
import model.Usuario;

public class FilhoDAO implements IFilho {
                                                                                                                                                                                                    
    private static Connection connection;    
	
    public FilhoDAO() {
        this.connection = DBConector.connect();
                 
    }
    
    public ArrayList<Filho> todosUsuarios(){
		ArrayList<Filho> filho = new ArrayList<Filho>();
				
		try {
								
			String sql = "SELECT u.pk_usuario, u.nome_usuario, u.cpf, u.tipo, f.cod_responsavel, f.nascimento, f.cod_responsavel"
					+ "FROM filho as  f, usuario as u WHERE fk_filho = pk_usuario";
					
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			
			while(result.next()){
				Filho fl = new Filho();						
				fl.setId(result.getInt("pk_usuario"));
				fl.setCpf(result.getString("cpf"));	
				fl.setNome(result.getString("nome_usuario"));
				fl.setTipo(result.getInt("tipo"));
				fl.setNascimento(result.getDate("f.nascimento"));
				fl.setResponsavelId(result.getInt("f.cod_responsavels"));

				// adiciiona no ArrayList
				filho.add(fl);
			}
			return filho;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return filho;
		}
		
	}
    
    /*public boolean validarDados(Filho filho, Responsavel responsavel) throws SQLException {	
        try {
			PreparedStatement pstmt = DBConector.connect().prepareStatement("select * from usuario where cpf ='"+filho.getCpf()+"'");
			ResultSet rs = pstmt.executeQuery();
			String nome = null;
			
			while(rs.next()) {
                            nome = rs.getString("nome_usuario");
			}
			
			if(nome != null) {
				return true;
			}
			else {
				return false;
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
    }
    */
    public int insere(Filho filho) throws SQLException {
    	UsuarioDAO daoUsuario = new UsuarioDAO();
    	daoUsuario.insere((Usuario) filho);
    	filho.setId(daoUsuario.buscaID(filho.getCpf()));
    	filho.setTipo(2);
    
    	 try{ 
    		 
        	 int i = 0;
            Statement statement = DBConector.connect().createStatement();
            
            String sql = "INSERT INTO filho(pk_filho, cod_responsavel, nascimento) VALUES"
            		+ "("+ filho.getId()+", "+ filho.getResponsavelId() +", '"+ filho.getNascimentoText() +"' )";
            i = statement.executeUpdate(sql);
            
            if(i == 1)
            	return 1;
            return 2;
        }
        catch(SQLException e){
        	daoUsuario.remove(filho.getId());
            return 0;
        }     
    }

    public boolean remove(int id) throws SQLException{
    	UsuarioDAO daoU = new UsuarioDAO();
    	
    	
        try {
            Statement statement = DBConector.connect().createStatement();
            String sql = "DELETE FROM filho WHERE pk_filho = "+ id;
            statement.executeUpdate(sql);            
           
            daoU.remove(id);
            //sql = "DELETE FROM banco_moedas WHERE cod_filho = " + id;
            //statement.executeUpdate(sql);
            
            
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean altera(Filho filho) throws SQLException {
    	UsuarioDAO daoU = new UsuarioDAO();
    	filho.setId(daoU.buscaID(filho.getCpf()));
        try {
            Statement statement = DBConector.connect().createStatement();
            String sql = "UPDATE filho SET cod_responsavel = "+ filho.getResponsavelId()
            		+", nascimento = '"+ filho.getNascimentoText() +"' WHERE pk_filho = " + filho.getId();
            System.out.println(sql);
            statement.executeUpdate(sql);
            
            daoU.altera(filho);
            
            return true;
        } catch (SQLException e) {        
            return false;
        }
    }
    
    public Filho  buscaUsuario(String cpf){
		Filho filho = new Filho();
		try {
						
			String sql = "SELECT u.pk_usuario, u.nome_usuario, u.cpf, u.tipo, f.cod_responsavel, f.nascimento, f.cod_responsavel"
					+ "FROM filho as  f, usuario as u WHERE fk_filho = pk_usuario and "
					+ "u.cpf = " + cpf ;					
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			
			while(result.next()){
				filho.setResponsavelId(result.getInt("f.cod_responsavel"));
				filho.setNascimento(result.getDate("f.nascimento"));
				filho.setId(result.getInt("pk_usuario"));
				filho.setCpf(result.getString("cpf"));	
				filho.setNome(result.getString("nome_usuario"));
				filho.setTipo(result.getInt("tipo"));

			}
			return filho;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return filho;
		}
		
	}

}
