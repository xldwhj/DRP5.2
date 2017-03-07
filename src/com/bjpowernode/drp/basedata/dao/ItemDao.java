package com.bjpowernode.drp.basedata.dao;

import java.sql.Connection;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.PageModel;

/**
 * 物料的数据访问接口，作为MySql以及Oracle等数据库实现的接口
 * @author Administrator
 *
 */
public interface ItemDao {
	
	/**
	 * 添加物料
	 */
	public void addItem(Connection conn,Item item);
	
	/**
	 * 删除物料
	 * @param conn
	 * @param itemNos
	 */
	public boolean delItem(Connection conn,String[] itemNos);
	
	/**
	 * 修改物料
	 * @param conn
	 * @param item
	 */
	public void modifyItem(Connection conn,Item item);
	
	/**
	 * 根据Id查找物料
	 * @param conn
	 * @param itemNo
	 * @param return 如果存在返回Item对象，否则返回No
	 */
	public Item findItemById(Connection conn,String itemNo);
	
	/**
	 * 根据条件分页查询
	 * @param pagNo
	 * @param pagSize
	 * @param condation
	 * @return
	 */
	public PageModel<Item> findItemList(Connection conn,int pageNo,int pageSize,String condation);
}
