package com.erp.service;

import java.util.List;

import com.erp.bo.ItemCategory;
import com.erp.bo.ItemClass;


public interface ItemService {

	public int saveItemCategory(ItemCategory item) throws Exception;
	
	public  List<ItemCategory> getItemCategories() throws Exception;
	
	public int updateItemCategory(ItemCategory item) throws Exception;
	
	public void deleteItemCategory(ItemCategory item) throws Exception;
	
	public void saveItemClass(ItemClass item) throws Exception;
	
	public  List<ItemClass> getItemClass() throws Exception;
	
	public void updateItemClass(ItemClass item) throws Exception;
	
	public void deleteItemClass(ItemClass item) throws Exception;
}
