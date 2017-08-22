package br.unitins.sac.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.unitins.sac.application.ApplicationException;
import br.unitins.sac.application.ValidationException;
import br.unitins.sac.model.Entity;

public class Repository<T extends Entity> {

	private final EntityManager entityManager;
	private final Class<T> clazz;

	
	public Repository(EntityManager em) {
		entityManager = em;
		clazz = null;
//		clazz =  getModelClass();
	}
	
	public Repository(EntityManager em, Class<T> clazz) {
		this.entityManager = em;
		this.clazz = clazz;
	}


	public EntityManager geEntityManager() {
		return entityManager;
	}

	private Class<T> getClazz() {
		return clazz;
	}
	
	public T save(T t) throws ValidationException, ApplicationException, OptimisticLockException {
		try {
			return entityManager.merge(t);
		}catch (PersistenceException e) {
			if(e.getCause() instanceof OptimisticLockException)
				throw new javax.persistence.OptimisticLockException();
			else
				throw new ApplicationException(e.getMessage());
		} catch (IllegalStateException | DatabaseException | IllegalArgumentException  e) {
			throw new ApplicationException(e.getMessage());  
		}
	}

	public void remove(T t) throws ApplicationException {
		try {
			entityManager.remove(t);
		} catch (IllegalStateException | DatabaseException | IllegalArgumentException | PersistenceException  e) {
			throw new ApplicationException(e.getMessage());  
		}
	}
	
	public T find(Integer id) throws ApplicationException {
		try {
			return entityManager.getReference(getClazz(), id);
		} catch (IllegalStateException | DatabaseException | IllegalArgumentException | PersistenceException  e) {
			throw new ApplicationException(e.getMessage());  
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> find(Query query) throws ApplicationException {
		try {
			return query.getResultList();
		} catch (IllegalStateException | DatabaseException | IllegalArgumentException | PersistenceException  e) {
			throw new ApplicationException(e.getMessage());  
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findSQL(Query query) throws ApplicationException {
		try {
			return query.getResultList();
		} catch (IllegalStateException | DatabaseException | IllegalArgumentException | PersistenceException  e) {
			throw new ApplicationException(e.getMessage());  
		}
	}

}
