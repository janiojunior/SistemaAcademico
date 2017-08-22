package br.unitins.sac.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aluno extends br.unitins.sac.model.Entity<Aluno>{
	
	private static final long serialVersionUID = 5340998728426485061L;

	@Id
	private Integer id;
	private String matricula;
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
