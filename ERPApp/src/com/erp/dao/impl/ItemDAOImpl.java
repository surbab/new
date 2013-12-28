package com.erp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.erp.bo.ItemCategory;
import com.erp.bo.ItemClass;
import com.erp.dao.ItemDAO;

@Repository("ItemCategoryRepository")
public class ItemDAOImpl implements ItemDAO {

	private EntityManager em;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

	
	
	public ItemCategory findByCategory(String cat) throws Exception{
		Query query = em.createQuery("From ItemCategory i where i.name =:nam");
		query.setParameter("nam", cat);
		List<ItemCategory> codeDet=(List<ItemCategory>)query.getResultList();
		if(codeDet.isEmpty()){
			return null;
		}else{
			return codeDet.get(0);	
		}
		
	}
	
	@Override
	@Transactional
	public int saveItemCategory(ItemCategory item) throws Exception{
		int saved=-1;
		ItemCategory itemCat = findByCategory(item.getName());
		if(itemCat==null){
		em.persist(item);
		saved=1;
		}
		return saved;
	}

	@Override
	public List<ItemCategory> getItemCatRepositories() throws Exception{
		return em.createQuery("from ItemCategory").getResultList();		
	}
	
	@Override
	@Transactional
	public int updateItemCategory(ItemCategory item) throws Exception {
		Query q=null;	
		int updated = -1;
		ItemCategory itemCat = findByCategory(item.getName());
		if(itemCat == null){
		q = em.createQuery ("UPDATE ItemCategory t SET t.name = :nam , t.desc = :des, t.updatedBy = :updatedBy, t.updated=:updatedOn where t.id = :id");
		q.setParameter("id", item.getId());
		q.setParameter ("nam", item.getName());
		q.setParameter ("des", item.getDesc());
		q.setParameter("updatedBy", item.getUpdatedBy());
		q.setParameter("updatedOn", item.getUpdated());
			
		updated =  q.executeUpdate ();
		
		}
		return updated;
	}
	
	
	

	@Override
	@Transactional
	public void saveItemClass(ItemClass item) throws Exception {
		em.persist(item);
	}

	@Override
	public List<ItemClass> getItemClassRepositories() throws Exception {
		return em.createQuery("from ItemClass").getResultList();		
	}
	
	@Override
	@Transactional
	public void updateItemClass(ItemClass item) throws Exception {
		Query q=null;		
		q = em.createQuery ("UPDATE ItemClass t SET t.className = :nam , t.itemCat = :cat, t.classDesc = :des, t.updatedBy = :updatedBy, t.updated=:updatedOn where t.id = :id");	
		q.setParameter("id", item.getId());
		q.setParameter ("nam", item.getClassName());
		q.setParameter("cat", item.getItemCat());
		q.setParameter ("des", item.getClassDesc());
		q.setParameter("updatedBy", item.getUpdatedBy());
		q.setParameter("updatedOn", item.getUpdated());
			
		int updated = q.executeUpdate ();
		
	}

	@Override
	@Transactional
	public void deleteItemCategory(ItemCategory item) throws Exception {
		Query q=null;		
		q = em.createQuery ("DELETE from ItemCategory t where t.id = :id");	
		q.setParameter("id", item.getId());
		int deleted = q.executeUpdate ();
	}

	@Override
	@Transactional
	public void deleteItemClass(ItemClass item) throws Exception {
		Query q=null;		
		q = em.createQuery ("DELETE from ItemClass t where t.id = :id");	
		q.setParameter("id", item.getId());
		int deleted = q.executeUpdate ();
		
	}

}
