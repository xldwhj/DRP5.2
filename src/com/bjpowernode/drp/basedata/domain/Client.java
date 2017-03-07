package com.bjpowernode.drp.basedata.domain;

import com.bjpowernode.drp.util.datadict.domain.ClientLevel;

/**
 * 分销商实体类
 * @author Administrator
 *
 */
public class Client {
	private int id;
	
	private int pid;
	
	private String clientId;//分销商代码
	
	private String bankAcctNo;//银行账户
	
	private String address;
	
	private String zipCode;//邮编
	
	private String isLeaf;//是否为叶子
	
	private String isClient;//是否为分销商
	
	private ClientLevel clientLevel;
	
	public ClientLevel getClientLevel() {
		return clientLevel;
	}

	public void setClientLevel(ClientLevel clientLevel) {
		this.clientLevel = clientLevel;
	}

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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getBankAcctNo() {
		return bankAcctNo;
	}

	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo == null ?"":bankAcctNo;
	}

	public String getAddress() {
		return address == null ?"":address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode == null ?"":zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getIsClient() {
		return isClient;
	}

	public void setIsClient(String isClient) {
		this.isClient = isClient;
	}
	
	private String name;
	
	private String contaceTel;

	public String getContaceTel() {
		return contaceTel == null ?"":contaceTel;
	}

	public void setContaceTel(String contaceTel) {
		this.contaceTel = contaceTel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
