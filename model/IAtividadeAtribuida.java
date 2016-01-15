package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IAtividadeAtribuida {
    public boolean cadastrarAtividadeAtribuida(int status,Filho filho,Atividade atividade)throws SQLException;
    //public relatorioPorFilho(ArrayList<Filho> filhos) throws SQLException;
}
