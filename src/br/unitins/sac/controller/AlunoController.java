package br.unitins.sac.controller;

import br.unitins.sac.model.Aluno;

public class AlunoController extends Controller<Aluno>{

	private Aluno entity;
	
	@Override
	public Aluno getEntity() {
		if (entity == null)
			entity = new Aluno();
		return  entity;
	}
	
	
	
	

}
