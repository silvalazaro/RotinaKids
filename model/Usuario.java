package model;

public class Usuario {
    private int id;
	private int tipo;
    private String nome;
    private String cpf;
    private String senha;
    private String endereco;
    
    public void setEndereco(String endereco){
    	this.endereco = endereco;
    }
    
    public String getEndereco(){
    	return this.endereco;
    }
    
    public Usuario(){
    	
    }
    
    public Usuario(String nome_usuario, String cpf) {
        this.nome = nome_usuario;
        this.cpf = cpf;
     
    }
    
    public Usuario(int pk_usuario, String nome_usuario, String cpf) {
        this.id = pk_usuario;
        this.nome = nome_usuario;
        this.cpf = cpf;
     
    }

    public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId() {
        return id;
    }

    public void setId(int pk_usuario) {
        this.id = pk_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome_usuario) {
        this.nome = nome_usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public boolean equals(Object usuario) {
		Usuario usr = (Usuario) usuario;
		if(this.nome.equalsIgnoreCase(usr.getNome()))
			if(this.cpf.equalsIgnoreCase(usr.getCpf()))
				if(this.id == usr.getId())
					if(this.getSenha().equalsIgnoreCase(usr.getSenha()))
						if(this.tipo == usr.getTipo())
							return true;
		return false;		
	}
    
}
