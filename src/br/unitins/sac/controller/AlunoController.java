package br.unitins.sac.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.unitins.sac.factory.JPAFactory;
import br.unitins.sac.model.Aluno;

public class AlunoController extends Controller<Aluno>{

	private Aluno entity;
	private List<Aluno> listaAluno;
	
	@Override
	public Aluno getEntity() {
		if (entity == null)
			entity = new Aluno();
		return  entity;
	}
	
	
	public List<Aluno> getListaAluno() {
		if (listaAluno == null) {
			em = JPAFactory.getEntityManager();
			Query query = em.createQuery("Select a From Aluno a Order by a.id Desc");
			
			listaAluno = query.getResultList();
			
			if (listaAluno == null)
				listaAluno = new ArrayList<Aluno>();
		}
		return listaAluno;
	}

	public void setListaAluno(List<Aluno> listaServidor) {
		this.listaAluno = listaServidor;
	}
	

}
