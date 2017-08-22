package br.unitins.sac.repository;

import javax.persistence.EntityManager;

import br.unitins.sac.model.Aluno;

public class AlunoRepository extends Repository<Aluno>{

	public AlunoRepository(EntityManager em) {
		super(em);
	}

	@Override
	protected Class<Aluno> getModelClass() {
		return Aluno.class;
	}
	
}
