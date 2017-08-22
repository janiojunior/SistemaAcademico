package br.unitins.sac.model;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable{
	
	private static final long serialVersionUID = -5636811877201895724L;

	public abstract Integer getId();

	public abstract void setId(Integer id);


}
