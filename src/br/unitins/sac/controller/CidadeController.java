package br.unitins.sac.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Query;

import br.unitins.sac.factory.JPAFactory;
import br.unitins.sac.model.Cidade;

@ManagedBean
@ViewScoped
public class CidadeController extends Controller<Cidade> {

	private Cidade entity;
	private List<Cidade> listaCidade;
	
	@Override
	public Cidade getEntity() {
		if (entity == null)
			entity = new Cidade();
		return  entity;
	}
	
	
	public List<Cidade> getListaCidade() {
		if (listaCidade == null) {
			em = JPAFactory.getEntityManager();
			Query query = em.createQuery("Select a From Cidade a Order by a.id Desc");
			
			listaCidade = query.getResultList();
			
			if (listaCidade == null)
				listaCidade = new ArrayList<Cidade>();
		}
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaServidor) {
		this.listaCidade = listaServidor;
	}
	

}
