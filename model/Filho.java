package model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Filho extends Usuario{
    private int id;
    private int responsavelId;    
    private Date nascimento;;

    public Filho(){
    	
    }
              
    
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getResponsavelId() {
		return responsavelId;
	}


	public void setResponsavelId(int responsavelId) {
		this.responsavelId = responsavelId;
	}


	public Date getNascimento() {
		return nascimento;
	}
	
	public String getNascimentoText() {
		SimpleDateFormat form = new SimpleDateFormat ("yyyy-MM-dd");
		return form.format(nascimento);
	}


	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	

	public void setNascimento(String nascimento) {
		
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.nascimento = date.parse(nascimento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Filho(String nome_filho,String cpf, int cod_responsavel,Date nascimento, int responsavelId) {
        super(nome_filho,cpf);
        this.responsavelId = responsavelId;
        this.nascimento = nascimento;
        
    }
    
    
}
