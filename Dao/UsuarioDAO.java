package Dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Filho;
import model.IUsuario;
import model.Login;
import model.Usuario;

public class UsuarioDAO{
    private static Connection connection;	
	
	public UsuarioDAO(){
		connection = DBConector.connect();		
	}
	
	// altera banco
	public boolean altera(Usuario usuario){
		try {
			Statement stmt = connection.createStatement();
			
			String sql = "UPDATE usuario SET nome_usuario = '"+ usuario.getNome()
					+"', tipo = " + usuario.getTipo()
					+", senha = '" + usuario.getSenha()
					+"', cpf = '" + usuario.getCpf()					
					+"' WHERE pk_usuario = "+ usuario.getId();
			
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	// insere banco
	public int insere(Usuario usuario){
		try {
			Statement stmt = connection.createStatement();
			String sql = "INSERT INTO USUARIO (nome_usuario, cpf, tipo, senha)"
					+ "VALUES ('"+usuario.getNome()+"','"+usuario.getCpf()+"', "+ usuario.getTipo() +", '" + usuario.getSenha()+ "' );";
			System.out.println(sql);
			int i = stmt.executeUpdate(sql);
			if(i != 0)
				return 1;
			return 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(isCadastrado(usuario))
				return 2;
			else
				return 0;
		}
		
	}
	
	// excluir
	public boolean remove(int id){
		System.out.println("funcao de remover");
		try {
			Statement stmt = connection.createStatement();
			String sql = "DELETE  FROM USUARIO WHERE pk_usuario = " + id;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean remove(String cpf){
		
		if(!cpf.isEmpty()){
			try {
				Statement stmt = connection.createStatement();
				String sql = "DELETE  FROM USUARIO WHERE cpf = '" + cpf + "'";				
				int i = stmt.executeUpdate(sql);
				if(i != 0 )
					return true;
				return false;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		return false;
	}

	public ArrayList<Usuario> todosUsuarios(){
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
				
		try {
								
			String sql = "SELECT * FROM usuario";
					
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			
			while(result.next()){
				Usuario usr = new Usuario();				
				usr.setId(result.getInt("pk_usuario"));
				usr.setCpf(result.getString("cpf"));	
				usr.setNome(result.getString("nome_usuario"));
				usr.setTipo(result.getInt("tipo"));

				// adiciiona no ArrayList
				usuarios.add(usr);
			}
			return usuarios;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return usuarios;
		}
		
	}
	
	public ArrayList<Usuario> todosUsuarios(int tipo){
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
				
		try {
								
			String sql = "SELECT * FROM usuario"
					+ "WHERE tipo = "+ tipo;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			
			while(result.next()){
				Usuario usr = new Usuario();				
				usr.setId(result.getInt("pk_usuario"));
				usr.setCpf(result.getString("cpf"));	
				usr.setNome(result.getString("nome_usuario"));
				usr.setTipo(result.getInt("tipo"));

				// adiciiona no ArrayList
				usuarios.add(usr);
			}
			return usuarios;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return usuarios;
		}
		
	}
	
	// recupera usuario
	public Usuario recupera(String cpf){
				Usuario usuario = new Usuario();
				try {
										
					String sql = "SELECT * FROM usuario "
							+ "WHERE cpf = '"+ cpf +"'";					
					PreparedStatement stmt = connection.prepareStatement(sql);
					ResultSet result = stmt.executeQuery();
					
					
					while(result.next()){
						usuario.setId(result.getInt("pk_usuario"));
						usuario.setCpf(result.getString("cpf"));	
						usuario.setNome(result.getString("nome_usuario"));
						usuario.setTipo(result.getInt("tipo"));

					}
					System.out.println(usuario.getNome()+"\n"+ usuario.getId());
					return usuario;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block					
					return usuario;
				}
				
			}
			
	// recupera usuario
		public Usuario buscaUsuario(int id){
			Usuario usuario = new Usuario();
			try {
									
				String sql = "SELECT * FROM usuario WHERE pk_usuario = " + id;
				PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet result = stmt.executeQuery();
				
				
				while(result.next()){
					usuario.setId(result.getInt("pk_usuario"));
					usuario.setCpf(result.getString("cpf"));	
					usuario.setNome(result.getString("nome_usuario"));
					usuario.setTipo(result.getInt("tipo"));

				}
				return usuario;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return usuario;
			}
		
		}
	
	public boolean isCadastrado(Usuario usuario){
		
		int i = buscaID(usuario.getCpf());
		if(i != 0)
			return true;		
		return false;
	}
		
	// consulta ID
	public int buscaID(String cpf){
		try {
			String sql = "SELECT pk_usuario FROM usuario "
					+ "WHERE cpf  = '"+ cpf +"'";		
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			int id = 0;
			
			while(result.next()){
				id = result.getInt("pk_usuario");
			}
			
			return id;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
}
