/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IAtividade {
    
    public  boolean excluirAtividade(Atividade atividade) throws SQLException;
    public ArrayList<Atividade> buscarListaDeAtividadesCadastradas() throws SQLException;
    public ArrayList<Atividade> buscarListaDeAtividadesParaFilho(Filho filho);
    public boolean inserirAtividade(Atividade atividade) throws SQLException;
    public int buscarIdAtividade(Atividade atividade) throws SQLException;
    public boolean validarDados(Atividade atividade) throws SQLException;
 
}
