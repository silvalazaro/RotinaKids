package model;
import java.util.Date;

public class AtividadeAtribuida {
	private Atividade atividade;
	private Usuario filho;
    private int id;
    
    private int situacao; // 0- Não realizada, 1- Realizada;
    private String dataAtribuicao;
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	public Usuario getFilho() {
		return filho;
	}
	public void setFilho(Usuario filho) {
		this.filho = filho;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSituacao() {
		return situacao;
	}
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	public String getDataAtribuicao() {
		return dataAtribuicao;
	}
	public void setDataAtribuicao(String dataAtribuicao) {
		this.dataAtribuicao = dataAtribuicao;
	}
    
    
}
