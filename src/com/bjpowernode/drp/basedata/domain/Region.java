package com.bjpowernode.drp.basedata.domain;
/**
 * 区域实体类
 * @author Administrator
 *
 */
public class Region {
	private int id;
	
	private int pid;
	
	private String name;
	
	private String isLeft;
	
	private String isClient;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsLeft() {
		return isLeft;
	}

	public void setIsLeft(String isLeft) {
		this.isLeft = isLeft;
	}

	public String getIsClient() {
		return isClient;
	}

	public void setIsClient(String isClient) {
		this.isClient = isClient;
	}
}
