package com.bjpowernode.drp.basedata.dao;

import java.sql.Connection;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.PageModel;

/**
 * ���ϵ����ݷ��ʽӿڣ���ΪMySql�Լ�Oracle�����ݿ�ʵ�ֵĽӿ�
 * @author Administrator
 *
 */
public interface ItemDao {
	
	/**
	 * �������
	 */
	public void addItem(Connection conn,Item item);
	
	/**
	 * ɾ������
	 * @param conn
	 * @param itemNos
	 */
	public boolean delItem(Connection conn,String[] itemNos);
	
	/**
	 * �޸�����
	 * @param conn
	 * @param item
	 */
	public void modifyItem(Connection conn,Item item);
	
	/**
	 * ����Id��������
	 * @param conn
	 * @param itemNo
	 * @param return ������ڷ���Item���󣬷��򷵻�No
	 */
	public Item findItemById(Connection conn,String itemNo);
	
	/**
	 * ����������ҳ��ѯ
	 * @param pagNo
	 * @param pagSize
	 * @param condation
	 * @return
	 */
	public PageModel<Item> findItemList(Connection conn,int pageNo,int pageSize,String condation);
}
