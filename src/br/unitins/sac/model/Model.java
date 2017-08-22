package br.unitins.sac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class Model<T> implements Serializable, Cloneable{
	
	private static final long serialVersionUID = -5636811877201895724L;
	
	@Version
	private Integer version;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;

	public abstract Integer getId();

	public abstract void setId(Integer id);

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	@PrePersist
	private void preInsert() {
		setDataCadastro(new Date());
		preUpdate();
	}
	
	@PreUpdate
	private void preUpdate() {
		setDataAlteracao(new Date());
	}


}
