package br.com.springboot.tabelafipe.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@SequenceGenerator(name = "seq_cadastro" , sequenceName = "seq_cadastro", allocationSize = 1, initialValue = 1)
public class Cadastro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cadastro")
	private Long codigo;

	@Column(unique = true, nullable = false)
	private String nome;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(unique = true, nullable = false)
	private String CPF;

	@Column(unique = true, nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date datanasc;
	
    public Cadastro() {
		
	}
	
	public Cadastro(String nome,  String email,
             String CPF) {
		
	this.email = email;
    this.CPF = CPF;
 
}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cadastro)) return false;
        Cadastro cadastros = (Cadastro) o;
        return Objects.equals(email, cadastros.email) &&
                Objects.equals(CPF, cadastros.CPF);
    }

@Override
    public int hashCode() {
        return Objects.hash(codigo, email, CPF);
                          
    }
@Override
    public String toString() {
        return "Cadastro{" +
                "codigo=" + codigo +
                ", email='" + email + '\'' +
                ", CPF=" + CPF  +
                '}';
    }
}
	
	
	
	
	