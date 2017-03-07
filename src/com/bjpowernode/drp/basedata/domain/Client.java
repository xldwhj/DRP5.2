package com.bjpowernode.drp.basedata.domain;

import com.bjpowernode.drp.util.datadict.domain.ClientLevel;

/**
 * ������ʵ����
 * @author Administrator
 *
 */
public class Client {
	private int id;
	
	private int pid;
	
	private String clientId;//�����̴���
	
	private String bankAcctNo;//�����˻�
	
	private String address;
	
	private String zipCode;//�ʱ�
	
	private String isLeaf;//�Ƿ�ΪҶ��
	
	private String isClient;//�Ƿ�Ϊ������
	
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
