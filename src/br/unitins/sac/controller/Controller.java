package br.unitins.sac.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;

import br.unitins.sac.application.ApplicationException;
import br.unitins.sac.application.Config;
import br.unitins.sac.application.Util;
import br.unitins.sac.application.ValidationException;
import br.unitins.sac.factory.JPAFactory;
import br.unitins.sac.model.Model;
import br.unitins.sac.repository.Repository;

public abstract class Controller<T extends Model<? super T>> {
	
	protected EntityManager em;
	protected T entity;
	
	public void add(ActionEvent actionEvent) {
		try {
			em = JPAFactory.getEntityManager();
			Repository<T> repository = getRepository(em);
			em.getTransaction().begin();
//			validarEntidade();
			setEntity(repository.save(getEntity()));
			em.getTransaction().commit();
			clean();
			Util.infoMessage(Config.INSERT_SUCCESS_MSG);
		} catch (ValidationException e) {
			em.getTransaction().rollback();
			Util.showMessagesWarning(e.getListMessages());
		} catch (ApplicationException e) {
			em.getTransaction().rollback();
			Util.errorMessage(e.getMessage());
		}
		
	}
	
	public void update(ActionEvent actionEvent) {
		try {
			em = JPAFactory.getEntityManager();
			Repository<T> repository = getRepository(em);
			em.getTransaction().begin();
//			validarEntidade();
			setEntity(repository.save(getEntity()));
			em.getTransaction().commit();
			clean();
			Util.infoMessage(Config.UPDATE_SUCCESS_MSG);
		} catch (ValidationException e) {
			em.getTransaction().rollback();
			Util.showMessagesWarning(e.getListMessages());
		} catch (ApplicationException e) {
			em.getTransaction().rollback();
			Util.errorMessage(e.getMessage());
		} catch (OptimisticLockException e) {
			em.getTransaction().rollback();
			Util.warningMessage(Config.VERSION_CHANGE_MSG);
		}
	}

	public void delete(ActionEvent actionEvent) {
		try {
			em = JPAFactory.getEntityManager();
			Repository<T> repository = getRepository(em);
			T t = repository.find(getEntity().getId());
			em.getTransaction().begin();
			repository.remove(t);
			em.getTransaction().commit();
//			cleanEntity();
			Util.infoMessage(Config.DELETE_SUCCESS_MSG);
		} catch (ApplicationException e) {
			em.getTransaction().rollback();
			Util.errorMessage(e.getMessage());
		} catch (OptimisticLockException e) {
			em.getTransaction().rollback();
			System.out.println(e.toString());
			Util.warningMessage(Config.VERSION_CHANGE_MSG);
		} catch (Exception e) {
			em.getTransaction().rollback();
			Util.errorMessage(e.getMessage());
		}
		
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
			Constructor constructor = clazz.getConstructor(EntityManager.class);
			
			return (Repository<T>) constructor.newInstance(em);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException
				| SecurityException 	| IllegalArgumentException | InvocationTargetException e) {
			System.out.println(	"Não existe um repositório (repository) para o modelo " + getEntity().getClass().getName());
			e.printStackTrace();
		}

		return null;
	}
	
	protected void clean() {
		setEntity(null);
	}

}
