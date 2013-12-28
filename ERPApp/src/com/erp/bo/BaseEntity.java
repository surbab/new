package com.erp.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Description TODO Specify class description.
 *
 * @author rushita
 */


@MappedSuperclass
public abstract class BaseEntity implements Serializable{
	public static final String CLASS_NAME = "BaseEntity";
	
	
	@Column(name="creat_on",  nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date created;

	@Column(name="creat_by",  nullable = true)
	protected int createdBy;

	@Column(name="mod_on",  nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updated;
	
	@Column(name="mod_by",  nullable = true)
	private int updatedBy;
	
//	@Version
//	int version;
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	

/*	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}*/

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	@PrePersist
	public void creationTimeStamp(){
	    created = new Date();
	    this.updateTimeStamp();
	    
	}

	@PreUpdate
	public void updateTimeStamp(){
	    updated = new Date();
	}
	
	
}
