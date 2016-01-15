package model;

public class BancoDeMoedas {
    private int id;
    private int filhoId;
    private double saldo;

    public BancoDeMoedas(int pf_bancoMoedas, int cod_filho, double saldo) {
        this.id = pf_bancoMoedas;
        this.filhoId = cod_filho;
        this.saldo = saldo;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFilhoId() {
		return filhoId;
	}

	public void setFilhoId(int filhoId) {
		this.filhoId = filhoId;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

    
    
    
}
