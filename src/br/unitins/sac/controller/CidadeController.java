package br.unitins.sac.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unitins.sac.model.Cidade;
import br.unitins.sac.validation.CidadeValidation;
import br.unitins.sac.validation.Validation;

@ManagedBean
@ViewScoped
public class CidadeController extends Controller<Cidade> {

	@Override
	public Cidade getEntity() {
		if (entity == null)
			entity = new Cidade();
		return entity;
	}

	@Override
	public Validation<Cidade> getValidation() {
		return new CidadeValidation();
	}

	

}
