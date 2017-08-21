package br.unitins.sac.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.faces.event.ActionEvent;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

import br.unitins.sac.factory.JPAFactory;
import br.unitins.sac.repository.Repository;

public abstract class Controller<T extends Entity> {
	
	protected EntityManager em;
	protected T entity;
	
	public void add(ActionEvent actionEvent) {
//		try {
//			em = JPAFactory.getEntityManager();
//			Repository<T> repository = getRepository(em);
//			em.getTransaction().begin();
//			setEntity(repository.salvar(getEntity()));
		
		
	}
	
	public void update(ActionEvent actionEvent) {
		
	}

	public void delete(ActionEvent actionEvent) {
		
	}

	
	public abstract T getEntity();
	
	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Repository<T> getRepository(EntityManager em) {
		int f = this.getClass().getPackage().getName().indexOf("controller");
		String pack = this.getClass().getPackage().getName().substring(0, f);

		try {
			Class clazz = Class.forName(pack + "repository." + getEntity().getClass().getSimpleName() + "Repository");
			Constructor<T> constructor = clazz.getConstructor(EntityManager.class);
			return (Repository<T>) constructor.newInstance(em);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException
				| SecurityException 	| IllegalArgumentException | InvocationTargetException e) {
			System.out.println(	"Não existe um repositório (repository) para o modelo " + getEntity().getClass().getName());
			e.printStackTrace();
		}

		return null;

	}

}
