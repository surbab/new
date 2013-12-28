package com.erp.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import com.erp.bo.ItemCategory;
import com.erp.model.BookTreeNode;
import com.erp.service.ItemService;

@ManagedBean(name = "itemMB")
@ViewScoped
public class ItemCategoryMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2651114367897099758L;

	private static Logger log = Logger.getLogger(ItemCategoryMB.class);

	@ManagedProperty("#{ItemCategoryService}")
	private ItemService itemService;

	private String itemDesc;
	private String itemName;

	private String className;
	private String classDesc;
	private List<SelectItem> classCat;

	private List<ItemCategory> category;
	private List<ItemCategory> filteredCategory;
	private ItemCategory selectedCategory;

	@PostConstruct
	public void init() {
		updateList();
	}
	
	private void updateList(){
		try {
			category = itemService.getItemCategories();
		} catch (Exception e) {
			log.error("Exception :" + e);
		}
	}

	public String submitItem() {
		try {
			ItemCategory item = new ItemCategory();
			item.setName(getItemName());
			item.setDesc(getItemDesc());
			item.setCreatedBy(1);
			item.setCreated(new Date());
			
			int saved = itemService.saveItemCategory(item);
			if(saved==-1){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Data exists!", "Data already exists!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Saved data!", "Data successfully saved");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				BookController treeController = (BookController) FacesContext
						.getCurrentInstance().getExternalContext().getSessionMap()
						.get("bookController");
				if (treeController != null) {
					treeController.getTabNodes().remove(
							treeController.getTabIndex());
					Integer leng = treeController.getTabNodes().size();
					if (leng != null) {
						treeController.setTabIndex(leng - 1);
					}
				}
				updateList();
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("tabView");
			}
			
		} catch (Exception e) {
			log.error("Exception :" + e);
		}
		return null;
	}

	public void onEdit(RowEditEvent event) {
		try {
			ItemCategory item = (ItemCategory) event.getObject();
			if (item != null) {

				item.setUpdatedBy(1);
				item.setUpdated(new Date());

				itemService.updateItemCategory(item);

				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Updated data!", "Data successfully updated");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			log.error("Exception :" + e);
		}
	}

	public void onCancel(RowEditEvent event) {
		// FacesMessage msg = new FacesMessage("Data Deleted", ((ItemCategory)
		// event.getObject()).getModel());

		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public String deleteItem(ItemCategory item) {
		try {
			itemService.deleteItemCategory(item);

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Deleted data!", "Data successfully deleted");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
			updateList();
		} catch (Exception e) {
			log.error("Exception :" + e);
		}
		return null;
	}

	public String editItem() {
		try {
			if(getSelectedCategory()!=null){
			ItemCategory item = new ItemCategory();
			item.setId(getSelectedCategory().getId());
			item.setName(getSelectedCategory().getName());
			item.setDesc(getSelectedCategory().getDesc());
			item.setUpdatedBy(1);
			item.setUpdated(new Date());

			itemService.updateItemCategory(item);

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Updated data!", "Data successfully updated");
			FacesContext.getCurrentInstance().addMessage("change", msg);
			}
		} catch (Exception e) {
			log.error("Exception :" + e);
		}
		return null;
	}

	public String addItem() {
		System.out.println("Add Item!!");
		// return "/views/itemCategory_Add.xhtml";

		BookController treeController = (BookController) FacesContext
				.getCurrentInstance().getExternalContext().getSessionMap()
				.get("bookController");
		if (treeController != null) {
			treeController.getTabNodes().add(
					new BookTreeNode("Item Category Add",
							"/views/itemCategory_Add.xhtml"));
			Integer leng = treeController.getTabNodes().size();
			if (leng != null) {
				treeController.setTabIndex(leng - 1);
			}
		}
		return null;

	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<ItemCategory> getCategory() {
		return category;
	}

	public void setCategory(List<ItemCategory> category) {
		this.category = category;
	}

	public List<ItemCategory> getFilteredCategory() {
		return filteredCategory;
	}

	public void setFilteredCategory(List<ItemCategory> filteredCategory) {
		this.filteredCategory = filteredCategory;
	}

	public ItemCategory getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(ItemCategory selectedCategory) {
		this.selectedCategory = selectedCategory;
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

	public List<SelectItem> getClassCat() {
		return classCat;
	}

	public void setClassCat(List<SelectItem> classCat) {
		this.classCat = classCat;
	}

}
