package br.unitins.sac.repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

public class Repository<T extends Entity> {

	private final EntityManager entityManager;
	private final Class<T> clazz;

	public Repository(EntityManager em, Class<T> clazz) {
		this.entityManager = em;
		this.clazz = clazz;
	}


	public EntityManager geEntityManager() {
		return entityManager;
	}

	public Class<T> getClazz() {
		return clazz;
	}

}
