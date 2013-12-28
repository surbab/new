package com.erp.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.erp.bo.ItemCategory;
  
public class ItemCategoryDataModel extends ListDataModel<ItemCategory> implements SelectableDataModel<ItemCategory>, Serializable {    
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 8740852613194804280L;

	public ItemCategoryDataModel() {  
    }  
  
    public ItemCategoryDataModel(List<ItemCategory> data) {  
        super(data);  
    }  
      
    @Override  
    public ItemCategory getRowData(String rowKey) {  
        @SuppressWarnings("unchecked")
		List<ItemCategory> cats = (List<ItemCategory>) getWrappedData();  
          
        for(ItemCategory cat : cats) {  
            if(cat.getName().equals(rowKey))  
                return cat;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(ItemCategory cat) {  
        return cat.getName();  
    }  
}  