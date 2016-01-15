package model;

public class Atividade {
    private int id;
    private String nome;
    private String descricao;
    private int grupoEtarioId;
    private int bonus;
    
    public Atividade(){
    	
    }
    
    public Atividade(String nome_atividade, String descricao_atividade, int grupoEtario_atividade, int qtdMoedas_atividade) {
   
        this.nome = nome_atividade;
        this.descricao = descricao_atividade;
        this.grupoEtarioId = grupoEtario_atividade;
        this.bonus= qtdMoedas_atividade;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getGrupoEtarioId() {
		return grupoEtarioId;
	}

	public void setGrupoEtarioId(int grupoEtarioId) {
		this.grupoEtarioId = grupoEtarioId;
	}

	public int getMoedas() {
		return bonus;
	}

	public void setMoedas(int moedas) {
		this.bonus = moedas;
	}
    

    
}
