package com.bjpowernode.drp.util.datadict.domain;
/**
 * Êý¾Ý×Öµä³éÏó
 * @author Administrator
 *
 */
public abstract class AbstractDataDict {
	
	private String id;
	
	private String name;
	
	public String getId() {
		return id == null ?"":id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name == null ?"":name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
