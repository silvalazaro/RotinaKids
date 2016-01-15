package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface ILogin {
    
    boolean validar(Login login) throws SQLException;	
	//void listar() throws SQLException;
   
}
