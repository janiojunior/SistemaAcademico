package br.unitins.sac.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.Query;

import br.unitins.sac.factory.JPAFactory;
import br.unitins.sac.model.Aluno;
import br.unitins.sac.model.Cidade;
import br.unitins.sac.repository.AlunoRepository;
import br.unitins.sac.validation.AlunoValidation;
import br.unitins.sac.validation.Validation;

@ManagedBean
@ViewScoped
public class AlunoController extends Controller<Aluno>{

	private List<Cidade> listaCidade;
	private List<Aluno> listaAluno;
	
	@Override
	public Aluno getEntity() {
		if (entity == null)
			entity = new Aluno();
		return  entity;
	}
	
	@Override
	public void clean(ActionEvent actionEvent) {
		super.clean(actionEvent);
		setListaAluno(null);
	}
	
	@Override
	public Validation<Aluno> getValidation() {
		return new AlunoValidation();
	}
	
	public List<Aluno> getListaAluno() {
		if (listaAluno == null) {
			AlunoRepository repository = new AlunoRepository(JPAFactory.getEntityManager());
			listaAluno = repository.bucarTodos();
		}
		return listaAluno;
	}
	
	public void setListaAluno(List<Aluno> listaServidor) {
		this.listaAluno = listaServidor;
	}
	
	public List<Cidade> getListaCidade() {
		if (listaCidade == null) {
			em = JPAFactory.getEntityManager();
			Query query = em.createQuery("Select c From Cidade c Order by c.nome ");
			
			listaCidade = query.getResultList();
			
			if (listaCidade == null)
				listaCidade = new ArrayList<Cidade>();
		}
		return listaCidade;
	}

	

	

}
