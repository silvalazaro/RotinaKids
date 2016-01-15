/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

public class AtividadeAtribuidaController {
    
    public boolean validarOpcaoDeAtividadeParaExclusao(int opcao, int tamanhoListaDeAtividades){
        if(opcao <= tamanhoListaDeAtividades){
            return true;
        }
        else{
            return false;
        }
    
    }
    
}
