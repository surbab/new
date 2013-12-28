package com.erp.model;

import java.io.Serializable;


public class BookTreeNode implements Serializable {

	private static final long serialVersionUID = 20120517L;

	private String name;

	private String view;

	public BookTreeNode(String name, String view) {
		this.name = name;
		this.view = view;
	}

	public String getName() {
		return name;
	}

	public String getView() {
		return view;
	}
}
