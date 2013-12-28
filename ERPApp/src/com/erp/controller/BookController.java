package com.erp.controller;

import com.erp.model.BookTreeNode;

import org.primefaces.event.NodeSelectEvent;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean
@SessionScoped
public class BookController implements Serializable {

    private static final long serialVersionUID = 20121705L;

    private static final String NODE_TYPE = "chapteritem";

    private TreeNode root;

    private TreeNode selectedNode;
    
    private List<BookTreeNode> tabNodes;
    
    private int tabIndex=0;

    public BookController() {
        root = new DefaultTreeNode("Root", null);

        TreeNode chapter1 = new DefaultTreeNode(new BookTreeNode("Item Category", "itemCategory_View.xhtml"), root);
        TreeNode chapter2 = new DefaultTreeNode(new BookTreeNode("Bill of Material", null), root);
        TreeNode chapter3 = new DefaultTreeNode(new BookTreeNode("Reports", null), root);
       
        constructChapter2(chapter2);
        constructChapter3(chapter3);
       
    }
    
    @PostConstruct
    public void init() {
    	 tabNodes=new ArrayList<BookTreeNode>();
    	 tabNodes.add(new BookTreeNode("Default", "/default.xhtml"));
    }

    

    private void constructChapter2(TreeNode chapter2) {
        new DefaultTreeNode(NODE_TYPE, new BookTreeNode("Customizing theme styles", "chapter2/customThemeStyles.xhtml"),
            chapter2);
       
    }

    private void constructChapter3(TreeNode chapter3) {
        new DefaultTreeNode(NODE_TYPE, new BookTreeNode("Formatted input with InputMask", "chapter3/inputMask.xhtml"),
            chapter3);
       
    }
    
    


	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public List<BookTreeNode> getTabNodes() {
		return tabNodes;
	}

	public void setTabNodes(List<BookTreeNode> tabNodes) {
		this.tabNodes = tabNodes;
	}

	public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        if (root != event.getTreeNode().getParent()) {
            
        

        for (TreeNode treeNode : root.getChildren()) {
            if (treeNode.equals(selectedNode.getParent())) {
                treeNode.setExpanded(true);
            } else {
                treeNode.setExpanded(false);
            }
        }}
        BookTreeNode selectedTreeNode = (BookTreeNode) event.getTreeNode().getData();
        tabNodes.add(new BookTreeNode(selectedTreeNode.getName(), "/views/"+selectedTreeNode.getView()));
        
        if(tabNodes.size()>0){
        	System.out.println("Size : "+tabNodes.size());
        tabIndex=tabNodes.size()-1;
        }
        
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getApplication().getNavigationHandler().handleNavigation(fc, "null",
            "/views/tab_View.xhtml" + "?faces-redirect=true");//+ ((BookTreeNode) event.getTreeNode().getData()).getView() + "?faces-redirect=true");
    }
}
