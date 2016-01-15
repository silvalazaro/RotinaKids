package Dao;

import static Dao.AtividadeDAO.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Filho;


public class BancoDeMoedasDAO {
    static Connection conexao;
	
    public BancoDeMoedasDAO(Connection conexaoBD) {
        this.conexao = conexaoBD;    
    }

    public int  mostrarSaldo(Filho filho) {
      
        int saldo = 0;
        try {
		PreparedStatement pstmt = conexao.prepareStatement("select saldo from banco_moedas where cod_filho ='"+filho.getId()+"'");
			
		ResultSet rs = pstmt.executeQuery();
                        
                while(rs.next())  
                {  
                   saldo = rs.getInt("saldo");   
                }

		} catch(SQLException e) {
                    System.out.println("Erro = "+e.getMessage());
		}		
        return saldo;
    }
    
    
    
}
