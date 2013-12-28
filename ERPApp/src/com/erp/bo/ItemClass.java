package com.erp.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="item_cats")
public class ItemClass extends BaseEntity implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="item_class")
    private String className;    
 	
 	@Column(name="item_class_desc")
    private String classDesc;
 	
 	@OneToOne
 	@JoinColumn(name="id")
    private ItemCategory itemCat;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassDesc() {
		return classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}

	public ItemCategory getItemCat() {
		return itemCat;
	}

	public void setItemCat(ItemCategory itemCat) {
		this.itemCat = itemCat;
	}

	
 	
 	
}
