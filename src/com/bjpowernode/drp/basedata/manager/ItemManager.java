package com.bjpowernode.drp.basedata.manager;


import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.PageModel;

/**
 * 物料业务逻辑层接口
 * @author Administrator
 *
 */
public interface ItemManager {
	/**
	 * 添加物料
	 */
	public void addItem(Item item);
	
	/**
	 * 删除物料
	 * @param conn
	 * @param itemNos
	 */
	public boolean delItem(String[] itemNos);
	
	/**
	 * 修改物料
	 * @param conn
	 * @param item
	 */
	public void modifyItem(Item item);
	
	/**
	 * 根据Id查找物料
	 * @param conn
	 * @param itemNo
	 * @param return 如果存在返回Item对象，否则返回No
	 */
	public Item findItemById(String itemNo);
	
	/**
	 * 根据条件分页查询
	 * @param pagNo
	 * @param pagSize
	 * @param condation
	 * @return
	 */
	public PageModel<Item> findItemList(int pageNo,int pageSize,String condation);
}
