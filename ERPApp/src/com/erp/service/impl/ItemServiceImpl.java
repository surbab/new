package com.erp.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.bo.ItemCategory;
import com.erp.bo.ItemClass;
import com.erp.dao.ItemDAO;
import com.erp.service.ItemService;

@Service("ItemCategoryService")
public class ItemServiceImpl implements ItemService,Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7948873501296011568L;
	@Resource(name = "ItemCategoryRepository")
	ItemDAO itemCatRepository;

	@Override
	@Transactional
	public int saveItemCategory(ItemCategory item) throws Exception {
		return itemCatRepository.saveItemCategory(item);

	}

	@Override
	public  List<ItemCategory> getItemCategories() throws Exception {
		return itemCatRepository.getItemCatRepositories();
		
	}

	@Override
	@Transactional
	public int updateItemCategory(ItemCategory item) throws Exception {
		 return itemCatRepository.updateItemCategory(item);
		
	}

	@Override
	@Transactional
	public void saveItemClass(ItemClass item) throws Exception {
		itemCatRepository.saveItemClass(item);
		
	}

	@Override
	public List<ItemClass> getItemClass() throws Exception{
		// TODO Auto-generated method stub
		return itemCatRepository.getItemClassRepositories();
	}

	@Override
	@Transactional
	public void updateItemClass(ItemClass item) throws Exception{
		itemCatRepository.updateItemClass(item);
		
	}

	@Override
	@Transactional
	public void deleteItemCategory(ItemCategory item) throws Exception{
		itemCatRepository.deleteItemCategory(item);
		
	}

	@Override
	@Transactional
	public void deleteItemClass(ItemClass item) throws Exception{
		itemCatRepository.deleteItemClass(item);
		
	}

	
	
}
