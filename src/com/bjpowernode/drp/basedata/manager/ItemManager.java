package com.bjpowernode.drp.basedata.manager;


import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.PageModel;

/**
 * ����ҵ���߼���ӿ�
 * @author Administrator
 *
 */
public interface ItemManager {
	/**
	 * �������
	 */
	public void addItem(Item item);
	
	/**
	 * ɾ������
	 * @param conn
	 * @param itemNos
	 */
	public boolean delItem(String[] itemNos);
	
	/**
	 * �޸�����
	 * @param conn
	 * @param item
	 */
	public void modifyItem(Item item);
	
	/**
	 * ����Id��������
	 * @param conn
	 * @param itemNo
	 * @param return ������ڷ���Item���󣬷��򷵻�No
	 */
	public Item findItemById(String itemNo);
	
	/**
	 * ����������ҳ��ѯ
	 * @param pagNo
	 * @param pagSize
	 * @param condation
	 * @return
	 */
	public PageModel<Item> findItemList(int pageNo,int pageSize,String condation);
}
